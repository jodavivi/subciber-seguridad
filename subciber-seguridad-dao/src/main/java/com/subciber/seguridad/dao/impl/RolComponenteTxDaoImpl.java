/**
 * 
 */
package com.subciber.seguridad.dao.impl;

import java.sql.SQLException;
import java.text.MessageFormat;
import java.time.LocalDateTime;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.dao.api.RolComponenteTxDao;
import com.subciber.seguridad.dao.base.BaseJPADao;
import com.subciber.seguridad.dto.RolComponenteDto;
import com.subciber.seguridad.entity.RolComponente;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.ConstantesConfig;

/**
 * @author jose david villanueva villalobos
 * @Creacion 0.1, 13/03/2019
 * @Update
 * 
 */
@Stateless
public class RolComponenteTxDaoImpl extends BaseJPADao<RolComponente>  implements RolComponenteTxDao {
	
	private static final long serialVersionUID = 1L;
	@Inject
    private MessageProvider messageProvider;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<Integer> registrarRolComponente(RequestGenericDto<RolComponenteDto> request)
			throws DaoException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<Integer> response = null;	
		try {
			response = new ResponseGenericDto<Integer>();
			response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTerminal());
			RolComponente campo = new RolComponente();
			campo.setUsuarioCreador(request.getAuditRequest().getUsuario());
			campo.setFechaCreacion(LocalDateTime.now());
			campo.setTerminalCreacion(request.getAuditRequest().getTerminal());
			campo.setEstadoId(ConstantesConfig.activo);
			campo.setComponenteId(request.getObjectRequest().getComponenteId());
			campo.setRolId(request.getObjectRequest().getRolId());
			
			entityManager.merge(campo);
			entityManager.flush();
			response.setObjectResponse(campo.getId());
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt1);
					  response.getAuditResponse().setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
				  	}
			 
		}catch(Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt1);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		return response; 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditResponseDto actualizarRolComponente(RequestGenericDto<RolComponenteDto> request) throws DaoException {
		
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			
			//1. Consultamos el rolComponente
			RolComponente campo = (RolComponente) entityManager.find(entityClass, request.getObjectRequest().getId());
			if(campo == null) {
				throw new DaoException(messageProvider.codigoErrorIdf7, messageProvider.mensajeErrorIdf7);
			}
			campo.setUsuarioModificador(request.getAuditRequest().getUsuario());
			campo.setFechaModificacion(LocalDateTime.now());
			campo.setTerminalModificador(request.getAuditRequest().getTerminal());
			campo.setComponenteId(request.getObjectRequest().getComponenteId());
			campo.setRolId(request.getObjectRequest().getRolId());
			
			
			entityManager.persist(campo);
			entityManager.flush();
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
					  response.setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
				  	}
			 
		}catch(Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		return response; 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AuditResponseDto eliminarRolComponente(RequestGenericDto<Integer> request) throws DaoException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		AuditResponseDto response = null;	
		try {
			response = new AuditResponseDto();
			response.setTransaccionId(request.getAuditRequest().getTerminal());
			//1. Consultamos el campo 
			RolComponente campo = (RolComponente) entityManager.find(entityClass, request.getObjectRequest());
			if(campo == null) {
				throw new DaoException(messageProvider.codigoErrorIdf7, messageProvider.mensajeErrorIdf7);
			}
			campo.setUsuarioModificador(request.getAuditRequest().getUsuario());
			campo.setFechaModificacion(LocalDateTime.now());
			campo.setTerminalModificador(request.getAuditRequest().getTerminal());
			campo.setEstadoId(ConstantesConfig.eliminado);
			
			entityManager.persist(campo);
			entityManager.flush();
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
			
		}catch(PersistenceException e) {
			  Throwable th = e.getCause();
				  if(th.getCause() instanceof SQLException) {
					  SQLException cause = (SQLException) th.getCause();
					  response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
					  response.setMensajeRespuesta( MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, cause.getMessage()));
				  	}
			 
		}catch(Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt1);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt1, clase, metodo, e.getStackTrace()[0].getLineNumber(), tableName, e.getMessage()));
		}
		return response; 
	}

}
