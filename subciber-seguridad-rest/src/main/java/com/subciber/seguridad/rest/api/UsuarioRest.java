/**
 * 
 */
package com.subciber.seguridad.rest.api;

import javax.ws.rs.core.Response;

import com.subciber.seguridad.base.dto.EliminarObjetoDto;
import com.subciber.seguridad.dto.ClaveUsuarioDto;
import com.subciber.seguridad.dto.RecuperarCuentaDto;
import com.subciber.seguridad.dto.UsuarioActualizacionDto;
import com.subciber.seguridad.dto.UsuarioDetalleDto;

/**
 * @description Interface para el mantenimiento de usuario
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
public interface UsuarioRest {
	
	/**
	 * @param metodo para buscar usuario
	 * @return devuelve lista de usuarios  segun filtro
	 * @throws 
	 */
	public abstract Response listarUsuario();

	/**
	 * @param metodo para ver el detalle del usuario
	 * @return devuelve el detalle del usuario
	 * @throws 
	 */
	public abstract Response usuarioDetalle();
	
	/**
	 * @param metodo para crear usuario
	 * @return devuelve informacion del usuario
	 * @throws 
	 */
	public abstract Response crearUsuario(UsuarioDetalleDto request);
	
	/**
	 * @param metodo para eliminar usuarios
	 * @return  
	 * @throws 
	 */
	public abstract Response eliminarUsuario(EliminarObjetoDto request);
	
	/**
	 * @param metodo para actualizar el usuario
	 * @return  
	 * @throws 
	 */
	public abstract Response actualizarUsuario(UsuarioActualizacionDto request);
	
	/**
	 * @param metodo para actualizar la clave del usuario
	 * @return  
	 * @throws 
	 */
	public abstract Response actualizarClaveUsuario(ClaveUsuarioDto request);

	/**
	 * @param metodo para eliminar usuario por la url 
	 * @return  
	 * @throws 
	 */
	public abstract Response  eliminarUsuarioPath();
	
	/**
	 * @param metodo para eliminar usuario por la url 
	 * @return  
	 * @throws 
	 */
	public abstract Response  recuperarCuenta(RecuperarCuentaDto request);
	
	/**
	 * @param metodo para ver información del usuario
	 * @return devuelve el detalle del usuario
	 * @throws 
	 */
	public abstract Response usuarioPerfil();
	
 
}
