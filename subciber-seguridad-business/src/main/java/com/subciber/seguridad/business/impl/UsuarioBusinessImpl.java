/**
 * 
 */
package com.subciber.seguridad.business.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.EliminarObjetoDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.RequestGenericEliminarDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.UsuarioBusiness;
import com.subciber.seguridad.business.util.ConfigBusiness;
import com.subciber.seguridad.client.api.EmailClient;
import com.subciber.seguridad.client.dto.EmailRequestClientDto;
import com.subciber.seguridad.dao.api.UsuarioDetalleRxDao;
import com.subciber.seguridad.dao.api.UsuarioDetalleTxDao;
import com.subciber.seguridad.dao.api.UsuarioRolTxDao;
import com.subciber.seguridad.dao.api.UsuarioRxDao;
import com.subciber.seguridad.dao.api.UsuarioTxDao;
import com.subciber.seguridad.dao.util.ConfigDao;
import com.subciber.seguridad.dto.UsuarioActualizacionDto;
import com.subciber.seguridad.dto.UsuarioDetalleDto;
import com.subciber.seguridad.dto.UsuarioDetalleFiltroDto;
import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.entity.Usuario;
import com.subciber.seguridad.entity.UsuarioDetalle;
import com.subciber.seguridad.entity.UsuarioRol;
import com.subciber.seguridad.entity.VUsuario;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.ConstantesConfig;
import com.subciber.seguridad.util.SendHtmlEmail;
import com.subciber.seguridad.util.Utilitario;

