/**
 * 
 */
package com.subciber.seguridad.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.AutenticacionBusiness;
import com.subciber.seguridad.business.api.RepositorioJwt;
import com.subciber.seguridad.business.dto.AccesosDto;
import com.subciber.seguridad.business.dto.InfoJwt;
import com.subciber.seguridad.business.dto.InfoUsuarioDto;
import com.subciber.seguridad.dao.api.AccesosRecursosUsuarioRxDao;
import com.subciber.seguridad.dao.api.AccesosUsuarioRxDao;
import com.subciber.seguridad.dao.api.AutenticacionRxDao;
import com.subciber.seguridad.dao.api.UsuarioRxDao;
import com.subciber.seguridad.dto.AccesoFiltroDto;
import com.subciber.seguridad.dto.AutenticacionFiltroDto;
import com.subciber.seguridad.dto.UsuarioAplicacionDto;
import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.dto.UsuarioGrupoDto;
import com.subciber.seguridad.entity.VAccesoComponente;
import com.subciber.seguridad.entity.VAccesoGrupoAplicacion;
import com.subciber.seguridad.entity.VUsuario;
import com.subciber.seguridad.entity.VUsuarioAutenticacion;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.ConstantesConfig;
import com.subciber.seguridad.util.EncriptacionAES;
import com.subciber.seguridad.util.Utilitario;

