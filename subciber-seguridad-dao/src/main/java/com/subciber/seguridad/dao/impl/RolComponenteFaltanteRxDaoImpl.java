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

import com.subciber.seguridad.dao.api.RolComponenteFaltanteRxDao;
import com.subciber.seguridad.dao.base.BaseJPADao;
import com.subciber.seguridad.dto.RolComponenteFiltroDto;
import com.subciber.seguridad.entity.VRolComponenteFaltate;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;

/**
 * @description implementacion de la interface GrupoComponenteRxDao
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Stateless
public class RolComponenteFaltanteRxDaoImpl extends BaseJPADao<VRolComponenteFaltate> implements RolComponenteFaltanteRxDao, Serializable  {

	@Inject
    private MessageProvider messageProvider;
	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	/**
	 * {@inheritDoc}
	 */ 
	@Override
	public List<VRolComponenteFaltate> consultarRolComponenteFaltante(RolComponenteFiltroDto request) throws DaoException {
		
		StringBuilder jpql = null;
		StringBuilder jpqlSelect = null;
		StringBuilder jpqlWhere = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		TypedQuery<VRolComponenteFaltate> query = null;
		List<VRolComponenteFaltate> resultado = null;
		try {
			jpqlSelect = new StringBuilder();
			jpqlSelect.append("SELECT ");
			jpqlSelect.append("u ");
			jpqlSelect.append("FROM ");
			jpqlSelect.append(entityClass.getSimpleName());
			jpqlSelect.append(" u ");
			
			jpqlWhere = new StringBuilder();
			jpqlWhere.append("WHERE ");
			jpqlWhere.append("1 = 1 ");
			if(request.getIdRol() != null) {
				jpqlWhere.append("and u.rolId = :rolId ");
			}
			
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			query = entityManager.createQuery(jpql.toString(), VRolComponenteFaltate.class);
			if(request.getIdRol() != null) {
				query.setParameter("rolId", request.getIdRol());
			}
			
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
