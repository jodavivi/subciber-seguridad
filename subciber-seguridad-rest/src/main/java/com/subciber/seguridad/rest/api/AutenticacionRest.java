package com.subciber.seguridad.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.seguridad.dto.AutenticacionFiltroDto;

/**
 * @description Interface para la autenticacion de usuario
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
public interface AutenticacionRest {

	/**
	 * @param metodo para la autenticacion de usuarios
	 * @return devuelve informacion del usuario
	 * @throws 
	 */
	public abstract Response autenticarUsuario(AutenticacionFiltroDto request);

}
