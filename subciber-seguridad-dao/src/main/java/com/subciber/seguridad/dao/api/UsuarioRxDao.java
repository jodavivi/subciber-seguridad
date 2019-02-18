/**
 * 
 */
package com.subciber.seguridad.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.entity.VUsuario;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para la consulta de usuarios
 * @author David Villanueva
 * @version 0.1, 17/02/2019
 * @update
 */
@Local
public interface UsuarioRxDao{

	/**
	 * @param metodo para la autenticacion de usuarios
	 * @return devuelve informacion del usuario
	 * @throws DaoException
	 */
	public abstract List<VUsuario> consultarUsuario(UsuarioFiltroDto request) throws DaoException;
	
}
