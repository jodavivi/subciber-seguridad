/**
 * 
 */
package com.subciber.seguridad.client.test;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.client.dto.EmailRequestClientDto;
import com.subciber.seguridad.client.impl.EmailClientImpl;

/**
 * @author josep
 *
 */
public class EnvioEmailTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RequestGenericDto<EmailRequestClientDto> request = new RequestGenericDto<>();
		request.getAuditRequest().setTransaccionId("112121212");
		request.getAuditRequest().setAplicacion("Test");
		request.getAuditRequest().setTerminal("127.0.0.1");
		request.getAuditRequest().setSession("l4QEV0A8K8M4SzT9Dd8JhfEfBnZdsc2ZCeqfeGen;24BLTX/SDrDY+Z1km0WskVyN3Kx3nIN0zWSO0qP1jKQEioXIj5iGRboBsb76keso481KLjXG4CQhUuQ/0tjNL5LwZJvYXU9OIbzMVLp42bPa2J9A20c453tG4/kyrhX+txC5vCrvguGqXUPDu34eVQ==");
		
		EmailRequestClientDto email = new EmailRequestClientDto();
		email.setCorreoDestino("jodavivi@gmail.com");
		email.setAsunto("Prueba");
		email.setCuerpo("Prueba");
		
		request.setObjectRequest(email);
		try {
			EmailClientImpl emailMetodo = new EmailClientImpl();
			AuditResponseDto responseEnvioCorreo = emailMetodo.enviarCorreo(request);
			System.out.println(responseEnvioCorreo);
		}catch(Exception e) {
			System.out.println(e);
		}

	}

}
