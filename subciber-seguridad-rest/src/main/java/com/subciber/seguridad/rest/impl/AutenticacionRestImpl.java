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
import com.subciber.seguridad.business.api.AutenticacionBusiness;
import com.subciber.seguridad.business.dto.InfoUsuarioDto;
import com.subciber.seguridad.dto.AutenticacionFiltroDto;
import com.subciber.seguridad.exception.BusinessException;
import com.subciber.seguridad.exception.GeneralException;
import com.subciber.seguridad.property.MessageProvider;
import com.subciber.seguridad.rest.api.AutenticacionRest;
import com.subciber.seguridad.util.Utilitario;

/**
 * @description Implementacion de la interface de AutenticacionRest
 * @author David Villanueva
 * @version 0.1, 11/02/2019
 * @update
 */
@Path("/autenticacion")
public class AutenticacionRestImpl implements AutenticacionRest {

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
	private AutenticacionBusiness autenticacionBusiness;

	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	@POST
	@Path("/")
	@Produces("application/json")	
	@Override
	public Response autenticarUsuario(AutenticacionFiltroDto request) {
		ResponseGenericDto<InfoUsuarioDto> response = new ResponseGenericDto<InfoUsuarioDto>();
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		String session = "";
		try {
			session = httpServletRequest.getSession().getId();
			RequestGenericDto<AutenticacionFiltroDto> requestGenerarico = utilitario.generateRequestAunteticar(request,
					httpHeaders, uriInfo);
			response.getAuditResponse().setTransaccionId(requestGenerarico.getAuditRequest().getTransaccionId());
			//1. Validamos la autenticacion del usuario
			requestGenerarico.getObjectRequest().setSession(session);
			ResponseGenericDto<InfoUsuarioDto> usuario = autenticacionBusiness.autenticarUsuario(requestGenerarico);
			
			response.setObjectResponse(usuario.getObjectResponse());
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

		return Response.status(200).header("sToken", session).entity(response).build();
	}

}
