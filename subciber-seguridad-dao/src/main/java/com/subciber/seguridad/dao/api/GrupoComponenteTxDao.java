/**
 * 
 */
package com.subciber.seguridad.dao.api;

import javax.ejb.Local;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.dto.GrupoComponenteDto;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para el mantenimiento de los componentes por grupo
 * @author David Villanueva
 * @version 0.1, 13/03/2019
 * @update
 */
@Local
public interface GrupoComponenteTxDao {
	
	/**
	 * @param metodo para registrar un grupo componente
	 * @return 
	 * @throws DaoException
	 */
	public abstract ResponseGenericDto<Integer> registrarGrupoComponente(RequestGenericDto<GrupoComponenteDto> request) throws DaoException;

	/**
	 * @param metodo para actualizar el grupo componente
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto actualizarGrupoComponente(RequestGenericDto<GrupoComponenteDto> request) throws DaoException;
	
	
	/**
	 * @param metodo para eliminar aplicaciones del grupo
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarGrupoComponente(RequestGenericDto<Integer> request) throws DaoException;
	
	/**
	 * @param metodo para eliminar todas las aplicaciones del grupo
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarGrupoComponenteAll(RequestGenericDto<Integer> request) throws DaoException;
	
}
