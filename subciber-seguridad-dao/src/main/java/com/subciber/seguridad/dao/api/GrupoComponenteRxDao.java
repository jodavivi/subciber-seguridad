package com.subciber.seguridad.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.seguridad.dto.GrupoComponenteFiltroDto;
import com.subciber.seguridad.entity.VGrupoComponente;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para obtener los componentes por grupo
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Local
public interface GrupoComponenteRxDao {
	
	/**
	 * @param metodo para listas los componentes por grupo
	 * @return devuelve los componentes por grupo
	 * @throws DaoException
	 */
	public abstract List<VGrupoComponente> consultarGrupoComponente(GrupoComponenteFiltroDto request) throws DaoException;

}
