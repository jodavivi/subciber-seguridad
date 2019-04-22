/**
 * 
 */
package com.subciber.seguridad.dao.api;

import javax.ejb.Local;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.dao.base.GenericaJPADao;
import com.subciber.seguridad.entity.UsuarioRol;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para el mantenimiento de usuario rol
 * @author David Villanueva
 * @version 0.1, 17/02/2019
 * @update
 */
@Local
public interface UsuarioRolTxDao extends GenericaJPADao<UsuarioRol>{

	/**
	 * @param metodo para eliminar el usuario rol por idUsuario
	 * @return 
	 * @throws DaoException
	 */
	public abstract AuditResponseDto eliminarUsuarioRolxIdUsuario(RequestGenericDto<Integer> request) throws DaoException;
	
}
