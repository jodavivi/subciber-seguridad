/**
 * 
 */
package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.EliminarObjetoDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.dto.UsuarioActualizacionDto;
import com.subciber.seguridad.dto.UsuarioDetalleDto;
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
	
	/**
	 * @param metodo para la eliminar  usuarios
	 * @return devuelve el objeto de auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto eliminarUsuario(RequestGenericDto<EliminarObjetoDto>  request) throws BusinessException;
	
	/**
	 * @param metodo actualizar el usuario
	 * @return devuelve el objeto de auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto actualizarUsuario(RequestGenericDto<UsuarioActualizacionDto>  request) throws BusinessException;

}
