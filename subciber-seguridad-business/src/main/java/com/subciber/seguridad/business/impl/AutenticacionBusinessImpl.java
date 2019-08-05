/**
 * 
 */
package com.subciber.seguridad.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.AutenticacionBusiness;
import com.subciber.seguridad.business.api.RepositorioJwt;
import com.subciber.seguridad.dao.api.AccesosRecursosUsuarioRxDao;
import com.subciber.seguridad.dao.api.AccesosUsuarioRxDao;
import com.subciber.seguridad.dao.api.AutenticacionRxDao;
import com.subciber.seguridad.dao.api.UsuarioRxDao;
import com.subciber.seguridad.dao.api.UsuarioTxDao;
import com.subciber.seguridad.dto.AccesoFiltroDto;
import com.subciber.seguridad.dto.AccesosDto;
import com.subciber.seguridad.dto.AutenticacionFiltroDto;
import com.subciber.seguridad.dto.InfoJwt;
import com.subciber.seguridad.dto.InfoUsuarioDto;
import com.subciber.seguridad.dto.UsuarioAplicacionDto;
import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.dto.UsuarioGrupoDto;
import com.subciber.seguridad.entity.Usuario;
import com.subciber.seguridad.entity.VAccesoComponente;
import com.subciber.seguridad.entity.VAccesoGrupoAplicacion;
import com.subciber.seguridad.entity.VUsuario;
import com.subciber.seguridad.entity.VUsuarioAutenticacion;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.ConstantesConfig;
import com.subciber.seguridad.util.JAXBUtil;
import com.subciber.seguridad.util.Utilitario;

