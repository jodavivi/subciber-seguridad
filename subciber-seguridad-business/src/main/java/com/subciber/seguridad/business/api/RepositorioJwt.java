/**
 * 
 */
package com.subciber.seguridad.business.api;

import com.subciber.seguridad.business.dto.InfoJwt;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para almacenar los usuarios logueados
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
public interface RepositorioJwt {

	/**
	 * @param metodopara almacenar los usuarios logueados
	 * @return devuelve informacion del usuario logueado
	 * @throws BusinessException
	 */
	public abstract void registrarUsuario(String token, InfoJwt informacion) throws BusinessException;
	
	/**
	 * @param metodopara almacenar los usuarios logueados
	 * @return devuelve informacion del usuario logueado
	 * @throws BusinessException
	 */
	public abstract InfoJwt recuperarUsuario(String request) throws BusinessException;

}
