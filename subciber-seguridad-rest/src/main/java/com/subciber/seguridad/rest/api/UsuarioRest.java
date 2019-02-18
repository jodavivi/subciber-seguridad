/**
 * 
 */
package com.subciber.seguridad.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.seguridad.business.dto.UsuarioDetalleDto;

/**
 * @description Interface para el mantenimiento de usuario
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
public interface UsuarioRest {

	/**
	 * @param metodo para crear usuario
	 * @return devuelve informacion del usuario
	 * @throws 
	 */
	public abstract Response crearUsuario(UsuarioDetalleDto request);
}
