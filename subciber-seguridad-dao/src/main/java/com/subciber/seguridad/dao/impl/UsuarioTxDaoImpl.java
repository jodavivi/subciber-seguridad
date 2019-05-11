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

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.dao.api.UsuarioTxDao;
import com.subciber.seguridad.dao.base.GenericaJPADaoImpl;
import com.subciber.seguridad.dto.ClaveUsuarioDto;
import com.subciber.seguridad.entity.Usuario;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.ConstantesConfig;

/**
 * @author josep
 *
 */
@Stateless
public class UsuarioTxDaoImpl extends GenericaJPADaoImpl<Usuario>  implements UsuarioTxDao  , Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
    private MessageProvider messageProvider;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	@Override
	public AuditResponseDto eliminarUsuarioxId(RequestGenericDto<Integer> request) throws DaoException {
		 
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		StringBuilder jpql 			= null;
		StringBuilder jpqlSelect 	= null;
		StringBuilder jpqlWhere  	= null;
		AuditResponseDto response = new AuditResponseDto();
		try {
			response.setTransaccionId(request.getAuditRequest().getTransaccionId());
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
			jpqlWhere.append("estadoId 		!= :estadoId ");
			jpqlWhere.append("and ");
			jpqlWhere.append("id  			= :usuarioId ");
			
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			Query query = entityManager.createQuery(jpql.toString());
			query.setParameter("fechaModificacion", LocalDateTime.now());
			query.setParameter("terminalModificador", request.getAuditRequest().getTerminal());
			query.setParameter("transaccionId", request.getAuditRequest().getTransaccionId());
			query.setParameter("usuarioModificador", request.getAuditRequest().getUsuario());
			query.setParameter("estadoId", ConstantesConfig.eliminado);
			query.setParameter("usuarioId", request.getObjectRequest());
			query.executeUpdate();
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
		} catch (Exception e) {
			String error = "";
			 Throwable th = e.getCause();
			  if(th.getCause() instanceof SQLException) {
				  SQLException cause = (SQLException) th.getCause();
				  error =cause.getMessage();
			  }
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, error));
		}
		return response; 
	}

	@Override
	public AuditResponseDto actualizarClaveUsuario(RequestGenericDto<ClaveUsuarioDto> request) throws DaoException {
		 
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		StringBuilder jpql 			= null;
		StringBuilder jpqlSelect 	= null;
		StringBuilder jpqlWhere  	= null;
		AuditResponseDto response = new AuditResponseDto();
		try {
			response.setTransaccionId(request.getAuditRequest().getTransaccionId());
			jpqlSelect = new StringBuilder();
			jpqlSelect.append("UPDATE ");
			jpqlSelect.append(entityClass.getSimpleName());
			jpqlSelect.append(" SET "); 
			jpqlSelect.append("fechaModificacion 	= :fechaModificacion, ");
			jpqlSelect.append("terminalModificador 	= :terminalModificador, "); 
			jpqlSelect.append("usuarioModificador 	= :usuarioModificador, ");
			jpqlSelect.append("clave 				= :clave, ");
			jpqlSelect.append("cambiarClave			= :cambiarClave ");
			jpqlWhere = new StringBuilder();
			jpqlWhere.append("WHERE "); 
			jpqlWhere.append("id  			= :usuarioId ");
			
			jpql = new StringBuilder();
			jpql.append(jpqlSelect);
			jpql.append(jpqlWhere);
			Query query = entityManager.createQuery(jpql.toString());
			query.setParameter("fechaModificacion", LocalDateTime.now());
			query.setParameter("terminalModificador", request.getAuditRequest().getTerminal());
			query.setParameter("usuarioModificador", request.getAuditRequest().getUsuario());
			query.setParameter("clave", request.getObjectRequest().getNuevaClave());
			query.setParameter("cambiarClave", ConstantesConfig.noCambiarClave);
			query.setParameter("usuarioId", request.getObjectRequest().getUsuarioId());
			query.executeUpdate();
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
		} catch (Exception e) {
			String error = "";
			 Throwable th = e.getCause();
			  if(th.getCause() instanceof SQLException) {
				  SQLException cause = (SQLException) th.getCause();
				  error =cause.getMessage();
			  }
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, error));
		}
		return response; 
	}

}

