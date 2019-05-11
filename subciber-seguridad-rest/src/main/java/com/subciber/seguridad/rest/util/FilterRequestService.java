package com.subciber.seguridad.rest.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.base.dto.ResponseGenericDto;
import com.subciber.seguridad.business.api.RepositorioJwt;
import com.subciber.seguridad.dto.EstructuraTokensDto;
import com.subciber.seguridad.dto.FiltroTokensDto;
import com.subciber.seguridad.property.MessageProvider;

@Provider
@PreMatching
public class FilterRequestService implements ContainerRequestFilter {

	@Inject
	private RepositorioJwt repositorioJwt;
	@Inject
    private MessageProvider messageProvider;
	
	private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
	        Arrays.asList("", "/autenticacion","/usuario/recuperarcuenta")));
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		String path 		= requestContext.getUriInfo().getPath();
		String session 		= requestContext.getHeaderString("tokens");
		boolean loggedIn 	= (session != null && session != "" && session.length() > 0);
	    boolean allowedPath = ALLOWED_PATHS.contains(path);
	    
	    if (!allowedPath && loggedIn) {
	    	
	    	try {
	    		
	    		RequestGenericDto<FiltroTokensDto> tokens = new RequestGenericDto<FiltroTokensDto>();
    	 		tokens.getAuditRequest().setTransaccionId(requestContext.getHeaderString("transaccionId"));
    	 		FiltroTokensDto inputTokens = new FiltroTokensDto();
    	 		inputTokens.setTokens(session);
    	 		tokens.setObjectRequest(inputTokens);
    	 		ResponseGenericDto<EstructuraTokensDto> validarTokensResponse = repositorioJwt.validarTokens(tokens);
    	 		 if(validarTokensResponse.getAuditResponse().getCodigoRespuesta() != messageProvider.codigoExito) {
    	 	    	 requestContext.abortWith(
    		                 Response.status(Response.Status.UNAUTHORIZED)
    		         .entity("No tiene permisos para utilizar este recurso")
    		                 .build());
    	 		 }else {
    	 			requestContext.getHeaders().add("tokens2", validarTokensResponse.getObjectResponse().getNuevoTokens());
    		    	requestContext.getHeaders().add("usuario", validarTokensResponse.getObjectResponse().getUsuario());
    		    	requestContext.getHeaders().add("usuarioId", validarTokensResponse.getObjectResponse().getUsuarioId().toString());
    		    	
    	 		 }
	    		
	    	}catch(Exception e) {
	    		requestContext.abortWith(
		                 Response.status(Response.Status.UNAUTHORIZED)
		         .entity("No tiene permisos para utilizar este recurso")
		                 .build());
	    	}
	
	     }else {
	    	 
	     }
		
	}

}
