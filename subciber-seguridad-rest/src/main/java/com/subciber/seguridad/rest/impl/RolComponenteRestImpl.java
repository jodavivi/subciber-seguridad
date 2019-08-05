package com.subciber.seguridad.rest.impl;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.RolComponenteFaltanteRxBusiness;
import com.subciber.seguridad.business.api.RolComponenteRxBusiness;
import com.subciber.seguridad.business.api.RolComponenteTxBusiness;
import com.subciber.seguridad.business.dto.ResponseRolComponenteFaltanteDto;
import com.subciber.seguridad.dao.dto.ResponseRolComponenteDto;
import com.subciber.seguridad.dto.RequestDeleteObjectDto;
import com.subciber.seguridad.dto.RolComponenteDto;
import com.subciber.seguridad.dto.RolComponenteFiltroDto;
import com.subciber.seguridad.dto.RolComponenteRequestDto;
import com.subciber.seguridad.exception.GeneralException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.rest.api.RolComponenteRest;
import com.subciber.seguridad.util.Utilitario;

/**
 * @description Implementacion de la interface de RolComponenteRest
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
@Path("/rolcomponente")
public class RolComponenteRestImpl implements RolComponenteRest, Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private MessageProvider messageProvider;
	@Inject
	private Utilitario utilitario;
	@Context
	private HttpHeaders httpHeaders;
	@Context
	private UriInfo uriInfo;
	@Inject
	private RolComponenteRxBusiness rolComponenteRxBusiness;
	@Inject
	private RolComponenteTxBusiness rolComponenteTxBusiness;
	@Inject
	private RolComponenteFaltanteRxBusiness rolComponenteFaltanteRxBusiness;
	
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@GET
	@Path("/")
	@Produces("application/json")
	@Override
	public Response consultarRolComponente() {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<ResponseRolComponenteDto> response = null;
		RequestGenericDto<RolComponenteFiltroDto> requestConsultar = null;
		RolComponenteFiltroDto requestFiltro = null; 
		try {
			requestFiltro = new RolComponenteFiltroDto();
			response = new ResponseGenericDto<ResponseRolComponenteDto>();
			requestConsultar = utilitario.generateRequest(requestFiltro, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestConsultar.getAuditRequest().getTransaccionId());
			ResponseGenericDto<ResponseRolComponenteDto> responseConsultarRolComponente = rolComponenteRxBusiness.consultarRolComponente(requestConsultar);
			response = responseConsultarRolComponente;

		} catch (GeneralException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo,
					e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/{rolId}")
	@Produces("application/json")
	@Override
	public Response consultarRolComponentePorRol() {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<ResponseRolComponenteDto> response = null;
		RequestGenericDto<RolComponenteFiltroDto> requestConsultar = null;
		RolComponenteFiltroDto requestFiltro = null; 
		try {
			requestFiltro = new RolComponenteFiltroDto();
			requestFiltro.setIdRol(Integer.valueOf(uriInfo.getPathParameters().getFirst("rolId")));
			response = new ResponseGenericDto<ResponseRolComponenteDto>();
			requestConsultar = utilitario.generateRequest(requestFiltro, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestConsultar.getAuditRequest().getTransaccionId());
			ResponseGenericDto<ResponseRolComponenteDto> responseConsultarRolComponente = rolComponenteRxBusiness.consultarRolComponente(requestConsultar);
			response = responseConsultarRolComponente;

		} catch (GeneralException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo,
					e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/faltante/{rolId}")
	@Produces("application/json")
	@Override
	public Response consultarRolComponentFaltante() {

		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<ResponseRolComponenteFaltanteDto> response = null;
		RequestGenericDto<RolComponenteFiltroDto> requestConsultar = null;
		RolComponenteFiltroDto requestFiltro = null; 
		try {
			requestFiltro = new RolComponenteFiltroDto();
			requestFiltro.setIdRol(Integer.valueOf(uriInfo.getPathParameters().getFirst("rolId")));
			response = new ResponseGenericDto<ResponseRolComponenteFaltanteDto>();
			requestConsultar = utilitario.generateRequest(requestFiltro, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestConsultar.getAuditRequest().getTransaccionId());
			ResponseGenericDto<ResponseRolComponenteFaltanteDto> responseConsultarGrupoComponente = rolComponenteFaltanteRxBusiness.consultarRolComponenteFaltante(requestConsultar);
			response = responseConsultarGrupoComponente;

		} catch (GeneralException e) {
			response.getAuditResponse().setCodigoRespuesta(e.getCodigo());
			response.getAuditResponse().setMensajeRespuesta(e.getMensaje());
		} catch (Exception e) {
			response.getAuditResponse().setCodigoRespuesta(messageProvider.codigoErrorIdt3);
			response.getAuditResponse().setMensajeRespuesta(MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo,
					e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return Response.status(200).entity(response).encoding("UTF-8").build();
	}
	
	@POST
	@Path("/{rolId}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response registrarRolComponente(RolComponenteRequestDto request) {
		ResponseGenericDto<Integer> response = null;
		RequestGenericDto<RolComponenteRequestDto> requestRolComponente = null;
		try {
			response = new ResponseGenericDto<Integer>(); 
			request.setRolId(Integer.valueOf(uriInfo.getPathParameters().getFirst("rolId")));
			requestRolComponente = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestRolComponente.getAuditRequest().getTransaccionId());
			response = rolComponenteTxBusiness.registrarRolComponente(requestRolComponente);
 
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

	@PUT
	@Path("/{rolId}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response actualizarRolComponente(RolComponenteDto request) {
		 
		AuditResponseDto response = null;
		RequestGenericDto<RolComponenteDto> requestCampoInput = null;
		try {
			response = new AuditResponseDto(); 
			request.setRolId(Integer.valueOf(uriInfo.getPathParameters().getFirst("rolId")));
			request.setId(Integer.parseInt(uriInfo.getPathParameters().getFirst("campoId")));
			requestCampoInput = utilitario.generateRequest(request, httpHeaders, uriInfo);
			
			response = rolComponenteTxBusiness.actualizarRolComponente(requestCampoInput);
 
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

	@DELETE
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response eliminarRolComponente(RequestDeleteObjectDto request) {
		 
		AuditResponseDto response = null;
		RequestGenericDto<RequestDeleteObjectDto> requestCampo = null;
		try {
			response = new AuditResponseDto(); 
			requestCampo = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response = rolComponenteTxBusiness.eliminarRolComponente(requestCampo);
			if (response.getCodigoRespuesta() != messageProvider.codigoExito) {
				response.setCodigoRespuesta(response.getCodigoRespuesta());
				response.setMensajeRespuesta(response.getMensajeRespuesta());
			}
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

	@DELETE
	@Path("/{rolId}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response eliminarRolComponenteAll() {
		 
		AuditResponseDto response = null;
		RequestGenericDto<Integer> requestCampo = null;
		try {
			response = new AuditResponseDto(); 
			Integer rolId = Integer.valueOf(uriInfo.getPathParameters().getFirst("rolId"));
			requestCampo = utilitario.generateRequest(rolId, httpHeaders, uriInfo);
			response = rolComponenteTxBusiness.eliminarRolComponenteAll(requestCampo);
			if (response.getCodigoRespuesta() != messageProvider.codigoExito) {
				response.setCodigoRespuesta(response.getCodigoRespuesta());
				response.setMensajeRespuesta(response.getMensajeRespuesta());
			}
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
}