/**
 * @description implementacion de la interface AutenticacionBusiness
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Dependent
public class AutenticacionBusinessImpl implements AutenticacionBusiness, Serializable {

	@Inject
    private MessageProvider messageProvider;
	@EJB
    private AutenticacionRxDao autenticacionDao;
	@EJB
    private UsuarioRxDao usuarioRxDao;
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<InfoUsuarioDto> autenticarUsuario(RequestGenericDto<AutenticacionFiltroDto> request)
			throws BusinessException {
		
		ResponseGenericDto<InfoUsuarioDto> respuesta = null;
		
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			String convertClaveAes = utilitario.encriptarString(request.getObjectRequest().getClave());
			respuesta = new ResponseGenericDto<InfoUsuarioDto>();
			StringBuilder accesosList = new StringBuilder();
			
			//1. Validamos el usuario y la clave
			AutenticacionFiltroDto filtroUsuario = new AutenticacionFiltroDto();
			filtroUsuario.setUsuario(request.getObjectRequest().getUsuario());
			filtroUsuario.setClave(convertClaveAes);
			VUsuarioAutenticacion usuarioAutenticar = autenticacionDao.autenticar(filtroUsuario);
			if(usuarioAutenticar == null) {
				throw new  BusinessException(messageProvider.codigoErrorIdf4, MessageFormat.format(messageProvider.mensajeErrorIdf4,"Usuario"));
			}
			
			//2. Obtenemos la informacion del usuairo
			UsuarioFiltroDto consultarUsuarioRequest = new UsuarioFiltroDto();
			consultarUsuarioRequest.setUsuarioId(usuarioAutenticar.getId());
			consultarUsuarioRequest.setEstadoId(23);
			List<VUsuario> consultarUsuarioResponse = usuarioRxDao.consultarUsuario(consultarUsuarioRequest);
			
			if(consultarUsuarioResponse == null ||  consultarUsuarioResponse.size() > 1) {
				throw new  BusinessException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"Usuario"));
			}
			
			InfoUsuarioDto infoUsuarioDto = new InfoUsuarioDto(); 
			infoUsuarioDto.getUsuario().setUsuario(consultarUsuarioResponse.get(0).getUsuario());
			infoUsuarioDto.getUsuario().setEmailUsuario(consultarUsuarioResponse.get(0).getEmail());
			infoUsuarioDto.getUsuario().setApellido(consultarUsuarioResponse.get(0).getApellido());
			infoUsuarioDto.getUsuario().setCodigoUsuario(consultarUsuarioResponse.get(0).getCodigo());
			infoUsuarioDto.getUsuario().setNombre(consultarUsuarioResponse.get(0).getNombre());
			infoUsuarioDto.getUsuario().setImagenUsuario(consultarUsuarioResponse.get(0).getImagen());
			
			//3. consultamos si tiene grupos y aplicaciones asignadas
			AccesoFiltroDto filtroAcceso = new AccesoFiltroDto();
			filtroAcceso.setUsuarioId(usuarioAutenticar.getId());
			List<VAccesoGrupoAplicacion>  listGrupoAplicaciones = accesosUsuarioDao.accesosGrupoAplicacionesUsuario(filtroAcceso);
			
			if(listGrupoAplicaciones == null) {
				throw new  BusinessException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2,"GrupoAplicaciones"));
			}
			
			ArrayList<String> listGrupos = new ArrayList<String>();
			for(VAccesoGrupoAplicacion acceso : listGrupoAplicaciones) {
				UsuarioGrupoDto grupo = new UsuarioGrupoDto();
				if(!listGrupos.contains(acceso.getGrupo())) {
					listGrupos.add(acceso.getGrupo());
					grupo.setCodigoGrupo(acceso.getGrupoId());
					grupo.setNombreGrupo(acceso.getGrupo());
					for(VAccesoGrupoAplicacion aplicacion : listGrupoAplicaciones) {
						if(aplicacion.getGrupo() == acceso.getGrupo()) {
							UsuarioAplicacionDto app = new UsuarioAplicacionDto();
							app.setAplicacionId(aplicacion.getAplicacionId());
							app.setAplicacion(aplicacion.getDescripcion());
							app.setUrl(aplicacion.getUrl());
							app.setIcono(aplicacion.getIcono());
							grupo.getAplicaciones().add(app);
						}
					}
					infoUsuarioDto.getGrupoAplicaciones().add(grupo);
				}
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
			
			//4. Registramos la Session
			LocalDateTime actual = LocalDateTime.now();
			LocalDateTime actualSuma = actual.plusMinutes(ConstantesConfig.timeoutSession);
			InfoJwt infoUsuario = new InfoJwt();
			infoUsuario.setSession(request.getObjectRequest().getSession());
			infoUsuario.setFechaInicioSession(actual);
			infoUsuario.setFechaFinSession(actualSuma);
			infoUsuario.setUsuario(request.getObjectRequest().getUsuario());
			infoUsuario.setAccesosRecursos(infoUsuarioDto.getAccesosRecursos());
			repositorioJwt.registrarUsuario(request.getObjectRequest().getSession(),infoUsuario);
			
			//5.Generamos el token con los accesos encripatados
			StringBuilder sessionId = new StringBuilder();
			sessionId.append(request.getObjectRequest().getSession());
			//Estructura Token
			//sessionId;IdUsuario;usuario,email;fechacreacion;fechaexpiracion;codigodeaccesos
			StringBuilder accesos = new StringBuilder();
			accesos.append(usuarioAutenticar.getId());
			accesos.append(";");
			accesos.append(usuarioAutenticar.getUsuario());
			accesos.append(";");
			accesos.append(usuarioAutenticar.getEmail());
			accesos.append(";");
			accesos.append(actual.toString());
			accesos.append(";");
			accesos.append(actualSuma.toString());
			accesos.append(";");
			accesos.append(accesosList);
			if(accesosList.length() > 0) {
				accesos.setLength(accesos.length() - 2);
			}
			String tokenEncriptado = EncriptacionAES.encrypt(accesos.toString(), ConstantesConfig.claveEncripacionAES);
			StringBuilder token = new StringBuilder();
			token.append(sessionId.toString());
			token.append(";");
			token.append(tokenEncriptado);
			respuesta.getObjectResponse().getUsuario().setTokenUsuario(token.toString());
			respuesta.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			respuesta.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
		
		} catch (DaoException e) {
			throw new  BusinessException(e.getCodigo(), e.getMensaje());
		} catch(Exception e) {
			throw new  BusinessException(messageProvider.codigoErrorIdt2, MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		return respuesta;
	}
}