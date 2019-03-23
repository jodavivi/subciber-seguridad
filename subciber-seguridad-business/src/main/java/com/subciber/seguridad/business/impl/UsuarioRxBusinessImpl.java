package com.subciber.seguridad.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.UsuarioRxBusiness;
import com.subciber.seguridad.business.dto.ResponseUsuarioDetalleDto;
import com.subciber.seguridad.dao.api.UsuarioRolRxDao;
import com.subciber.seguridad.dao.api.UsuarioRxDao;
import com.subciber.seguridad.dao.dto.ResponseUsuarioDto;
import com.subciber.seguridad.dto.UsuarioFiltroDto;
import com.subciber.seguridad.entity.VUsuario;
import com.subciber.seguridad.entity.VUsuarioRol;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;

@Dependent
public class UsuarioRxBusinessImpl implements UsuarioRxBusiness, Serializable{
	
	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	long timeStart = 0;
	String transactionId = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private UsuarioRxDao usuarioRxDao;
	@EJB
	private UsuarioRolRxDao usuarioRolRxDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<ResponseUsuarioDto> buscarUsuario(RequestGenericDto<UsuarioFiltroDto> request)
			throws BusinessException { 
		ResponseGenericDto<ResponseUsuarioDto> response = null;
		try {
			timeStart 		= System.currentTimeMillis();
			transactionId 	= request.getAuditRequest().getTransaccionId();
			metodo 			= Thread.currentThread().getStackTrace()[1].getMethodName();
			response 		= new ResponseGenericDto<ResponseUsuarioDto>();
			response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			//1. Verificamos si existe el usuario registrado
			List<VUsuario>  responseconsultarUsuario = usuarioRxDao.buscarUsuario(request.getObjectRequest());
			
			if(responseconsultarUsuario == null || responseconsultarUsuario.size() == 0) {
				throw new  DaoException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2, metodo));
			}
			
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			
			ResponseUsuarioDto responseUsuario = new ResponseUsuarioDto();
			responseUsuario.setUsuarios(responseconsultarUsuario);
			response.setObjectResponse(responseUsuario);
		} catch (DaoException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		}  
		
		return response;
	}

	@Override
	public ResponseGenericDto<ResponseUsuarioDetalleDto> buscarUsuarioDetalle(RequestGenericDto<UsuarioFiltroDto> request)
			throws BusinessException {
		ResponseGenericDto<ResponseUsuarioDetalleDto> response = null;
		try {
			timeStart 		= System.currentTimeMillis();
			transactionId 	= request.getAuditRequest().getTransaccionId();
			metodo 			= Thread.currentThread().getStackTrace()[1].getMethodName();
			response 		= new ResponseGenericDto<ResponseUsuarioDetalleDto>();
			response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			//1. Verificamos si existe el usuario registrado
			List<VUsuario>  responseconsultarUsuario = usuarioRxDao.buscarUsuario(request.getObjectRequest());
			
			
			if(responseconsultarUsuario == null || responseconsultarUsuario.size() == 0) {
				throw new  DaoException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2, metodo));
			}
			ResponseUsuarioDetalleDto responseDetalle = new ResponseUsuarioDetalleDto();
			responseDetalle.setUsuario(responseconsultarUsuario.get(0));
			//2. Consultamos los  roles
			UsuarioFiltroDto requestRolUsuario = new UsuarioFiltroDto(); 
			List<VUsuarioRol> consultarRolUsuarioResponse  =  usuarioRolRxDao.consultarRolUsuario(requestRolUsuario);
			if(consultarRolUsuarioResponse != null && responseconsultarUsuario.size() > 0) {
				responseDetalle.setRol(consultarRolUsuarioResponse);
			 
			}
			response.setObjectResponse(responseDetalle);
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			 
		} catch (DaoException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt2);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt2, clase, metodo, e.getStackTrace()[0].getLineNumber(),  e.getMessage()));		
		}  
		
		return response;
	}

}
