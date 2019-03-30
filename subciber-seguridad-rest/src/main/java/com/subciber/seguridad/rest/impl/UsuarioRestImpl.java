/**
 * 
 */
package com.subciber.seguridad.rest.impl;

import java.text.MessageFormat;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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
import com.subciber.seguridad.business.api.UsuarioRxBusiness;
import com.subciber.seguridad.business.dto.ResponseUsuarioDetalleDto;
import com.subciber.seguridad.dao.dto.ResponseUsuarioDto;
import com.subciber.seguridad.dto.UsuarioActualizacionDto;
import com.subciber.seguridad.dto.UsuarioDetalleDto;
import com.subciber.seguridad.dto.UsuarioFiltroDto;
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
public class UsuarioRestImpl implements UsuarioRest {

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
	@Inject
	private UsuarioRxBusiness usuarioRxBusiness;

	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	@GET
	@Path("/")
	@Produces("application/json")
	@Override
	public Response listarUsuario() {
		//nombre=jodadad&usuario=jodad&estadoId=23&fechaCreacion=2019-03-02&correo=jodavivivi@hotmail.com
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<ResponseUsuarioDto> response = null;
		RequestGenericDto<UsuarioFiltroDto> requestBuscarUsuario = null;
		try {
			response = new ResponseGenericDto<ResponseUsuarioDto>();
			UsuarioFiltroDto requestUsuario = new UsuarioFiltroDto();
			requestUsuario.setUsuario(uriInfo.getQueryParameters().getFirst("usuario"));
			requestUsuario.setEstadoId(utilitario.validarIntegerIsNull(uriInfo.getQueryParameters().getFirst("estadoId")));
			requestUsuario.setCorreo(uriInfo.getQueryParameters().getFirst("correo"));
			requestUsuario.setNombre(uriInfo.getQueryParameters().getFirst("nombre"));
			requestUsuario.setApellido(uriInfo.getQueryParameters().getFirst("apellido"));
			
			requestBuscarUsuario = utilitario.generateRequest(requestUsuario, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestBuscarUsuario.getAuditRequest().getTransaccionId());
			response = usuarioRxBusiness.buscarUsuario(requestBuscarUsuario);
			 
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
	
	@GET
	@Path("/{usuarioId}")
	@Produces("application/json")
	@Override
	public Response usuarioDetalle() {
		ResponseGenericDto<ResponseUsuarioDetalleDto> response = null;
		RequestGenericDto<UsuarioFiltroDto> requestBuscarUsuario = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			response = new ResponseGenericDto<ResponseUsuarioDetalleDto>();
			UsuarioFiltroDto requestUsuario = new UsuarioFiltroDto();
			System.out.println(uriInfo.getPathParameters().getFirst("usuarioId"));
			requestUsuario.setUsuarioId(utilitario.validarIntegerIsNull(uriInfo.getPathParameters().getFirst("usuarioId")));
			requestBuscarUsuario = utilitario.generateRequest(requestUsuario, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestBuscarUsuario.getAuditRequest().getTransaccionId());
			response = usuarioRxBusiness.buscarUsuarioDetalle(requestBuscarUsuario);
			 
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
	
	@POST
	@Path("/")
	@Produces("application/json")
	@Override
	public Response crearUsuario(UsuarioDetalleDto request) {

		ResponseGenericDto<UsuarioDetalleDto> response = new ResponseGenericDto<UsuarioDetalleDto>();
		ResponseGenericDto<UsuarioDetalleDto> crearUsuarioResponse = null;
		RequestGenericDto<UsuarioDetalleDto> requestCrearUsuario = null;
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			requestCrearUsuario = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestCrearUsuario.getAuditRequest().getTransaccionId());
			crearUsuarioResponse = usuarioBusiness.crearUsuario(requestCrearUsuario);

			if (crearUsuarioResponse.getAuditResponse().getCodigoRespuesta() == messageProvider.codigoExito) {
				response.setObjectResponse(crearUsuarioResponse.getObjectResponse());
				response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
				response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			} else {
				response.getAuditResponse()
						.setCodigoRespuesta(crearUsuarioResponse.getAuditResponse().getCodigoRespuesta());
				response.getAuditResponse()
						.setMensajeRespuesta(crearUsuarioResponse.getAuditResponse().getMensajeRespuesta());
			}
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
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			response = new AuditResponseDto();
			requestCrearUsuario = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.setTransaccionId(requestCrearUsuario.getAuditRequest().getTransaccionId());
			AuditResponseDto responseEliminarUsuario = usuarioBusiness.eliminarUsuario(requestCrearUsuario);
			response.setCodigoRespuesta(responseEliminarUsuario.getCodigoRespuesta());
			response.setMensajeRespuesta(responseEliminarUsuario.getMensajeRespuesta());

			if (responseEliminarUsuario.getCodigoRespuesta() == messageProvider.codigoExito) {
				response.setCodigoRespuesta(messageProvider.codigoExito);
				response.setMensajeRespuesta(messageProvider.mensajeExito);
			} else {
				response.setCodigoRespuesta(responseEliminarUsuario.getCodigoRespuesta());
				response.setMensajeRespuesta(responseEliminarUsuario.getMensajeRespuesta());
			}

		} catch (GeneralException e) {
			response.setCodigoRespuesta(e.getCodigo());
			response.setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo,
					e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}

	@PUT
	@Path("/")
	@Produces("application/json")
	@Override
	public Response actualizarUsuario(UsuarioActualizacionDto request) {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<UsuarioActualizacionDto> response = new ResponseGenericDto<UsuarioActualizacionDto>();
		AuditResponseDto actualizarUsuarioResponse = null;
		RequestGenericDto<UsuarioActualizacionDto> requestActualizarUsuario = null;
		try {
			requestActualizarUsuario = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestActualizarUsuario.getAuditRequest().getTransaccionId());
			actualizarUsuarioResponse = usuarioBusiness.actualizarUsuario(requestActualizarUsuario);
			if (actualizarUsuarioResponse.getCodigoRespuesta() == messageProvider.codigoExito) {
				response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoExito);
				response.getAuditResponse().setMensajeRespuesta(messageProvider.mensajeExito);
			} else {
				response.getAuditResponse()
						.setCodigoRespuesta(actualizarUsuarioResponse.getCodigoRespuesta());
				response.getAuditResponse()
						.setMensajeRespuesta(actualizarUsuarioResponse.getMensajeRespuesta());
			}
			
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
