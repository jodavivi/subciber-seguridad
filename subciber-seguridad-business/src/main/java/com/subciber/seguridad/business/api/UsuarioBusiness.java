/**
 * 
 */
package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.dto.UsuarioDetalleDto;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para el mantenimiento de usuarios
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
public interface UsuarioBusiness {

	/**
	 * @param metodo para la creacion de usuarios
	 * @return devuelve informacion del usuario creado
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<UsuarioDetalleDto> crearUsuario(RequestGenericDto<UsuarioDetalleDto> request) throws BusinessException;
	
}