/**
 * @description implementacion de la interface AutenticacionBusiness
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Dependent
public class AutenticacionBusinessImpl implements AutenticacionBusiness, Serializable {

	static final Logger logger = LoggerFactory.getLogger(AutenticacionBusinessImpl.class);
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
    private AutenticacionRxDao autenticacionDao;
	@EJB
    private UsuarioRxDao usuarioRxDao;
	@EJB
    private UsuarioTxDao usuarioTxDao;
	@EJB
	private AccesosUsuarioRxDao accesosUsuarioDao;
	@EJB
	private AccesosRecursosUsuarioRxDao accesosRecursosUsuarioDao;
	@Inject
	private Utilitario utilitario;
	@Inject
	private RepositorioJwt repositorioJwt;

	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	long timeStart = 0;
	String transactionId = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<InfoUsuarioDto> autenticarUsuario(RequestGenericDto<AutenticacionFiltroDto> request)
			throws BusinessException {
		
		ResponseGenericDto<InfoUsuarioDto> respuesta = null;

		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			
			Integer usuarioId  			= 0; 
			String clave 				= request.getObjectRequest().getClave();
			String convertClaveAes		= "";
			try {
				convertClaveAes = utilitario.encriptarString(clave);
				String claveDescriptada = utilitario.desencriptarString(request.getObjectRequest().getClave());
				String[] campos = claveDescriptada.split("\\|");
				
				LocalDateTime fechaActual 				= LocalDateTime.now();
				LocalDateTime fechaSessionExpiracion 	= LocalDateTime.parse(campos[2]);
				
				if(campos[0].equals("R")) {
					if (fechaActual.compareTo(fechaSessionExpiracion) > 0) {
						throw new BusinessException(messageProvider.codigoErrorIdf10,
								messageProvider.mensajeErrorIdf10);
					}else {
						convertClaveAes = claveDescriptada;
						usuarioId  = Integer.valueOf(campos[1]);
						Usuario user = usuarioTxDao.buscarId(usuarioId);
						user.setClave(claveDescriptada);
						user.setCambiarClave(0); 
						usuarioTxDao.actualizar(user); 
					}
				} 
			}catch(Exception e) {
				
			}
			 
			respuesta = new ResponseGenericDto<InfoUsuarioDto>();
			respuesta.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			
			StringBuilder accesosList = new StringBuilder();
			//1. Validamos el usuario y la clave
			AutenticacionFiltroDto filtroUsuario = new AutenticacionFiltroDto();
			filtroUsuario.setUsuario(request.getObjectRequest().getUsuario());
			filtroUsuario.setClave(convertClaveAes);
			VUsuarioAutenticacion usuarioAutenticar = autenticacionDao.autenticar(filtroUsuario);
			if(usuarioAutenticar == null) {
				throw new  BusinessException(messageProvider.codigoErrorIdf4, MessageFormat.format(messageProvider.mensajeErrorIdf4,"Usuario"));
			}
			
			//2. Obtenemos la informacion del usuario
			UsuarioFiltroDto consultarUsuarioRequest = new UsuarioFiltroDto();
			consultarUsuarioRequest.setUsuarioId(usuarioAutenticar.getId());
			consultarUsuarioRequest.setEstadoId(23);
			List<VUsuario> consultarUsuarioResponse = usuarioRxDao.consultarUsuario(consultarUsuarioRequest);
			
			if(consultarUsuarioResponse == null ||  consultarUsuarioResponse.size() > 1) {
				throw new  BusinessException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"Usuario"));
			}
			
			InfoUsuarioDto infoUsuarioDto = new InfoUsuarioDto(); 
			infoUsuarioDto.getUsuario().setUsuarioId(consultarUsuarioResponse.get(0).getUsuarioId());
			infoUsuarioDto.getUsuario().setUsuario(consultarUsuarioResponse.get(0).getUsuario());
			infoUsuarioDto.getUsuario().setEmail(consultarUsuarioResponse.get(0).getEmail());
			infoUsuarioDto.getUsuario().setApellido(consultarUsuarioResponse.get(0).getApellido());
			infoUsuarioDto.getUsuario().setCodigo(consultarUsuarioResponse.get(0).getCodigo());
			infoUsuarioDto.getUsuario().setNombre(consultarUsuarioResponse.get(0).getNombre());
			infoUsuarioDto.getUsuario().setImagen(consultarUsuarioResponse.get(0).getImagen());
			infoUsuarioDto.getUsuario().setEstadoId(usuarioAutenticar.getEstadoId());
			infoUsuarioDto.getUsuario().setCambiarClave(consultarUsuarioResponse.get(0).getCambiarClave());
			infoUsuarioDto.getUsuario().setAfiliadoId(consultarUsuarioResponse.get(0).getAfiliadoId());
			infoUsuarioDto.getUsuario().setCodigoAfiliado(consultarUsuarioResponse.get(0).getCodigoAfiliado());
			String[] rolesUsuario = usuarioAutenticar.getRol().split("\\|");
			List<Integer> listaRoles = new ArrayList<>();
			for(String rolUser : rolesUsuario) {
				listaRoles.add(Integer.valueOf(rolUser.trim())); 
			}
			infoUsuarioDto.getUsuario().setRol(listaRoles);
			
			//3. consultamos si tiene grupos y aplicaciones asignadas
			AccesoFiltroDto filtroAcceso = new AccesoFiltroDto();
			filtroAcceso.setUsuarioId(usuarioAutenticar.getId());
			List<VAccesoGrupoAplicacion>  listGrupoAplicaciones = accesosUsuarioDao.accesosGrupoAplicacionesUsuario(filtroAcceso);
			
			if(listGrupoAplicaciones == null) {
				throw new  BusinessException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"GrupoAplicaciones"));
			}
			
			ArrayList<Integer> listGruposDiferente = new ArrayList<Integer>();
			List<VAccesoGrupoAplicacion> listGrupo = new ArrayList<VAccesoGrupoAplicacion>();
			for(VAccesoGrupoAplicacion acceso : listGrupoAplicaciones) {
				if(!listGruposDiferente.contains(acceso.getGrupoId())) {
					listGruposDiferente.add(acceso.getGrupoId());
					listGrupo.add(acceso);
				}
			}
			
			for(VAccesoGrupoAplicacion grupo : listGrupo) {
				UsuarioGrupoDto grupoNuevo = new UsuarioGrupoDto();
				grupoNuevo.setCodigoGrupo(grupo.getGrupoId());
				grupoNuevo.setNombreGrupo(grupo.getGrupo());
				for(VAccesoGrupoAplicacion aplicacion : listGrupoAplicaciones) {
					if(Integer.valueOf(aplicacion.getGrupoId()) - Integer.valueOf(grupo.getGrupoId()) == 0) {
						UsuarioAplicacionDto app = new UsuarioAplicacionDto();
						app.setAplicacionId(aplicacion.getComponenteId());
						app.setNombre(aplicacion.getNombre());
						app.setDescripcion(aplicacion.getDescripcion());
						app.setUrl(aplicacion.getUrl());
						app.setIcono(aplicacion.getIcono());
						grupoNuevo.getAplicaciones().add(app);
					} 
				}
				infoUsuarioDto.getGrupoAplicaciones().add(grupoNuevo);
			}
			
			//3. Consultamos los recursos que tiene accesos el usuario
			AccesoFiltroDto filtroRecurso = new AccesoFiltroDto();
			filtroRecurso.setUsuarioId(usuarioAutenticar.getId());
			List<VAccesoComponente>  listaAccesos = accesosRecursosUsuarioDao.accesosRecursosUsuario(filtroRecurso);
			if(listaAccesos == null) {
				throw new  BusinessException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"GrupoAplicaciones"));
			}
			for(VAccesoComponente componente : listaAccesos) {
				accesosList.append(componente.getCodigoEncriptado());
				accesosList.append("__");
				AccesosDto acceso = new AccesosDto();
				acceso.setNombreControl(componente.getDescripcion());
				acceso.setCodigoControl(componente.getCodigoEncriptado());
				acceso.setUrlControl(componente.getUrl());
				infoUsuarioDto.getAccesosRecursos().add(acceso);
			}
			
			respuesta.setObjectResponse(infoUsuarioDto);
			
			LocalDateTime actual = LocalDateTime.now();
			LocalDateTime actualSuma = actual.plusMinutes(ConstantesConfig.timeoutSession);
			
			//5.Generamos el token con los accesos encripatados
 
			if(accesosList.length() > 0) {
				accesosList.setLength(accesosList.length() - 2);
			}
			
			String tokenGenerado = repositorioJwt.generarToken(request.getObjectRequest().getSession(), usuarioAutenticar.getId(), usuarioAutenticar.getCodigo(), usuarioAutenticar.getEmail(), accesosList.toString(), usuarioAutenticar.getRol());
			
			//4. Registramos la Session
			InfoJwt infoUsuario = new InfoJwt();
			infoUsuario.setSession(request.getObjectRequest().getSession());
			infoUsuario.setFechaInicioSession(actual.toString());
			infoUsuario.setFechaFinSession(actualSuma.toString());
			infoUsuario.setUsuario(request.getObjectRequest().getUsuario());
			infoUsuario.setTokens(tokenGenerado.toString());
			infoUsuario.setAccesosRecursos(infoUsuarioDto.getAccesosRecursos());
			repositorioJwt.registrarUsuario(request.getObjectRequest().getSession(),infoUsuario);
			
			//5. Activamos el depurador de sessiones
			if(ConstantesConfig.activarDepurador) {
				System.out.println("Depurador de Sessiones Activado");
				depuradorSesisiones();
				ConstantesConfig.activarDepurador = false;
			}
			
			respuesta.getObjectResponse().getUsuario().setTokenUsuario(tokenGenerado.toString());
			respuesta.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			respuesta.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			
		} catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			respuesta.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			respuesta.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch(Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			respuesta.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			respuesta.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(respuesta)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		return respuesta;
	}
	
	public void depuradorSesisiones() {
		try {
			Timer timer;
			timer = new Timer();	
			TimerTask task = new TimerTask() {
				@Override
				public void run() { 
					try { 
						repositorioJwt.depurarSesiones();
					}catch(Exception e) {
						System.out.println("Error al llamar la funcion de depurador de sessiones: " + e);
					}
				}
			};
			timer.schedule(task, 1000, 300000);
		}catch(Exception e) {
			System.out.println("Error en el timer de depurardor de sessiones: " + e);
		}
	}
	
}