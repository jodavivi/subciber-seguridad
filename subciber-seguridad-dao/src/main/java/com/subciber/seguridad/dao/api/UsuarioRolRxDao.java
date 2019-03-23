/**
 * 
 */
package com.subciber.seguridad.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.entity.VUsuarioRol;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para la consulta de usuarios
 * @author David Villanueva
 * @version 0.1, 17/02/2019
 * @update
 */
@Local
public interface UsuarioRolRxDao{

	/**
	 * @param metodo para ver los roles del usuario
	 * @return devuelve informacion de los roles del usuario
	 * @throws DaoException
	 */
	public abstract List<VUsuarioRol> consultarRolUsuario(UsuarioFiltroDto request) throws DaoException;
	 
	
}
