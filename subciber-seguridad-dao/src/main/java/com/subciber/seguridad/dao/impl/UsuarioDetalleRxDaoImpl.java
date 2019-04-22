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

import com.subciber.seguridad.dao.api.UsuarioDetalleRxDao;
import com.subciber.seguridad.dao.base.BaseJPADao;
import com.subciber.seguridad.dto.UsuarioDetalleFiltroDto;
import com.subciber.seguridad.entity.UsuarioDetalle;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;

/**
 * @author josep
 *
 */
@Stateless
public class UsuarioDetalleRxDaoImpl extends BaseJPADao<UsuarioDetalle>  implements UsuarioDetalleRxDao  , Serializable {

	@Inject
    private MessageProvider messageProvider;
	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UsuarioDetalle consultarUsuarioDetalle(UsuarioDetalleFiltroDto request) throws DaoException {
		StringBuilder jpql = null;
		StringBuilder jpqlSelect = null;
		StringBuilder jpqlWhere = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		TypedQuery<UsuarioDetalle> query = null;
		UsuarioDetalle resultado = null;
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
//			if(request.getEstadoId()!= null && request.getEstadoId() != 0) {
//				jpqlWhere.append("and ");
//				jpqlWhere.append("u.estadoId = :estadoId ");
//			}
			
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			
			query = entityManager.createQuery(jpql.toString(), UsuarioDetalle.class);
			
			if(request.getUsuarioId()!= null && request.getUsuarioId() != 0) {
				query.setParameter("usuarioId", request.getUsuarioId());
			}
//			if(request.getEstadoId()!= null && request.getEstadoId() != 0) {
//				query.setParameter("estadoId", request.getEstadoId());
//			}

			resultado = query.getSingleResult();
			
		}catch (NoResultException e){
			resultado = null;
		} catch(Exception e) {
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		
		return resultado;
	}


}

