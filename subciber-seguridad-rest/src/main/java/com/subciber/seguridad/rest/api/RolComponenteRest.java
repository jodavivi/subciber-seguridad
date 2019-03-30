/**
 * 
 */
package com.subciber.seguridad.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.seguridad.dto.RequestDeleteObjectDto;
import com.subciber.seguridad.dto.RolComponenteDto;

/**
 * @description Interface para el mantenimiento de componentes por rol
 * @author David Villanueva
 * @version 0.1, 29/03/2019
 * @update
 */
public interface RolComponenteRest {

	/**
	 * @param metodo para consultar los  componentes por rol
	 * @return devuelve informacion de los componentes
	 * @throws 
	 */
	public abstract Response consultarRolComponente();
	
	/**
	 * @param metodo para consultar los  componentes por rol
	 * @return devuelve informacion de los componentes
	 * @throws 
	 */
	public abstract Response consultarRolComponentePorRol();
	
	/**
	 * @param metodo para registrar un componente por rol
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response registrarRolComponente(RolComponenteDto request);
	
	/**
	 * @param metodo para actualizar un componente por rol
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response actualizarRolComponente(RolComponenteDto request);
	

	/**
	 * @param metodo para eliminar un componente por rol
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response eliminarRolComponente(RequestDeleteObjectDto request);
}
