/**
 * 
 */
package com.subciber.seguridad.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.UsuarioBusiness;
import com.subciber.seguridad.business.dto.UsuarioDetalleDto;
import com.subciber.seguridad.business.util.ConfigBusiness;
import com.subciber.seguridad.dao.api.UsuarioDao;
import com.subciber.seguridad.dao.util.ConfigDao;
import com.subciber.seguridad.entity.Usuario;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.ConstantesConfig;
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
	private UsuarioDao usuarioDao;
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
			Usuario usuario = new Usuario();
			Integer idUsuario = usuarioDao.obtenerId(ConfigDao.sequeciaTablaUsuario);
			usuario.setEstadoId(ConstantesConfig.activo);
			usuario.setUsuarioCreador(request.getAuditRequest().getUsuario());
			usuario.setFechaCreacion(LocalDateTime.now());
			usuario.setTerminalCreacion(request.getAuditRequest().getTerminal());
			String codigoUsuario = ConfigBusiness.codigoUsuario + idUsuario;
			usuario.setCodigo(ConfigBusiness.caracterUsuario + codigoUsuario.substring(3));
			usuario.setUsuario(request.getObjectRequest().getUsuario());
			usuario.setClave(request.getObjectRequest().getClave());
			usuario.setEmail(request.getObjectRequest().getEmailUsuario());
			usuario.setImagen(request.getObjectRequest().getImagenUsuario());
			usuario.setAplicacionId(ConstantesConfig.aplicacionId);
			Usuario responseCrear = usuarioDao.crear(usuario);
			
			UsuarioDetalleDto usuarioDto = new UsuarioDetalleDto();
			usuarioDto.setApellido(responseCrear.getUsuario());
			usuarioDto.setUsuarioId(responseCrear.getId());
			response.getAuditResponse().setCodigoRespuesta(1);
			response.getAuditResponse().setMensajeRespuesta("OK");
			response.getAuditResponse().setTransaccionId("123123123123");
			response.setObjectResponse(usuarioDto);
		} catch (DaoException e) {
			throw new  BusinessException(e.getCodigo(), e.getMensaje());
		} catch (Exception e) {
			throw new  BusinessException(messageProvider.codigoErrorIdt2, MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));
		}
		
		return response;
	}

}
