/**
 * 
 */
package com.subciber.seguridad.dao.impl;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.subciber.seguridad.dao.api.AutenticacionRxDao;
import com.subciber.seguridad.dao.base.BaseJPADao;
import com.subciber.seguridad.dto.AutenticacionFiltroDto;
import com.subciber.seguridad.entity.VUsuarioAutenticacion;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;

/**
 * @description implementacion de la interface AutenticacionDao
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Stateless
public class AutenticacionRxDaoImpl extends BaseJPADao<VUsuarioAutenticacion> implements AutenticacionRxDao, Serializable {

	@Inject
    private MessageProvider messageProvider;
	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VUsuarioAutenticacion autenticar(AutenticacionFiltroDto request) throws DaoException {
		
		StringBuilder jpql = null;
		StringBuilder jpqlSelect = null;
		StringBuilder jpqlWhere = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		TypedQuery<VUsuarioAutenticacion> query = null;
		VUsuarioAutenticacion resultado = null;
		try {
			jpqlSelect = new StringBuilder();
			jpqlSelect.append("SELECT ");
			jpqlSelect.append("u ");
			jpqlSelect.append("FROM ");
			jpqlSelect.append(entityClass.getSimpleName());
			jpqlSelect.append(" u ");
			
			jpqlWhere = new StringBuilder();
			jpqlWhere.append("WHERE ");
			jpqlWhere.append("u.estadoId = :estadoId ");
			jpqlWhere.append("and ");
			jpqlWhere.append("u.usuario  = :usuario ");
			jpqlWhere.append("and ");
			jpqlWhere.append("u.clave    = :clave ");
			
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			
			query = entityManager.createQuery(jpql.toString(), VUsuarioAutenticacion.class);
			query.setParameter("estadoId", 23);
			query.setParameter("usuario", request.getUsuario());
			query.setParameter("clave", request.getClave());
			
			resultado = query.getSingleResult();
			
		}catch (NoResultException e){
			resultado = null;
		} catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		
		return resultado;
	}

}
