/**
 * 
 */
package com.subciber.seguridad.business.impl;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.GrupoComponenteFaltanteRxBusiness;
import com.subciber.seguridad.business.dto.ResponseGrupoComponenteFaltanteDto;
import com.subciber.seguridad.dao.api.GrupoComponenteFaltanteRxDao;
import com.subciber.seguridad.dto.GrupoComponenteFiltroDto;
import com.subciber.seguridad.entity.VGrupoComponenteFaltate;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.DaoException;
import com.subciber.seguridad.property.MessageProvider;

/**
 * @description implementacion de la interface GrupoComponenteFaltanteRxBusiness
 * @author David Villanueva
 * @version 0.1, 04/08/2019
 * @update
 */
@Dependent
public class GrupoComponenteFaltanteRxBusinessImpl implements GrupoComponenteFaltanteRxBusiness, Serializable {

	private static final long serialVersionUID = 1L;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	long timeStart = 0;
	String transactionId = null;
	
	@Inject
    private MessageProvider messageProvider;
	@EJB
	private GrupoComponenteFaltanteRxDao grupoComponenteFaltanteRxDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResponseGenericDto<ResponseGrupoComponenteFaltanteDto> consultarGrupoComponenteFaltante(
			RequestGenericDto<GrupoComponenteFiltroDto> request) throws BusinessException {
		 
		ResponseGenericDto<ResponseGrupoComponenteFaltanteDto> response = null;
		try {
			timeStart 		= System.currentTimeMillis();
			transactionId 	= request.getAuditRequest().getTransaccionId();
			metodo 			= Thread.currentThread().getStackTrace()[1].getMethodName();
			response 		= new ResponseGenericDto<ResponseGrupoComponenteFaltanteDto>();
			response.getAuditResponse().setTransaccionId(request.getAuditRequest().getTransaccionId());
			
			//1. Consultamos los componentes
			List<VGrupoComponenteFaltate> consultarGrupoComponenteResponse = grupoComponenteFaltanteRxDao.consultarGrupoComponenteFaltante(request.getObjectRequest());
			
			if(consultarGrupoComponenteResponse == null || consultarGrupoComponenteResponse.size() == 0) {
				throw new  DaoException(messageProvider.codigoErrorIdf2, MessageFormat.format(messageProvider.mensajeErrorIdf2, metodo));
			}
			
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			ResponseGrupoComponenteFaltanteDto objectResponse = new ResponseGrupoComponenteFaltanteDto();
			objectResponse.setComponentes(consultarGrupoComponenteResponse);
			response.setObjectResponse(objectResponse);
			
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
