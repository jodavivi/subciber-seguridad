/**
 * 
 */
package com.subciber.seguridad.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.subciber.seguridad.dao.api.AccesosRecursosUsuarioRxDao;
import com.subciber.seguridad.dao.base.BaseJPADao;
import com.subciber.seguridad.dto.AccesoFiltroDto;
import com.subciber.seguridad.entity.VAccesoComponente;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author jose david villanueva villalobos
 * @Creacion 12 feb. 2019
 * @Update
 * 
 */
@Stateless
public class AccesoRecursoUsuarioRxDaoImpl extends BaseJPADao<VAccesoComponente> implements AccesosRecursosUsuarioRxDao, Serializable {
	
	static final Logger logger = LoggerFactory.getLogger(AccesoRecursoUsuarioRxDaoImpl.class);
	
	@Inject
    private MessageProvider messageProvider;
	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VAccesoComponente> accesosRecursosUsuario(AccesoFiltroDto request) throws DaoException {

		StringBuilder jpql = null;
		StringBuilder jpqlSelect = null;
		StringBuilder jpqlWhere = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		TypedQuery<VAccesoComponente> query = null;
		List<VAccesoComponente> resultado = null;
		try {
			jpqlSelect = new StringBuilder();
			jpqlSelect.append("SELECT ");
			jpqlSelect.append("u ");
			jpqlSelect.append("FROM ");
			jpqlSelect.append(entityClass.getSimpleName());
			jpqlSelect.append(" u ");
			
			jpqlWhere = new StringBuilder();
			jpqlWhere.append("WHERE ");
			jpqlWhere.append("u.usuarioId = :usuarioId ");
			
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			query = entityManager.createQuery(jpql.toString(), VAccesoComponente.class);
			query.setParameter("usuarioId", request.getUsuarioId());
			
			resultado = query.getResultList();
			
		}catch (NoResultException e){
			resultado = null;
		} catch(Exception e) {
			String error = "";
			 Throwable th = e.getCause();
			  if(th.getCause() instanceof SQLException) {
				  SQLException cause = (SQLException) th.getCause();
				  error =cause.getMessage();
			  }
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, error));
		}
		
		return resultado;
	}

}
