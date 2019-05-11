/**
 * 
 */
package com.subciber.seguridad.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.RepositorioJwt;
import com.subciber.seguridad.dto.EstructuraTokensDto;
import com.subciber.seguridad.dto.FiltroTokensDto;
import com.subciber.seguridad.dto.InfoJwt;
import com.subciber.seguridad.dto.UsuariosLoginDto;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.GeneralException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.ConstantesConfig;
import com.subciber.seguridad.util.EncriptacionAES;
import com.subciber.seguridad.util.Utilitario;

/**
 * @description implementacion de la interface RepositorioJwt
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */

@Startup
@Singleton
public class RepositorioJwtImpl implements RepositorioJwt, Serializable {

	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	@Inject
	private Utilitario utilitario;
	@Inject
	private MessageProvider messageProvider;
	@Inject
	private EncriptacionAES encriptacionAES;

	private Map<String, InfoJwt> logueadaos; 

	@PostConstruct
	public void afterCreate() {
		logueadaos = new HashMap<String, InfoJwt>();
	}

	@Override
	public void registrarUsuario(String session, InfoJwt informacion) throws BusinessException {

		logueadaos.put(session, informacion);

	}

	@Override
	public InfoJwt recuperarUsuario(String tokens) throws BusinessException {
		InfoJwt user = null;
		try {
			String[] parts = tokens.split(";");
			String session = parts[0];
			user = logueadaos.get(session);

			if (user == null) {
				throw new BusinessException(messageProvider.codigoErrorIdf2,
						MessageFormat.format(messageProvider.mensajeErrorIdf2, "Usuario logeado"));
			}
		} catch (BusinessException e) {
			throw new BusinessException(e.getCodigo(), e.getMensaje());
		} catch (Exception e) {
			throw new BusinessException(messageProvider.codigoErrorIdt2,
					MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}
		return user;
	}

	@Override
	public UsuariosLoginDto usuarioLogueados() throws BusinessException {

		UsuariosLoginDto response = null;
		List<InfoJwt> logueados = new ArrayList<>();
		try {
			response = new UsuariosLoginDto();
			Iterator it = logueadaos.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				logueados.add(logueadaos.get(key));
			}

			if (logueados.size() == 0) {
				throw new BusinessException(messageProvider.codigoErrorIdf2,
						MessageFormat.format(messageProvider.mensajeErrorIdf2, "Usuarios logeados"));
			}

			response.setUsuariosLogueados(logueados);
			response.setTotalLogueados(logueados.size());

		} catch (BusinessException e) {
			throw new BusinessException(e.getCodigo(), e.getMensaje());
		} catch (Exception e) {
			throw new BusinessException(messageProvider.codigoErrorIdt2,
					MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return response;
	}

	@Override
	public ResponseGenericDto<EstructuraTokensDto> validarTokens(RequestGenericDto<FiltroTokensDto> tokens) throws BusinessException {

		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<EstructuraTokensDto> response = null;
		try {
			response = new ResponseGenericDto<EstructuraTokensDto>();
			response.getAuditResponse().setTransaccionId(tokens.getAuditRequest().getTransaccionId());
			
			if (utilitario.isNullOrEmpty(tokens.getObjectRequest().getTokens())) {
				throw new GeneralException(messageProvider.codigoErrorIdf5,
						MessageFormat.format(messageProvider.mensajeErrorIdf5, "No existe el token"));
			}
			// 1. descomponemos el token
			String[] parts 							= tokens.getObjectRequest().getTokens().split(";");
			String session 							= parts[0]; // Session
			String datosUsuario 					= parts[1]; // datos de expiracion y datos de usuario
			String datosToken 						= encriptacionAES.decrypt(datosUsuario, ConstantesConfig.claveEncripacionAES);
			String[] datos 							= datosToken.split(";");
			String fechaCreacion					= datos[3];
			String fechaExpiracion					= datos[4];
			LocalDateTime fechaActual 				= LocalDateTime.now();
			LocalDateTime fechaSessionExpiracion 	= LocalDateTime.parse(datos[4]);
			String usuarioId 						= datos[0];
			String usuario	 						= datos[1];
			String email 							= datos[2];
			String codigodeaccesos					= "";
			String rol								= "";
			try {
				codigodeaccesos 					= datos[5];
			}catch(Exception e){
				codigodeaccesos						= "";
			}
			try {
				rol									= datos[6];
			}catch(Exception e){
				rol									= "";
			}
			// 2. validamos si el token se encuentra vencido o falta configuración de
			// accesos
			if (fechaActual.compareTo(fechaSessionExpiracion) > 0) {
				throw new BusinessException(messageProvider.codigoErrorIdf5,
						MessageFormat.format(messageProvider.mensajeErrorIdf5, "Token Vencido"));
			}
//			try {
//				String acceso = datos[5];
//				if (utilitario.isNullOrEmpty(acceso)) {
//					throw new BusinessException(messageProvider.codigoErrorIdf5,
//							MessageFormat.format(messageProvider.mensajeErrorIdf5, "Accesos no configurados"));
//				}
//			} catch (Exception e) {
//				throw new BusinessException(messageProvider.codigoErrorIdf5,
//						MessageFormat.format(messageProvider.mensajeErrorIdf5, "Accesos no configurados"));
//			}
			
			//3. Validamos que la session aun siga activa
			recuperarUsuario(session);
			
			//4. Generamos el nuevo tokens 
			String nuevoToken  = generarToken(session, Integer.parseInt(usuarioId), usuario, email, codigodeaccesos, rol);
			
			EstructuraTokensDto estructura = new EstructuraTokensDto();
			estructura.setCodigodeaccesos(codigodeaccesos);
			estructura.setEmail(email);
			estructura.setFechaCreacion(fechaCreacion);
			estructura.setFechaExpiracion(fechaExpiracion);
			estructura.setSession(session);
			estructura.setTokens(tokens.getObjectRequest().getTokens());
			estructura.setNuevoTokens(nuevoToken);
			estructura.setUsuario(usuario);
			estructura.setUsuarioId(Integer.parseInt(usuarioId));
			estructura.setRol(rol);
			
			response.setObjectResponse(estructura);
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
		} catch (BusinessException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo,
					e.getStackTrace()[0].getLineNumber(), "Estructura tokens incorrecta"));
		}

		return response;
	}
	
