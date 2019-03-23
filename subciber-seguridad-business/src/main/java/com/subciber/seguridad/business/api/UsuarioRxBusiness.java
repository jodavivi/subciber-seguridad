package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.dto.ResponseUsuarioDetalleDto;
import com.subciber.seguridad.dao.dto.ResponseUsuarioDto;
import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para la consulta de usuarios
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
public interface UsuarioRxBusiness {

	/**
	 * @param metodo para consultar los usuarios
	 * @return devuelve informacion de los usuarios  
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<ResponseUsuarioDto> buscarUsuario(RequestGenericDto<UsuarioFiltroDto> request) throws BusinessException;

	/**
	 * @param metodo para consultar el detalle de usuario
	 * @return devuelve informacion del usuario
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<ResponseUsuarioDetalleDto> buscarUsuarioDetalle(RequestGenericDto<UsuarioFiltroDto> request) throws BusinessException;

}
