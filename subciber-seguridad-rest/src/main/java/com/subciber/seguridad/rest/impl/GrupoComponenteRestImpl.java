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
import com.subciber.seguridad.business.api.GrupoComponenteFaltanteRxBusiness;
import com.subciber.seguridad.business.api.GrupoComponenteRxBusiness;
import com.subciber.seguridad.business.api.GrupoComponenteTxBusiness;
import com.subciber.seguridad.business.dto.ResponseGrupoComponenteFaltanteDto;
import com.subciber.seguridad.dao.dto.ResponseGrupoComponenteDto;
import com.subciber.seguridad.dto.GrupoComponenteDto;
import com.subciber.seguridad.dto.GrupoComponenteFiltroDto;
import com.subciber.seguridad.dto.GrupoComponenteRequestDto;
import com.subciber.seguridad.dto.RequestDeleteObjectDto;
import com.subciber.seguridad.exception.GeneralException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.rest.api.GrupoComponenteRest;
import com.subciber.seguridad.util.Utilitario;

/**
 * @description Implementacion de la interface de GrupoComponenteRest
 * @author David Villanueva
 * @version 0.1, 16/02/2019
 * @update
 */
@Path("/grupocomponente")
public class GrupoComponenteRestImpl implements GrupoComponenteRest, Serializable {

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
	private GrupoComponenteRxBusiness grupoComponenteRxBusiness;
	@Inject
	private GrupoComponenteFaltanteRxBusiness grupoComponenteFaltanteRxBusiness;
	@Inject
	private GrupoComponenteTxBusiness grupoComponenteTxBusiness;
	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
	@GET
	@Path("/")
	@Produces("application/json")
	@Override
	public Response consultarGrupoComponente() {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<ResponseGrupoComponenteDto> response = null;
		RequestGenericDto<GrupoComponenteFiltroDto> requestConsultar = null;
		GrupoComponenteFiltroDto requestFiltro = null; 
		try {
			requestFiltro = new GrupoComponenteFiltroDto();
			response = new ResponseGenericDto<ResponseGrupoComponenteDto>();
			requestConsultar = utilitario.generateRequest(requestFiltro, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestConsultar.getAuditRequest().getTransaccionId());
			ResponseGenericDto<ResponseGrupoComponenteDto> responseConsultarRolComponente = grupoComponenteRxBusiness.consultarGrupoComponente(requestConsultar);
			response = responseConsultarRolComponente;

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
	
	@GET
	@Path("/faltante/{grupoId}")
	@Produces("application/json")
	@Override
	public Response consultarGrupoComponentFaltante() {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<ResponseGrupoComponenteFaltanteDto> response = null;
		RequestGenericDto<GrupoComponenteFiltroDto> requestConsultar = null;
		GrupoComponenteFiltroDto requestFiltro = null; 
		try {
			requestFiltro = new GrupoComponenteFiltroDto();
			requestFiltro.setIdGrupo(Integer.valueOf(uriInfo.getPathParameters().getFirst("grupoId")));
			response = new ResponseGenericDto<ResponseGrupoComponenteFaltanteDto>();
			requestConsultar = utilitario.generateRequest(requestFiltro, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestConsultar.getAuditRequest().getTransaccionId());
			ResponseGenericDto<ResponseGrupoComponenteFaltanteDto> responseConsultarGrupoComponente = grupoComponenteFaltanteRxBusiness.consultarGrupoComponenteFaltante(requestConsultar);
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
	
	@GET
	@Path("/{grupoId}")
	@Produces("application/json")
	@Override
	public Response consultarGrupoComponentPorGrupo() {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ResponseGenericDto<ResponseGrupoComponenteDto> response = null;
		RequestGenericDto<GrupoComponenteFiltroDto> requestConsultar = null;
		GrupoComponenteFiltroDto requestFiltro = null; 
		try {
			requestFiltro = new GrupoComponenteFiltroDto();
			requestFiltro.setIdGrupo(Integer.valueOf(uriInfo.getPathParameters().getFirst("grupoId")));
			response = new ResponseGenericDto<ResponseGrupoComponenteDto>();
			requestConsultar = utilitario.generateRequest(requestFiltro, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestConsultar.getAuditRequest().getTransaccionId());
			ResponseGenericDto<ResponseGrupoComponenteDto> responseConsultarGrupoComponente = grupoComponenteRxBusiness.consultarGrupoComponente(requestConsultar);
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
	@Path("/{grupoId}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response registrarGrupoComponente(GrupoComponenteRequestDto request) {
		 
		ResponseGenericDto<Integer> response = null;
		RequestGenericDto<GrupoComponenteRequestDto> requestGrupoComponente = null;
		try {
			response = new ResponseGenericDto<Integer>(); 
			request.setGrupoId(Integer.valueOf(uriInfo.getPathParameters().getFirst("grupoId")));
			requestGrupoComponente = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestGrupoComponente.getAuditRequest().getTransaccionId());
			response = grupoComponenteTxBusiness.registrarGrupoComponente(requestGrupoComponente);
 
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
	@Path("/{grupoId}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response actualizarGrupoComponente(GrupoComponenteDto request) {
		AuditResponseDto response = null;
		RequestGenericDto<GrupoComponenteDto> requestCampoInput = null;
		try {
			response = new AuditResponseDto(); 
			request.setGrupoId(Integer.valueOf(uriInfo.getPathParameters().getFirst("grupoId")));
			requestCampoInput = utilitario.generateRequest(request, httpHeaders, uriInfo);
			
			response = grupoComponenteTxBusiness.actualizarGrupoComponente(requestCampoInput);
 
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
	public Response eliminarGrupoComponente(RequestDeleteObjectDto request) {
		
		AuditResponseDto response = null;
		RequestGenericDto<RequestDeleteObjectDto> requestCampo = null;
		try {
			response = new AuditResponseDto(); 
			requestCampo = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response = grupoComponenteTxBusiness.eliminarGrupoComponente(requestCampo);
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
	@Path("/{grupoId}")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response eliminarGrupoComponenteAll() {
		 
		AuditResponseDto response = null;
		RequestGenericDto<Integer> requestCampo = null;
		try {
			response = new AuditResponseDto(); 
			Integer grupoId = Integer.valueOf(uriInfo.getPathParameters().getFirst("grupoId"));
			requestCampo = utilitario.generateRequest(grupoId, httpHeaders, uriInfo);
			response = grupoComponenteTxBusiness.eliminarGrupoComponenteAll(requestCampo);
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
