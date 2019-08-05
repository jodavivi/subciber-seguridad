package com.subciber.seguridad.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.seguridad.dto.RolComponenteFiltroDto;
import com.subciber.seguridad.entity.VRolComponenteFaltate;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para obtener los componentes faltantes por rol
 * @author David Villanueva
 * @version 0.1, 04/08/2019
 * @update
 */
@Local
public interface RolComponenteFaltanteRxDao {
	
	/**
	 * @param metodo para listas los componentes faltantes por rol
	 * @return devuelve los componentes faltantes por rol
	 * @throws DaoException
	 */
	public abstract List<VRolComponenteFaltate> consultarRolComponenteFaltante(RolComponenteFiltroDto request) throws DaoException;

}
