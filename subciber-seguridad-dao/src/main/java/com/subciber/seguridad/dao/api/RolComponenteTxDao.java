/**
 * 
 */
package com.subciber.seguridad.dao.api;

import javax.ejb.Local;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.dto.RolComponenteDto;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para el mantenimiento de los componentes por grupo
 * @author David Villanueva
 * @version 0.1, 13/03/2019
 * @update
 */
@Local
public interface RolComponenteTxDao {
	
	/**
	 * @param metodo para registrar un rol componente
	 * @return 
	 * @throws DaoException
	 */
	public abstract ResponseGenericDto<Integer> registrarRolComponente(RequestGenericDto<RolComponenteDto> request) throws DaoException;

	/**
	 * @param metodo para actualizar el rol componente
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto actualizarRolComponente(RequestGenericDto<RolComponenteDto> request) throws DaoException;
	
	
	/**
	 * @param metodo para actualizar el rol componente
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarRolComponente(RequestGenericDto<Integer> request) throws DaoException;
	
}
