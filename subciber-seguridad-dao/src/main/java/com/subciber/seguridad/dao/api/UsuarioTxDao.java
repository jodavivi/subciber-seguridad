/**
 * 
 */
package com.subciber.seguridad.dao.api;

import javax.ejb.Local;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.dao.base.GenericaJPADao;
import com.subciber.seguridad.dto.ClaveUsuarioDto;
import com.subciber.seguridad.entity.Usuario;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para la creacion de usuairos
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
@Local
public interface UsuarioTxDao extends GenericaJPADao<Usuario>{

	public abstract AuditResponseDto actualizarClaveUsuario(RequestGenericDto<ClaveUsuarioDto> request) throws DaoException;
	public abstract AuditResponseDto eliminarUsuarioxId(RequestGenericDto<Integer> request) throws DaoException;
	
}