/**
 * @description implementacion de la interface UsuarioBusiness
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
@Dependent
public class UsuarioBusinessImpl implements UsuarioBusiness, Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
    private MessageProvider messageProvider;
	@Inject
	private Utilitario utilitario;
	@EJB
	private UsuarioTxDao usuarioTxDao;
	@EJB
	private UsuarioRxDao usuarioRxDao;
	@EJB
	private UsuarioDetalleTxDao usuarioDetalleTxDao;
	@EJB
	private UsuarioDetalleRxDao usuarioDetalleRxDao;
	@EJB
	private UsuarioRolTxDao usuarioRolTxDao; 
	
	@Inject
	private EmailClient emailClient;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<UsuarioDetalleDto> crearUsuario(RequestGenericDto<UsuarioDetalleDto> request)
			throws BusinessException {
		
		ResponseGenericDto<UsuarioDetalleDto> response = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			response = new ResponseGenericDto<>();
			
			//1. Verificamos si existe el usuario registrado
			UsuarioFiltroDto requestConsultarUsuario = new UsuarioFiltroDto(); 
			requestConsultarUsuario.setEstadoId(ConstantesConfig.activo);
			requestConsultarUsuario.setUsuario(request.getObjectRequest().getUsuario());
			List<VUsuario>  responseconsultarUsuario = usuarioRxDao.consultarUsuario(requestConsultarUsuario);
			
			if(responseconsultarUsuario != null && responseconsultarUsuario.size() > 0) {
				throw new  DaoException(messageProvider.codigoErrorIdf6, messageProvider.mensajeErrorIdf6);
			}
			//2. Si no existe- creamos el usuario
			
			String claveNueva = utilitario.encriptarString(utilitario.generarString());
			
			Usuario usuario = new Usuario();
			Integer idUsuario = usuarioTxDao.obtenerId(ConfigDao.sequeciaTablaUsuario);
			usuario.setEstadoId(ConstantesConfig.activo);
			usuario.setUsuarioCreador(request.getAuditRequest().getUsuario());
			usuario.setFechaCreacion(LocalDateTime.now());
			usuario.setTerminalCreacion(request.getAuditRequest().getTerminal());
			String codigoUsuario = ConfigBusiness.codigoUsuario + idUsuario;
			codigoUsuario = ConfigBusiness.caracterUsuario + codigoUsuario.substring(3);
			usuario.setCodigo(codigoUsuario);
			usuario.setUsuario(request.getObjectRequest().getUsuario());
			usuario.setClave(claveNueva);
			usuario.setEmail(request.getObjectRequest().getEmailUsuario());
			usuario.setAplicacionId(ConstantesConfig.aplicacionId);
			
			Usuario responseCrear = usuarioTxDao.crear(usuario);
			
			//4. Registramos el detalle del usuario
			UsuarioDetalle usuarioDetalleRequest = new UsuarioDetalle();
			usuarioDetalleRequest.setEstadoId(ConstantesConfig.activo);
			usuarioDetalleRequest.setUsuarioCreador(request.getAuditRequest().getUsuario());
			usuarioDetalleRequest.setFechaCreacion(LocalDateTime.now());
			usuarioDetalleRequest.setTerminalCreacion(request.getAuditRequest().getTerminal());
			usuarioDetalleRequest.setNombre(request.getObjectRequest().getNombre());
			usuarioDetalleRequest.setApellido(request.getObjectRequest().getApellido());
			usuarioDetalleRequest.setUsuarioId(responseCrear.getId());
			usuarioDetalleRequest.setImagen(request.getObjectRequest().getImagenUsuario());
			request.getObjectRequest().setUsuarioId(responseCrear.getId());
			request.getObjectRequest().setCodigoUsuario(codigoUsuario);
			
			usuarioDetalleTxDao.crear(usuarioDetalleRequest);
			
			//5. Registramos los roles del usuario
			if(request.getObjectRequest().getRol() != null) {
				for(Integer rol : request.getObjectRequest().getRol()) {
					UsuarioRol usuarioRolInput = new UsuarioRol();
					usuarioRolInput.setEstadoId(ConstantesConfig.activo);
					usuarioRolInput.setFechaCreacion(LocalDateTime.now());
					usuarioRolInput.setTerminalCreacion(request.getAuditRequest().getTerminal());
					usuarioRolInput.setUsuarioCreador(request.getAuditRequest().getUsuario());
					usuarioRolInput.setRolId(rol);
					usuarioRolInput.setUsuarioId(responseCrear.getId());
					usuarioRolTxDao.crear(usuarioRolInput);
				}
			}
			//5. Enviamos el correo de creacion de usuario
			RequestGenericDto<EmailRequestClientDto> requestEnvioCorreo = new RequestGenericDto<>();
			requestEnvioCorreo.getAuditRequest().setTransaccionId(request.getAuditRequest().getTransaccionId());
			requestEnvioCorreo.getAuditRequest().setAplicacion(request.getAuditRequest().getAplicacion());
			requestEnvioCorreo.getAuditRequest().setTerminal(request.getAuditRequest().getTerminal());
			requestEnvioCorreo.getAuditRequest().setUsuario(request.getAuditRequest().getUsuario());
			requestEnvioCorreo.getAuditRequest().setSession(request.getAuditRequest().getSession());
			
			EmailRequestClientDto emailRequest = new EmailRequestClientDto();
			emailRequest.setCorreoDestino(request.getObjectRequest().getEmailUsuario());
			emailRequest.setAsunto("Registro de Usuario");
			String		html	= fileToString(SendHtmlEmail.class.getResourceAsStream(ConstantesConfig.plantillaRegistroUsuario), "utf-8");
			emailRequest.setCuerpo(html.replace("{0}", request.getObjectRequest().getUsuario()).replace("{1}", claveNueva));
			requestEnvioCorreo.setObjectRequest(emailRequest);
			AuditResponseDto responseEnviarCorreo = emailClient.enviarCorreo(requestEnvioCorreo);
			if(responseEnviarCorreo.getCodigoRespuesta() ==  messageProvider.codigoExito) {
				response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
				response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
				response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
				response.setObjectResponse(request.getObjectRequest());
			} else {
				response.getAuditResponse().setCodigoRespuesta(responseEnviarCorreo.getCodigoRespuesta());
				response.getAuditResponse().setMensajeRespuesta(responseEnviarCorreo.getMensajeRespuesta());
				response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			}
	
		} catch (DaoException e) {
			throw new  BusinessException(e.getCodigo(), e.getMensaje());
		} catch (Exception e) {
			throw new  BusinessException(messageProvider.codigoErrorIdt2, MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		
		return response;
	}

	private String fileToString(InputStream input, String encoding) throws Exception {
		StringWriter	  sw = new StringWriter();
		InputStreamReader in = new InputStreamReader(input, encoding);
 
		char[]	buffer = new char[1024 * 2];
		int		n	   = 0;
		while (-1 != (n = in.read(buffer))) {
			sw.write(buffer, 0, n);
		}    		
		return sw.toString();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditResponseDto eliminarUsuario(RequestGenericDto<EliminarObjetoDto> request) throws BusinessException {
		
		AuditResponseDto response = null;	
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTransaccionId());
			for(Integer itemId : request.getObjectRequest().getItems()){
				//1. Verificamos si existe el usuario
				Usuario responseBuscarId = usuarioTxDao.buscarId(itemId);
				if(responseBuscarId != null) {
					responseBuscarId.setEstadoId(ConstantesConfig.eliminado);
					responseBuscarId.setFechaCreacion(LocalDateTime.now());
					responseBuscarId.setTerminalModificador(request.getAuditRequest().getTerminal());
					responseBuscarId.setTransaccionId(request.getAuditRequest().getTransaccionId());
					responseBuscarId.setUsuarioModificador(request.getAuditRequest().getUsuario());
					//2. Eliminamos el usuario
					usuarioTxDao.eliminar(responseBuscarId);
					
					//3. Eliminamos el detalle del usuario
					RequestGenericEliminarDto requestEliminarDetalle = new RequestGenericEliminarDto(); 
					requestEliminarDetalle.setAuditRequest(request.getAuditRequest());
					requestEliminarDetalle.getId().add(responseBuscarId.getId());
					usuarioDetalleTxDao.eliminarUsuarioDetallexIdUsuario(requestEliminarDetalle);
					
					//4. Eliminamos el rol del usuario
					RequestGenericEliminarDto requestEliminarUsuarioRol = new RequestGenericEliminarDto(); 
					requestEliminarUsuarioRol.setAuditRequest(request.getAuditRequest());
					requestEliminarUsuarioRol.getId().add(responseBuscarId.getId());
					usuarioRolTxDao.eliminarUsuarioRolxIdUsuario(requestEliminarUsuarioRol);
				}
			}
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		} catch (DaoException e) {
			throw new  BusinessException(e.getCodigo(), e.getMensaje());
		} catch (Exception e) {
			throw new  BusinessException(messageProvider.codigoErrorIdt2, MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		
		return response;
	}

	@Override
	public AuditResponseDto actualizarUsuario(RequestGenericDto<UsuarioActualizacionDto> request) throws BusinessException {
		
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTransaccionId());
			if(request.getObjectRequest().getDatosUsuario() != null) {
				//1. Buscamos al usuario por el id
				Usuario responseBuscarId = usuarioTxDao.buscarId(request.getObjectRequest().getDatosUsuario().getUsuarioId());
				if(responseBuscarId != null) {
					responseBuscarId.setUsuarioModificador(request.getAuditRequest().getUsuario());
					responseBuscarId.setFechaModificacion(LocalDateTime.now());
					responseBuscarId.setTerminalModificador(request.getAuditRequest().getTerminal());
					responseBuscarId.setUsuario(request.getObjectRequest().getDatosUsuario().getUsuario());
					responseBuscarId.setEmail(request.getObjectRequest().getDatosUsuario().getEmailUsuario());
					usuarioTxDao.actualizar(responseBuscarId);
				}
				
				//2. consultamos el detalle
				UsuarioDetalleFiltroDto usuarioDetalleInput = new UsuarioDetalleFiltroDto();
				usuarioDetalleInput.setEstadoId(ConstantesConfig.activo);
				usuarioDetalleInput.setUsuarioId(request.getObjectRequest().getDatosUsuario().getUsuarioId());
				UsuarioDetalle consultarUsuarioDetalleResponse = usuarioDetalleRxDao.consultarUsuarioDetalle(usuarioDetalleInput);
				//3. Actualizamos el detalle
				if(consultarUsuarioDetalleResponse != null) {
					consultarUsuarioDetalleResponse.setFechaModificacion(LocalDateTime.now());
					consultarUsuarioDetalleResponse.setTerminalModificador(request.getAuditRequest().getTerminal());
					consultarUsuarioDetalleResponse.setUsuarioModificador(request.getAuditRequest().getUsuario());
					consultarUsuarioDetalleResponse.setNombre(request.getObjectRequest().getDatosUsuario().getNombre());
					consultarUsuarioDetalleResponse.setApellido(request.getObjectRequest().getDatosUsuario().getApellido());
					consultarUsuarioDetalleResponse.setImagen(request.getObjectRequest().getDatosUsuario().getImagenUsuario());
					usuarioDetalleTxDao.actualizar(consultarUsuarioDetalleResponse);
				}
			}
			
			if(request.getObjectRequest().getRol()!= null) {
				//4. eliminamos el usuarioRol
				RequestGenericEliminarDto usuarioRolInput = new RequestGenericEliminarDto();
				usuarioRolInput.setAuditRequest(request.getAuditRequest());
				usuarioRolInput.getId().add(request.getObjectRequest().getDatosUsuario().getUsuarioId());
				usuarioRolTxDao.eliminarUsuarioRolxIdUsuario(usuarioRolInput);
				
				//5. registramos el rol
				for(Integer rol : request.getObjectRequest().getRol()) {
					UsuarioRol UsuarioRolInput = new UsuarioRol();
					UsuarioRolInput.setEstadoId(ConstantesConfig.activo);
					UsuarioRolInput.setFechaCreacion(LocalDateTime.now());
					UsuarioRolInput.setTerminalCreacion(request.getAuditRequest().getTerminal());
					UsuarioRolInput.setUsuarioCreador(request.getAuditRequest().getUsuario());
					UsuarioRolInput.setRolId(rol);
					UsuarioRolInput.setUsuarioId(request.getObjectRequest().getDatosUsuario().getUsuarioId());
					usuarioRolTxDao.crear(UsuarioRolInput);
				}
				
			}
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		}catch (DaoException e) {
			throw new  BusinessException(e.getCodigo(), e.getMensaje());
		} catch (Exception e) {
			throw new  BusinessException(messageProvider.codigoErrorIdt2, MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		
		return response;
	}	 
}
