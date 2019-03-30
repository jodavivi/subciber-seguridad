/**
 * 
 */
package com.subciber.seguridad.business.api;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.dto.RequestDeleteObjectDto;
import com.subciber.seguridad.dto.RolComponenteDto;
import com.subciber.seguridad.exception.BusinessException;

/**
 * @description Interface para el mantenimiento de los  componentes por rol
 * @author David Villanueva
 * @version 0.1, 13/03/2019
 * @update
 */
public interface RolComponenteTxBusiness {
	
	/**
	 * @param metodo para registrar el id componente por rol
	 * @return devuelve el Id del registro
	 * @throws BusinessException
	 */
	public abstract ResponseGenericDto<Integer> registrarRolComponente(RequestGenericDto<RolComponenteDto> request) throws BusinessException;

	/**
	 * @param metodo para actualizar el campo de la tabla generica
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto actualizarRolComponente(RequestGenericDto<RolComponenteDto> request) throws BusinessException;

	/**
	 * @param metodo para eliminar el campo de la tabla generica
	 * @return devuelve el objeto de Auditoria
	 * @throws BusinessException
	 */
	public abstract AuditResponseDto eliminarRolComponente(RequestGenericDto<RequestDeleteObjectDto> request) throws BusinessException;
	
}
