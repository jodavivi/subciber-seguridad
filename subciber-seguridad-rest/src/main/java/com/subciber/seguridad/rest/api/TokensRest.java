package com.subciber.seguridad.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.seguridad.dto.FiltroTokensDto;

/**
 * @description Interface para el mantenimiento de tokens
 * @author David Villanueva
 * @version 0.1, 06/03/2019
 * @update
 */
public interface TokensRest {

	/**
	 * @param metodo para consultar los tokens generados en el sistema
	 * @return devuelve informacion de los token generados
	 * @throws 
	 */
	public abstract Response consultarTokens(FiltroTokensDto request);
	
	/**
	 * @param metodo para lista los usuarios logueados al sistema
	 * @return devuelve informacion de los token generados
	 * @throws 
	 */
	public abstract Response usuariosLogueados();
	
	/**
	 * @param metodo para lista los usuarios logueados al sistema
	 * @return devuelve informacion de los token generados
	 * @throws 
	 */
	public abstract Response validarTokens(FiltroTokensDto request);

	/**
	 * @param metodo para cerrar session
	 * @return 
	 * @throws 
	 */
	public abstract Response cerrarSesion(FiltroTokensDto request);
}
