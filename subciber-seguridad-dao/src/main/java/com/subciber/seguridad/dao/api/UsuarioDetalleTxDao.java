/**
 * 
 */
package com.subciber.seguridad.dao.api;

import javax.ejb.Local;

import com.subciber.seguridad.base.dto.RequestGenericEliminarDto;
import com.subciber.seguridad.dao.base.GenericaJPADao;
import com.subciber.seguridad.entity.UsuarioDetalle;
import com.subciber.seguridad.exception.DaoException;

/**
 * @description Interface para el mantenimiento de la tabla usuario detalle
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
@Local
public interface UsuarioDetalleTxDao extends GenericaJPADao<UsuarioDetalle>{

	/**
	 * @param metodo para actualizar el detalle del usuario
	 * @return 
	 * @throws DaoException
	 */
	public abstract void eliminarUsuarioDetallexIdUsuario (RequestGenericEliminarDto request) throws DaoException;
}
