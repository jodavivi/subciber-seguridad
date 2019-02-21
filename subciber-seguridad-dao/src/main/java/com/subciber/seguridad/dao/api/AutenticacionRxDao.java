package com.subciber.seguridad.dao.api;

import javax.ejb.Local;

import com.subciber.seguridad.dto.AutenticacionFiltroDto;
import com.subciber.seguridad.entity.VUsuarioAutenticacion;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para la autenticacion de usuario
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Local
public interface AutenticacionRxDao {
	
	/**
	 * @param metodo para la autenticacion de usuarios
	 * @return devuelve informacion del usuario
	 * @throws DaoException
	 */
	public abstract VUsuarioAutenticacion autenticar(AutenticacionFiltroDto request) throws DaoException;

}
