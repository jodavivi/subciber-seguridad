package com.subciber.seguridad.dao.api;

import java.util.List;

import com.subciber.seguridad.dto.AccesoFiltroDto;
import com.subciber.seguridad.entity.VAccesoComponente;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para obtener los permisos de usuario
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
public interface AccesosRecursosUsuarioDao {
	
	/**
	 * @param metodo para listas los accesos a los componentes del usuario
	 * @return devuelve informacion de accesos del usuario
	 * @throws DaoException
	 */
	public abstract List<VAccesoComponente> accesosRecursosUsuario(AccesoFiltroDto request) throws DaoException;

}
