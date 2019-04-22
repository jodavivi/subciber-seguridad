/**
 * 
 */
package com.subciber.seguridad.dao.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.subciber.seguridad.dao.api.UsuarioRolRxDao;
import com.subciber.seguridad.dao.base.BaseJPADao;
import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.entity.VUsuarioRol;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;

/**
 * @author josep
 *
 */
@Stateless
public class UsuarioRolRxDaoImpl extends BaseJPADao<VUsuarioRol>  implements UsuarioRolRxDao  , Serializable {

	@Inject
    private MessageProvider messageProvider;
	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VUsuarioRol> consultarRolUsuario(UsuarioFiltroDto request) throws DaoException {
		StringBuilder jpql = null;
		StringBuilder jpqlSelect = null;
		StringBuilder jpqlWhere = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		TypedQuery<VUsuarioRol> query = null;
		List<VUsuarioRol> resultado = null;
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
			if(request.getUsuarioId()!= null && request.getUsuarioId() != 0) {
				jpqlWhere.append("and ");
				jpqlWhere.append("u.usuarioId = :usuarioId ");
			}
			
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			
			query = entityManager.createQuery(jpql.toString(), VUsuarioRol.class);
			
			if(request.getUsuarioId()!= null && request.getUsuarioId() != 0) {
				query.setParameter("usuarioId", request.getUsuarioId());
			}

			resultado = query.getResultList();
			
		}catch (NoResultException e){
			resultado = null;
		} catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		
		return resultado;
	}
 

}

