package com.subciber.seguridad.dao.api;

import java.util.List;

import javax.ejb.Local;

import com.subciber.seguridad.dto.AccesoFiltroDto;
import com.subciber.seguridad.entity.VAccesoGrupoAplicacion;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para obtener los permisos de usuario
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Local
public interface AccesosUsuarioRxDao {
	
	/**
	 * @param metodo para listas los accesos a grupos y aplicaciones del usuario
	 * @return devuelve informacion de accesos del usuario
	 * @throws DaoException
	 */
	public abstract List<VAccesoGrupoAplicacion> accesosGrupoAplicacionesUsuario(AccesoFiltroDto request) throws DaoException;
	
}
