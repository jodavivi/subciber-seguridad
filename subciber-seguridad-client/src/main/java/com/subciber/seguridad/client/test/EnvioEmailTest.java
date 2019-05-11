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
		request.getAuditRequest().setSession("3UWfa42g1oNJrvL96MCmWGY_8gE2XVTUfyPVq_Br;HfKvlGoAbRucdtJa6lDMEgZbOfxkrNY93kA7Pe9QdbFcdxr1WfZ7ilRg2fr4L6U0FNURtntLESKf7J828esVQUFLQAqesyXPMlME4CuGek+quNCJwVPShWuaxTlLCiB6GVy9r+e0VVZtDMebr7jjL8/qyD9klCE7UhjW8mTFDmMbfgnqJgkZqKucawpkZw9wJgeioJiH0UWDdd+PmAV/054qGV8VJ3B03oa7S3wGKNe2nybsvJaKrAhFnoh34ifiDuIl4akDIm5Tln9kxv7LR8ZTv2NacwBlwCWtWpZ0+Ov7nRqpsT6lSRivo9VeULeh1LA+6VKYXQJgHCrk6BfSRgL4GBtPdss1zkNd703WbyHbuEEn8rf+QWGqMgshmwE87gGdReUbsCeq2C8xwoXsmsKqxxpId5ohvXB4J1lXsKaKKqxgVeqFa6M2/PCpYmqbkuo9WHGqd0KGGR4uxhefnPzx1I59Ux4RyjZ5d6c64I4323LquiRYZGY51wdeLkdTbtITB3AK0Nc5wFi8ksSWczgPWOMZbn8ALrrHkL6Ny6269+vg+zZkoVQz3BIx/yUR");
		
		EmailRequestClientDto email = new EmailRequestClientDto();
		email.setCorreoDestino("jdvillanuevavillalobos@gmail.com");
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
