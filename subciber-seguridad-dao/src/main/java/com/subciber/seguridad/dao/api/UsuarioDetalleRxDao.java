/**
 * 
 */
package com.subciber.seguridad.dao.api;

import javax.ejb.Local;

import com.subciber.seguridad.dto.UsuarioDetalleFiltroDto;
import com.subciber.seguridad.entity.UsuarioDetalle;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para la consulta el detalle del usuario
 * @author David Villanueva
 * @version 0.1, 17/02/2019
 * @update
 */
@Local
public interface UsuarioDetalleRxDao{

	/**
	 * @param metodo obtener el detalle del usuario
	 * @return devuelve informacion del detalle del usuario
	 * @throws DaoException
	 */
	public abstract UsuarioDetalle consultarUsuarioDetalle(UsuarioDetalleFiltroDto request) throws DaoException;
	
}
