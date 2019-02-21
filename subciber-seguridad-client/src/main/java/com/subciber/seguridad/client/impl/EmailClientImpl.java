/**
 * 
 */
package com.subciber.seguridad.client.impl;

import java.text.MessageFormat;

import javax.enterprise.context.Dependent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.client.api.EmailClient;
import com.subciber.seguridad.client.dto.EmailRequestClientDto;
import com.subciber.seguridad.client.dto.EmailResponseClientDto;
import com.subciber.seguridad.exception.ClientException;
import com.subciber.seguridad.property.MessageClientService;

/**
 * @author josep
 *
 */
@Dependent
public class EmailClientImpl  implements EmailClient{

	@Override
	public AuditResponseDto enviarCorreo(RequestGenericDto<EmailRequestClientDto> request) throws ClientException {
		
		AuditResponseDto respuesta = null;
		try {
			respuesta = new AuditResponseDto();
			Client client = ClientBuilder.newClient();
			MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
			headers.add("aplicacion", request.getAuditRequest().getAplicacion());
			headers.add("terminal", request.getAuditRequest().getTerminal());
			headers.add("transaccionId", request.getAuditRequest().getTransaccionId());
			headers.add("tokens", request.getAuditRequest().getSession());
			String url = "http://localhost:8088/subciber-configuracion-rest/rest/enviocorreo/";
			Response response = client.target(url).request(MediaType.APPLICATION_JSON).headers(headers).post(Entity.entity(request.getObjectRequest(), MediaType.APPLICATION_JSON));
			
			EmailResponseClientDto respuestaEnvioEmail = response.readEntity(EmailResponseClientDto.class);
			respuesta.setTransaccionId(respuestaEnvioEmail.getTransaccionId());
			respuesta.setCodigoRespuesta(respuestaEnvioEmail.getCodigoRespuesta());
			respuesta.setMensajeRespuesta(respuestaEnvioEmail.getMensajeRespuesta());
	      
			response.close();
		    client.close();
		
		}catch(Exception e) {
			throw new  ClientException(MessageClientService.codigoErrorIdt100, MessageFormat.format(MessageClientService.mensajeErrorIdt100,"EnvioCorreo"));
		}
		return respuesta;
	}

}