	public String generarToken(String session, Integer usuarioId, String usuario, String email, String codigodeaccesos, String rol) {
		
		String nuevoToken = "";
		try {
				//1.Generamos el token con los accesos encripatados
				LocalDateTime actual = LocalDateTime.now();
				LocalDateTime actualSuma = actual.plusMinutes(ConstantesConfig.timeoutSession);
				StringBuilder sessionId = new StringBuilder();
				sessionId.append(session);
				//Estructura Token
				//sessionId;IdUsuario;usuario,email;fechacreacion;fechaexpiracion;codigodeaccesos;rol
				StringBuilder accesos = new StringBuilder();
				accesos.append(usuarioId);
				accesos.append(";");
				accesos.append(usuario);
				accesos.append(";");
				accesos.append(email);
				accesos.append(";");
				accesos.append(actual.toString());
				accesos.append(";");
				accesos.append(actualSuma.toString());
				accesos.append(";");
				accesos.append(codigodeaccesos); 
				accesos.append(";");
				accesos.append(rol); 
				String tokenEncriptado = EncriptacionAES.encrypt(accesos.toString(), ConstantesConfig.claveEncripacionAES);
				StringBuilder token = new StringBuilder();
				token.append(sessionId.toString());
				token.append(";");
				token.append(tokenEncriptado); 
				
				nuevoToken = token.toString();  
				
				//Actualizamos la fecha de vencimiento del token
				try {
					InfoJwt user = logueadaos.get(session);
					user.setFechaInicioSession(actual.toString());
					user.setFechaFinSession(actualSuma.toString());
					registrarUsuario(session, user);
					
				}catch(Exception e){
					
				}
				
			}catch(Exception e) {
				nuevoToken = "";
			}
		return nuevoToken;
	}

	@Override
	public void depurarSesiones() {
		try {
			Iterator it = logueadaos.keySet().iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				InfoJwt session =  logueadaos.get(key);
				LocalDateTime fechaActual 				= LocalDateTime.now();
				LocalDateTime fechaSessionExpiracion 	= LocalDateTime.parse(session.getFechaFinSession());
				if (fechaActual.compareTo(fechaSessionExpiracion) > 0) {
					logueadaos.remove(session.getSession());
				}
			}
		}catch(Exception e) {
			System.out.println("Error al Depurar Sessiones : " + e );
		}
	}

	@Override
	public AuditResponseDto eliminarSession(RequestGenericDto<FiltroTokensDto> tokens) throws BusinessException {
		
		AuditResponseDto response = null;
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(tokens.getAuditRequest().getTransaccionId());
			String[] parts 	= tokens.getObjectRequest().getTokens().split(";");
			logueadaos.remove(parts[0]);
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		} catch (Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo,
					e.getStackTrace()[0].getLineNumber(), "No se pudo eliminar el Token"));
		}

		return response;
	}

}
