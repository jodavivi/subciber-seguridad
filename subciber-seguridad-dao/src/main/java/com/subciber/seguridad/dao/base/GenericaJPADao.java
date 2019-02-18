/**
 * 
 */
package com.subciber.seguridad.dao.base;

import javax.ejb.Local;

import com.subciber.seguridad.exception.DaoException;

/**
 * @description Clase para metodos genericos
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
@Local
public interface GenericaJPADao<T> {
	     
	/**
	 * @param Metodo para obtener el Id 
	 * @return el Id del Registro
	 * @throws DaoException 
	 */
	public abstract Integer obtenerId(String sqlSecuencia) throws DaoException;
	
	/**
	 * @param Metodo para crar objetos
	 * @return el Id del Registro
	 * @throws DaoException 
	 */
	public abstract T crear(T request) throws DaoException;
	
	    
}
