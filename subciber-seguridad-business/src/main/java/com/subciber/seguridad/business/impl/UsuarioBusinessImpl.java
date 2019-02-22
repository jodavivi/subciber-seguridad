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
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.UsuarioBusiness;
import com.subciber.seguridad.business.dto.UsuarioDetalleDto;
import com.subciber.seguridad.business.util.ConfigBusiness;
import com.subciber.seguridad.client.api.EmailClient;
import com.subciber.seguridad.client.dto.EmailRequestClientDto;
import com.subciber.seguridad.dao.api.UsuarioDetalleTxDao;
import com.subciber.seguridad.dao.api.UsuarioRxDao;
import com.subciber.seguridad.dao.api.UsuarioTxDao;
import com.subciber.seguridad.dao.util.ConfigDao;
import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.entity.Usuario;
import com.subciber.seguridad.entity.UsuarioDetalle;
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
	private UsuarioTxDao usuarioDao;
	@EJB
	private UsuarioRxDao usuarioRxDao;
	@EJB
	private UsuarioDetalleTxDao usuarioDetalleTxDao;
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
			Integer idUsuario = usuarioDao.obtenerId(ConfigDao.sequeciaTablaUsuario);
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
			
			Usuario responseCrear = usuarioDao.crear(usuario);
			
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
}
