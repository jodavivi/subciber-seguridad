/**
 * 
 */
package com.subciber.seguridad.dao.base;

import java.math.BigInteger;
import java.sql.SQLException;
import java.text.MessageFormat;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;

/**
 * @author josep
 * @param <T>
 *
 */
@Stateless
public abstract  class GenericaJPADaoImpl<T> extends BaseJPADao<T>  implements GenericaJPADao<T> {

	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@Inject
    private MessageProvider messageProvider;
	
	/**
	 * {@inheritDoc}
	 */
	public Integer obtenerId(String sqlSecuencia) throws DaoException{

		Integer response  = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			Query q = entityManager.createNativeQuery(sqlSecuencia);
			BigInteger result=(BigInteger)q.getSingleResult();   
			response = result.intValue();
		}catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		
		return response;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public T buscarId(Integer id) throws DaoException{

		T response  = null;
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			T responseFind = entityManager.find(entityClass, id);
			response = responseFind;
		}catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		
		return response;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public T crear(T request) throws DaoException {
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.persist(request);
			entityManager.flush();
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
				  }
			 
		}catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		
		return request;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actualizar(T request) throws DaoException {
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.merge(request);
			entityManager.flush();
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
				  }
			 
		}catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminar(T request) throws DaoException {
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			entityManager.merge(request);
			entityManager.flush();
			
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
				  }
			 
		}catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
	}
}
