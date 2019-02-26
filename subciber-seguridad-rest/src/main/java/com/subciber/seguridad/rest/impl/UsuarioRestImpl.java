/**
 * 
 */
package com.subciber.seguridad.rest.impl;

import java.text.MessageFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.EliminarObjetoDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.UsuarioBusiness;
import com.subciber.seguridad.dto.UsuarioActualizacionDto;
import com.subciber.seguridad.dto.UsuarioDetalleDto;
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

	@DELETE
	@Path("/")
	@Produces("application/json")	
	@Override
	public Response eliminarUsuario(EliminarObjetoDto request) {
		
		AuditResponseDto response = null;
		RequestGenericDto<EliminarObjetoDto> requestCrearUsuario = null;
		
		try {
			response = new AuditResponseDto();
			requestCrearUsuario = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.setTransaccionId(requestCrearUsuario.getAuditRequest().getTransaccionId());
			AuditResponseDto responseEliminarUsuario = usuarioBusiness.eliminarUsuario(requestCrearUsuario);
			response.setCodigoRespuesta(responseEliminarUsuario.getCodigoRespuesta());
			response.setMensajeRespuesta(responseEliminarUsuario.getMensajeRespuesta());
		} catch (BusinessException e) {
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (GeneralException e) {
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3,
					clase, metodo, e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}  

		return Response.status(200).entity(response).build();
	}

	@PUT
	@Path("/")
	@Produces("application/json")	
	@Override
	public Response actualizarUsuario(UsuarioActualizacionDto request) {
		
		ResponseGenericDto<UsuarioActualizacionDto> response = new ResponseGenericDto<UsuarioActualizacionDto>();
		AuditResponseDto actualizarUsuarioResponse = null;
		RequestGenericDto<UsuarioActualizacionDto> requestActualizarUsuario = null;
		try {
			requestActualizarUsuario = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestActualizarUsuario.getAuditRequest().getTransaccionId());
			actualizarUsuarioResponse = usuarioBusiness.actualizarUsuario(requestActualizarUsuario);
			response.getAuditResponse().setCodigoRespuesta(actualizarUsuarioResponse.getCodigoRespuesta());
			response.getAuditResponse().setMensajeRespuesta(actualizarUsuarioResponse.getMensajeRespuesta());
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
