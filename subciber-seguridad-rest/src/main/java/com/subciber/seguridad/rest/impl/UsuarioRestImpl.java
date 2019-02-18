/**
 * 
 */
package com.subciber.seguridad.rest.impl;

import java.text.MessageFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.UsuarioBusiness;
import com.subciber.seguridad.business.dto.UsuarioDetalleDto;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.GeneralException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.rest.api.UsuarioRest;
import com.subciber.seguridad.util.Utilitario;

/**
 * @description Implementacion de la interface de UsuarioRest
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
@Path("/usuario")
public class UsuarioRestImpl implements UsuarioRest{

	@Inject
	private MessageProvider messageProvider;
	@Inject
	private Utilitario utilitario;
	@Context
	private HttpHeaders httpHeaders;
	@Context
	private UriInfo uriInfo;
	@Context 
	private HttpServletRequest httpServletRequest;
	@Inject
	private UsuarioBusiness usuarioBusiness;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@POST
	@Path("/")
	@Produces("application/json")	
	@Override
	public Response crearUsuario(UsuarioDetalleDto request) {
	
		ResponseGenericDto<UsuarioDetalleDto> response = new ResponseGenericDto<UsuarioDetalleDto>();
		ResponseGenericDto<UsuarioDetalleDto>  crearUsuarioResponse = null;
		RequestGenericDto<UsuarioDetalleDto> requestCrearUsuario = null;
		try {
			requestCrearUsuario = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestCrearUsuario.getAuditRequest().getTransaccionId());
			crearUsuarioResponse = usuarioBusiness.crearUsuario(requestCrearUsuario);
			response.setObjectResponse(crearUsuarioResponse.getObjectResponse());
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
			response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
		} catch (BusinessException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (GeneralException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3,
					clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}  

		return Response.status(200).entity(response).build();
	}

}
