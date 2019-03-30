package com.subciber.seguridad.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.seguridad.dto.RolComponenteFiltroDto;
import com.subciber.seguridad.entity.VRolComponente;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para obtener los componentes por rol
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Local
public interface RolComponenteRxDao {
	
	/**
	 * @param metodo para listas los componentes por rol
	 * @return devuelve los componentes
	 * @throws DaoException
	 */
	public abstract List<VRolComponente> consultarRolComponente(RolComponenteFiltroDto request) throws DaoException;

}
