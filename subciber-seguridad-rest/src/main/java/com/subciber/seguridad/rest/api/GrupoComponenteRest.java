/**
 * 
 */
package com.subciber.seguridad.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.seguridad.dto.GrupoComponenteDto;
import com.subciber.seguridad.dto.RequestDeleteObjectDto;

/**
 * @description Interface para el mantenimiento de componentes por rol
 * @author David Villanueva
 * @version 0.1, 29/03/2019
 * @update
 */
public interface GrupoComponenteRest {

	/**
	 * @param metodo para consultar los  componentes por Grupo
	 * @return devuelve informacion de los componentes
	 * @throws 
	 */
	public abstract Response consultarGrupoComponente();
	
	/**
	 * @param metodo para consultar los  componentes por grupo
	 * @return devuelve informacion de los componentes
	 * @throws 
	 */
	public abstract Response consultarGrupoComponentPorGrupo();
	
	/**
	 * @param metodo para registrar un componente por grupo
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response registrarGrupoComponente(GrupoComponenteDto request);
	
	/**
	 * @param metodo para actualizar un componente por grupo
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response actualizarGrupoComponente(GrupoComponenteDto request);
	

	/**
	 * @param metodo para eliminar un componente por grupo
	 * @return devuelve Response
	 * @throws 
	 */
	public abstract Response eliminarGrupoComponente(RequestDeleteObjectDto request);
}
