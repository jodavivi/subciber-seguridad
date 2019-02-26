/**
 * 
 */
package com.subciber.seguridad.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.LocalDateTime;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import com.subciber.seguridad.base.dto.RequestGenericEliminarDto;
import com.subciber.seguridad.dao.api.UsuarioRolTxDao;
import com.subciber.seguridad.dao.base.GenericaJPADaoImpl;
import com.subciber.seguridad.entity.UsuarioRol;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.ConstantesConfig;

/**
 * @author josep
 *
 */
@Stateless
public class UsuarioRolTxDaoImpl extends GenericaJPADaoImpl<UsuarioRol>  implements UsuarioRolTxDao  , Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private MessageProvider messageProvider;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void eliminarUsuarioRolxIdUsuario(RequestGenericEliminarDto request) throws DaoException {
		 
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		StringBuilder jpql 			= null;
		StringBuilder jpqlSelect 	= null;
		StringBuilder jpqlWhere  	= null;
		try {
			jpqlSelect = new StringBuilder();
			jpqlSelect.append("UPDATE ");
			jpqlSelect.append(entityClass.getSimpleName());
			jpqlSelect.append(" SET ");
			jpqlSelect.append("estadoId 			= 25, ");
			jpqlSelect.append("fechaModificacion 	= :fechaModificacion, ");
			jpqlSelect.append("terminalModificador 	= :terminalModificador, ");
			jpqlSelect.append("transaccionId 		= :transaccionId, ");
			jpqlSelect.append("usuarioModificador 	= :usuarioModificador ");
			
			jpqlWhere = new StringBuilder();
			jpqlWhere.append("WHERE ");
			jpqlWhere.append("estadoId 		= :estadoId ");
			jpqlWhere.append("and ");
			jpqlWhere.append("usuarioId  	= :usuarioId ");
			
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			Query query = entityManager.createQuery(jpql.toString());
			query.setParameter("fechaModificacion", LocalDateTime.now());
			query.setParameter("terminalModificador", request.getAuditRequest().getTerminal());
			query.setParameter("transaccionId", request.getAuditRequest().getTransaccionId());
			query.setParameter("usuarioModificador", request.getAuditRequest().getUsuario());
			query.setParameter("estadoId", ConstantesConfig.activo);
			query.setParameter("usuarioId", request.getId().get(0));
			query.executeUpdate();
			
		} catch (Exception e) {
			String error = "";
			 Throwable th = e.getCause();
			  if(th.getCause() instanceof SQLException) {
				  SQLException cause = (SQLException) th.getCause();
				  error =cause.getMessage();
			  }
			throw new DaoException(messageProvider.codigoErrorIdt1, MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, error));
		}
	}


}

