/**
 * 
 */
package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.dto.AutenticacionFiltroDto;
import com.subciber.seguridad.dto.InfoUsuarioDto;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para la autenticacion de usuario
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
public interface AutenticacionBusiness {

	/**
	 * @param metodo para la autenticacion de usuarios
	 * @return devuelve informacion del usuario
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<InfoUsuarioDto> autenticarUsuario(RequestGenericDto<AutenticacionFiltroDto> request) throws BusinessException;
	
}
