package com.subciber.seguridad.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.seguridad.dto.GrupoComponenteFiltroDto;
import com.subciber.seguridad.entity.VGrupoComponenteFaltate;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para obtener los componentes faltantes por grupo
 * @author David Villanueva
 * @version 0.1, 04/08/2019
 * @update
 */
@Local
public interface GrupoComponenteFaltanteRxDao {
	
	/**
	 * @param metodo para listas los componentes faltantes por grupo
	 * @return devuelve los componentes faltantes por grupo
	 * @throws DaoException
	 */
	public abstract List<VGrupoComponenteFaltate> consultarGrupoComponenteFaltante(GrupoComponenteFiltroDto request) throws DaoException;

}
