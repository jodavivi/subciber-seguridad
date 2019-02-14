/**
 * 
 */
package com.subciber.seguridad.dao.base;

import com.subciber.seguridad.exception.DaoException;

/**
 * @author josep
 *
 */
public interface GenericaJPADao<T> {
	     
	    public T get(int id) throws DaoException;
	    
}
