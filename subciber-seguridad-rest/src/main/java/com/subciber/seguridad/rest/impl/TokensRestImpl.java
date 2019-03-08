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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.subciber.seguridad.base.dto.AuditRequestDto;
import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.RepositorioJwt;
import com.subciber.seguridad.dto.EstructuraTokensDto;
import com.subciber.seguridad.dto.FiltroTokensDto;
import com.subciber.seguridad.dto.InfoJwt;
import com.subciber.seguridad.dto.UsuariosLoginDto;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.GeneralException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.rest.api.TokensRest;
import com.subciber.seguridad.util.Utilitario;

/**
 * @description Implementacion de la interface de TokensRest
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Path("/tokens")
public class TokensRestImpl implements TokensRest {

	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;
	
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
	private RepositorioJwt repositorioJwt;
 
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response consultarTokens(FiltroTokensDto request) {
		
		ResponseGenericDto<InfoJwt> response = new ResponseGenericDto<InfoJwt>();
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			RequestGenericDto<FiltroTokensDto> requestGenerarico = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			//1. Validamos la autenticacion del usuario 
			InfoJwt  recuperarUsuarioResponse = repositorioJwt.recuperarUsuario(request.getTokens());
			response.setObjectResponse(recuperarUsuarioResponse);
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

	@POST
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response usuariosLogueados() { 
		
		ResponseGenericDto<UsuariosLoginDto> response = new ResponseGenericDto<UsuariosLoginDto>();
		try {
			metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
			AuditRequestDto requestGenerarico = utilitario.generateSimpleRequest(httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestGenerarico.getTransaccionId());
			
			UsuariosLoginDto usuarioLogueadosResponse  = repositorioJwt.usuarioLogueados();  
			
			response.setObjectResponse(usuarioLogueadosResponse);
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

	@POST
	@Path("/validar")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response validarTokens(FiltroTokensDto request) {
		 
		ResponseGenericDto<EstructuraTokensDto> response = new ResponseGenericDto<EstructuraTokensDto>();
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			RequestGenericDto<FiltroTokensDto> requestGenerarico = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			//1. Validamos la autenticacion del usuario 
			//InfoJwt  recuperarUsuarioResponse = 
			ResponseGenericDto<EstructuraTokensDto> validarTokensResponse = repositorioJwt.validarTokens(requestGenerarico);
			response = validarTokensResponse;
			
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

	@POST
	@Path("/cerrarsesion")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")	
	@Override
	public Response cerrarSesion(FiltroTokensDto request) {
		
		AuditResponseDto response = new AuditResponseDto();
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			RequestGenericDto<FiltroTokensDto> requestGenerarico = utilitario.generateRequest(request, httpHeaders, uriInfo);
			response.setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			//1. Cerramos la session 
			AuditResponseDto eliminarSessionResponse = repositorioJwt.eliminarSession(requestGenerarico);
			response.setCodigoRespuesta(eliminarSessionResponse.getCodigoRespuesta());
			response.setMensajeRespuesta(eliminarSessionResponse.getMensajeRespuesta());
			
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

}
