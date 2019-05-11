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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.EliminarObjetoDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
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
import com.subciber.seguridad.dto.ClaveUsuarioDto;
import com.subciber.seguridad.dto.RecuperarCuentaDto;
import com.subciber.seguridad.dto.UsuarioActualizacionDto;
import com.subciber.seguridad.dto.UsuarioDetalleDto;
import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.entity.Usuario;
import com.subciber.seguridad.entity.UsuarioDetalle;
import com.subciber.seguridad.entity.UsuarioRol;
import com.subciber.seguridad.entity.VUsuario;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.ConstantesConfig;
import com.subciber.seguridad.util.JAXBUtil;
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

	static final Logger logger = LoggerFactory.getLogger(UsuarioBusinessImpl.class);
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
	long timeStart = 0;
	String transactionId = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<UsuarioDetalleDto> crearUsuario(RequestGenericDto<UsuarioDetalleDto> request)
			throws BusinessException {
		
		ResponseGenericDto<UsuarioDetalleDto> response = null;
		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			
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
			
			String claveGenerada = utilitario.generarString();
			String claveNueva = utilitario.encriptarString(claveGenerada);
			
			Usuario usuario = new Usuario();
			Integer idUsuario = usuarioTxDao.obtenerId(ConfigDao.sequeciaTablaUsuario);
			usuario.setId(idUsuario);
			usuario.setEstadoId(ConstantesConfig.activo);
			usuario.setUsuarioCreador(request.getAuditRequest().getUsuario());
			usuario.setFechaCreacion(LocalDateTime.now());
			usuario.setTerminalCreacion(request.getAuditRequest().getTerminal());
			String codigoUsuario = ConfigBusiness.codigoUsuario + idUsuario;
			codigoUsuario = ConfigBusiness.caracterUsuario + codigoUsuario.substring(3);
			usuario.setCodigo(codigoUsuario);
			usuario.setUsuario(request.getObjectRequest().getUsuario());
			usuario.setClave(claveNueva);
			usuario.setEmail(request.getObjectRequest().getEmail());
			usuario.setAplicacionId(ConstantesConfig.aplicacionId);
			usuario.setCambiarClave(0);
			
			Usuario responseCrear = usuarioTxDao.crear(usuario);
			
			//4. Registramos el detalle del usuario
			UsuarioDetalle usuarioDetalleRequest = new UsuarioDetalle();
			usuarioDetalleRequest.setEstadoId(ConstantesConfig.activo);
			usuarioDetalleRequest.setUsuarioCreador(request.getAuditRequest().getUsuario());
			usuarioDetalleRequest.setFechaCreacion(LocalDateTime.now());
			usuarioDetalleRequest.setTerminalCreacion(request.getAuditRequest().getTerminal());
			usuarioDetalleRequest.setNombre(request.getObjectRequest().getNombre());
			usuarioDetalleRequest.setApellido(request.getObjectRequest().getApellido());
			usuarioDetalleRequest.setUsuarioId(idUsuario);
			usuarioDetalleRequest.setImagen(request.getObjectRequest().getImagen());
			usuarioDetalleRequest.setAfiliadoId(request.getObjectRequest().getAfiliadoId());
			request.getObjectRequest().setUsuarioId(responseCrear.getId());
			request.getObjectRequest().setCodigo(codigoUsuario);
			
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
			emailRequest.setCorreoDestino(request.getObjectRequest().getEmail());
			emailRequest.setAsunto("Registro de Usuario");
			String		html	= fileToString(SendHtmlEmail.class.getResourceAsStream(ConstantesConfig.plantillaRegistroUsuario), "utf-8");
			emailRequest.setCuerpo(html.replace("{0}", request.getObjectRequest().getUsuario()).replace("{1}", claveGenerada));
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
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
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
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTransaccionId());
			for(Integer itemId : request.getObjectRequest().getItems()){ 
					//1. Eliminamos el usuario
					RequestGenericDto<Integer> requestEliminar = new RequestGenericDto<>();	
					requestEliminar.setAuditRequest(request.getAuditRequest());
					requestEliminar.setObjectRequest(itemId);
					usuarioTxDao.eliminarUsuarioxId(requestEliminar);
					
					//3. Eliminamos el detalle del usuario
					RequestGenericDto<Integer> requestEliminarDetalle = new RequestGenericDto<Integer>(); 
					requestEliminarDetalle.setAuditRequest(request.getAuditRequest());
					requestEliminarDetalle.setObjectRequest(itemId);
					usuarioDetalleTxDao.eliminarUsuarioDetallexIdUsuario(requestEliminarDetalle);
					
					//4. Eliminamos el rol del usuario
					RequestGenericDto<Integer> requestEliminarUsuarioRol = new RequestGenericDto<Integer>(); 
					requestEliminarUsuarioRol.setAuditRequest(request.getAuditRequest());
					requestEliminarUsuarioRol.setObjectRequest(itemId);
					usuarioRolTxDao.eliminarUsuarioRolxIdUsuario(requestEliminarUsuarioRol);
			}
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		} catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));

		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		
		return response;
	}

	@Override
	public AuditResponseDto actualizarUsuario(RequestGenericDto<UsuarioActualizacionDto> request) throws BusinessException { 
 
		AuditResponseDto response = null;	
		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTransaccionId());
			if(request.getObjectRequest().getDatosUsuario() != null) {
				//1. Buscamos al usuario por el id
				UsuarioFiltroDto  buscarUsuario = new UsuarioFiltroDto();
				buscarUsuario.setUsuarioId(request.getObjectRequest().getDatosUsuario().getUsuarioId());
				List<VUsuario> responseBuscarUsuario = usuarioRxDao.buscarUsuario(buscarUsuario);
				if(responseBuscarUsuario != null && responseBuscarUsuario.size() == 1) {
					
					//2. Actualizamos la tabla usuario
					Usuario responseUsuario = usuarioTxDao.buscarId(responseBuscarUsuario.get(0).getUsuarioId());
					responseUsuario.setFechaModificacion(LocalDateTime.now());
					responseUsuario.setTerminalModificador(request.getAuditRequest().getTerminal()); 
					responseUsuario.setEstadoId(request.getObjectRequest().getDatosUsuario().getEstadoId());
					if(request.getObjectRequest().getDatosUsuario().getUsuario() != null) {
						responseUsuario.setUsuario(request.getObjectRequest().getDatosUsuario().getUsuario());
					}
					if(request.getObjectRequest().getDatosUsuario().getEmail() != null) {
						responseUsuario.setEmail(request.getObjectRequest().getDatosUsuario().getEmail());
					} 
					usuarioTxDao.actualizar(responseUsuario);
					
					//3. Actualizamos el detalle
					if(responseBuscarUsuario.get(0).getUsuarioDetalleId() != null) {
						UsuarioDetalle consultarUsuarioDetalleResponse = usuarioDetalleTxDao.buscarId(responseBuscarUsuario.get(0).getUsuarioDetalleId());
						consultarUsuarioDetalleResponse.setFechaModificacion(LocalDateTime.now());
						consultarUsuarioDetalleResponse.setTerminalModificador(request.getAuditRequest().getTerminal());
						consultarUsuarioDetalleResponse.setUsuarioModificador(request.getAuditRequest().getUsuario());
						consultarUsuarioDetalleResponse.setEstadoId(request.getObjectRequest().getDatosUsuario().getEstadoId());
						if(request.getObjectRequest().getDatosUsuario().getNombre() != null) {
							consultarUsuarioDetalleResponse.setNombre(request.getObjectRequest().getDatosUsuario().getNombre());
						}
						if(request.getObjectRequest().getDatosUsuario().getApellido()!= null) {
							consultarUsuarioDetalleResponse.setApellido(request.getObjectRequest().getDatosUsuario().getApellido());
						}
						
						if(request.getObjectRequest().getDatosUsuario().getImagen()!= null) {
							consultarUsuarioDetalleResponse.setImagen(request.getObjectRequest().getDatosUsuario().getImagen());
						}
						
						usuarioDetalleTxDao.actualizar(consultarUsuarioDetalleResponse);
					}else {
						//4. Creamos el detalle
						UsuarioDetalle usuarioDetalleRequest = new  UsuarioDetalle();
						usuarioDetalleRequest.setFechaCreacion(LocalDateTime.now());
						usuarioDetalleRequest.setTerminalCreacion(request.getAuditRequest().getTerminal());
						usuarioDetalleRequest.setUsuarioCreador(request.getAuditRequest().getUsuario());
						usuarioDetalleRequest.setEstadoId(request.getObjectRequest().getDatosUsuario().getEstadoId());
						if(request.getObjectRequest().getDatosUsuario().getNombre() != null) {
							usuarioDetalleRequest.setNombre(request.getObjectRequest().getDatosUsuario().getNombre());
						}
						if(request.getObjectRequest().getDatosUsuario().getApellido() != null) {
							usuarioDetalleRequest.setApellido(request.getObjectRequest().getDatosUsuario().getApellido());
						}
						if(request.getObjectRequest().getDatosUsuario().getImagen() != null) {
							usuarioDetalleRequest.setImagen(request.getObjectRequest().getDatosUsuario().getImagen());
						}
						usuarioDetalleRequest.setUsuarioId(responseBuscarUsuario.get(0).getUsuarioId());
						usuarioDetalleTxDao.crear(usuarioDetalleRequest);
					}
					
				}
				
			}
			
			if(request.getObjectRequest().getRol()!= null) {
				//4. eliminamos el usuarioRol
				RequestGenericDto<Integer> usuarioRolInput = new RequestGenericDto<Integer>();
				usuarioRolInput.setAuditRequest(request.getAuditRequest());
				usuarioRolInput.setObjectRequest(request.getObjectRequest().getDatosUsuario().getUsuarioId());
				usuarioRolTxDao.eliminarUsuarioRolxIdUsuario(usuarioRolInput);
				
				//5. registramos el rol
				for(Integer rol : request.getObjectRequest().getRol()) {
					UsuarioRol UsuarioRolInput = new UsuarioRol();
					UsuarioRolInput.setId(usuarioRolTxDao.obtenerId(ConfigDao.sequeciaTablaUsuarioRol));
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
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch(Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		
		return response;
	}

	@Override
	public AuditResponseDto actualizarClaveUsuario(RequestGenericDto<ClaveUsuarioDto> request)
			throws BusinessException {
		 
		AuditResponseDto response = null;	
		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTransaccionId());
			
			if(!request.getObjectRequest().getNuevaClave().equals(request.getObjectRequest().getRepetirNuevaClave())){
				throw new DaoException(messageProvider.codigoErrorIdf8, messageProvider.mensajeErrorIdf8);
			}
			if(request.getObjectRequest().getNuevaClave().length() < 6){
				throw new DaoException(messageProvider.codigoErrorIdf9, messageProvider.mensajeErrorIdf9);
			}
			
			request.getObjectRequest().setUsuarioId(request.getAuditRequest().getUsuarioId());
			String claveGenerada = request.getObjectRequest().getNuevaClave();
			String claveNueva = utilitario.encriptarString(claveGenerada);
			request.getObjectRequest().setNuevaClave(claveNueva);
			AuditResponseDto actualizarClaveUsuarioResponse = usuarioTxDao.actualizarClaveUsuario(request);
			if(actualizarClaveUsuarioResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
				throw new DaoException(actualizarClaveUsuarioResponse.getCodigoRespuesta(), actualizarClaveUsuarioResponse.getMensajeRespuesta());
			}
			
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		} catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));

		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		
		return response;
	}

	@Override
	public AuditResponseDto restaurarCuenta(RequestGenericDto<RecuperarCuentaDto> request) throws BusinessException {
	 
		AuditResponseDto  response = null;
		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTransaccionId());
			//1. Verificamos si existe el usuario registrado
			UsuarioFiltroDto requestConsultarUsuario = new UsuarioFiltroDto(); 
			requestConsultarUsuario.setEstadoId(ConstantesConfig.activo);
			requestConsultarUsuario.setUsuario(request.getObjectRequest().getUsuario());
			List<VUsuario>  responseconsultarUsuario = usuarioRxDao.consultarUsuario(requestConsultarUsuario);
			
			if(responseconsultarUsuario == null || responseconsultarUsuario.size() == 0) {
				throw new  DaoException(messageProvider.codigoErrorIdf7, messageProvider.mensajeErrorIdf7);
			}
			//2. Si existe creamos  un clave temporal
			LocalDateTime actual = LocalDateTime.now();
			LocalDateTime actualSuma = actual.plusMinutes(ConstantesConfig.timeoutRecuperar);
			StringBuilder claveTemporal = new StringBuilder();
			claveTemporal.append("R");
			claveTemporal.append("|");
			claveTemporal.append(responseconsultarUsuario.get(0).getUsuarioId());
			claveTemporal.append("|"); 
			claveTemporal.append(actualSuma.toString());
			 
			String claveNueva = utilitario.encriptarString(claveTemporal.toString());
			 
			//5. Enviamos el correo de recuperar de cuenta de usuario
			RequestGenericDto<EmailRequestClientDto> requestEnvioCorreo = new RequestGenericDto<>();
			requestEnvioCorreo.getAuditRequest().setTransaccionId(request.getAuditRequest().getTransaccionId());
			requestEnvioCorreo.getAuditRequest().setAplicacion(request.getAuditRequest().getAplicacion());
			requestEnvioCorreo.getAuditRequest().setTerminal(request.getAuditRequest().getTerminal());
			requestEnvioCorreo.getAuditRequest().setUsuario(request.getAuditRequest().getUsuario());
			requestEnvioCorreo.getAuditRequest().setSession(request.getAuditRequest().getSession());
			
			EmailRequestClientDto emailRequest = new EmailRequestClientDto();
			emailRequest.setCorreoDestino(responseconsultarUsuario.get(0).getEmail());
			emailRequest.setAsunto("Recuperar Cuenta de Usuario");
			String		html	= fileToString(SendHtmlEmail.class.getResourceAsStream(ConstantesConfig.plantillaRecuperarUsuario), "utf-8");
			emailRequest.setCuerpo(html.replace("{0}", responseconsultarUsuario.get(0).getUsuario()).replace("{1}", claveNueva));
			requestEnvioCorreo.setObjectRequest(emailRequest);
			AuditResponseDto responseEnviarCorreo = emailClient.enviarCorreo(requestEnvioCorreo);
			if(responseEnviarCorreo.getCodigoRespuesta() ==  messageProvider.codigoExito) { 
				response.setCodigoRespuesta(messageProvider.codigoExito);
				response.setMensajeRespuesta(messageProvider.mensajeExito);
			} else { 
				response.setCodigoRespuesta(responseEnviarCorreo.getCodigoRespuesta());
				response.setMensajeRespuesta(responseEnviarCorreo.getMensajeRespuesta());
			}
	
		} catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		
		return response;
	}
 
}
