/**
 * 
 */
package com.subciber.seguridad.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.RolComponenteTxBusiness;
import com.subciber.seguridad.dao.api.RolComponenteTxDao;
import com.subciber.seguridad.dto.RequestDeleteObjectDto;
import com.subciber.seguridad.dto.RolComponenteDto;
import com.subciber.seguridad.dto.RolComponenteRequestDto;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.util.JAXBUtil;

/**
 * @description implementacion de la interface RolComponenteTxBusiness
 * @author David Villanueva
 * @version 0.1, 11/03/2019
 * @update
 */
@Dependent
public class RolComponenteTxBusinessImpl implements RolComponenteTxBusiness , Serializable {

	private static final long serialVersionUID = 1L;
	static final Logger logger = LoggerFactory.getLogger(RolComponenteTxBusinessImpl.class);
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	long timeStart = 0;
	String transactionId = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private RolComponenteTxDao rolComponenteTxDao;
	
	@Override
	public ResponseGenericDto<Integer> registrarRolComponente(RequestGenericDto<RolComponenteRequestDto> request)
			throws BusinessException {
		ResponseGenericDto<Integer> response = null;

		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			response = new ResponseGenericDto<>();
			response.getAuditResponse().setTransaccionId(transactionId);		
			
			
			for(Integer item : request.getObjectRequest().getComponenteId()) {
				
				RequestGenericDto<RolComponenteDto> componenteDto = new RequestGenericDto<RolComponenteDto>();
				componenteDto.setAuditRequest(request.getAuditRequest());
				RolComponenteDto itemDto = new RolComponenteDto();
				itemDto.setComponenteId(item);
				itemDto.setRolId(request.getObjectRequest().getRolId());
				componenteDto.setObjectRequest(itemDto);
				ResponseGenericDto<Integer>  registrarGrupoComponenteResponse = rolComponenteTxDao.registrarRolComponente(componenteDto);
				
				if(registrarGrupoComponenteResponse.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
					throw new DaoException(registrarGrupoComponenteResponse.getAuditResponse().getCodigoRespuesta(), registrarGrupoComponenteResponse.getAuditResponse().getMensajeRespuesta());
				}
			}
			
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
		}catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		
		return response;
	}
	@Override
	public AuditResponseDto actualizarRolComponente(RequestGenericDto<RolComponenteDto> request)
			throws BusinessException {
		AuditResponseDto response = null;

		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			response = new AuditResponseDto();
			response.setTransaccionId(transactionId);		
			AuditResponseDto  actualizarRolComponenteResponse = rolComponenteTxDao.actualizarRolComponente(request);
			
			if(actualizarRolComponenteResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
				throw new DaoException(actualizarRolComponenteResponse.getCodigoRespuesta(), actualizarRolComponenteResponse.getMensajeRespuesta());
			}
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
		}catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		
		return response;
	}
	@Override
	public AuditResponseDto eliminarRolComponente(RequestGenericDto<RequestDeleteObjectDto> request)
			throws BusinessException {
		AuditResponseDto response = null;

		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			response = new AuditResponseDto();
			response.setTransaccionId(transactionId);		
			
			AuditResponseDto  eliminareliminarRolComponente = null;
			for(Integer item : request.getObjectRequest().getItems()) {
				RequestGenericDto<Integer> requestEliminar = new RequestGenericDto<Integer>();
				requestEliminar.setAuditRequest(request.getAuditRequest());
				requestEliminar.setObjectRequest(item);
				eliminareliminarRolComponente = rolComponenteTxDao.eliminarRolComponente(requestEliminar);
				if(eliminareliminarRolComponente.getCodigoRespuesta() != messageProvider.codigoExito) {
					throw new DaoException(eliminareliminarRolComponente.getCodigoRespuesta(), eliminareliminarRolComponente.getMensajeRespuesta());
				}
			}
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
		}catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		
		return response;
	} 
	
	@Override
	public AuditResponseDto eliminarRolComponenteAll(RequestGenericDto<Integer> request) throws BusinessException {
		
		AuditResponseDto response = null;

		try {
			timeStart = System.currentTimeMillis();
			transactionId = request.getAuditRequest().getTransaccionId();
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			logger.info(MessageFormat.format(messageProvider.logMensajeInicio, transactionId, metodo));
			logger.info(MessageFormat.format(messageProvider.logMensajeInp, transactionId, metodo, JAXBUtil.log(request)));
			response = new AuditResponseDto();
			response.setTransaccionId(transactionId);		
			AuditResponseDto  actualizarRolComponenteResponse = rolComponenteTxDao.eliminarRolComponenteAll(request);
			
			if(actualizarRolComponenteResponse.getCodigoRespuesta() != messageProvider.codigoExito) {
				throw new DaoException(actualizarRolComponenteResponse.getCodigoRespuesta(), actualizarRolComponenteResponse.getMensajeRespuesta());
			}
			response.setCodigoRespuesta(messageProvider.codigoExito);
			response.setMensajeRespuesta(messageProvider.mensajeExito);
		}catch (DaoException e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			logger.error(MessageFormat.format(messageProvider.logMensajeError, transactionId, metodo, e.getMessage()));
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		} finally { 
			logger.info(MessageFormat.format(messageProvider.logMensajeOut, transactionId, metodo, JAXBUtil.log(response)));
			logger.info(MessageFormat.format(messageProvider.logMensajeTime, transactionId,	metodo, (System.currentTimeMillis() - timeStart)));
			logger.info(MessageFormat.format(messageProvider.logMensajeEnd, transactionId, metodo));
		}
		
		return response;
	} 

}
