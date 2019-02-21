/**
 * 
 */
package com.subciber.seguridad.client.api;

import com.subciber.seguridad.base.dto.AuditResponseDto;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.client.dto.EmailRequestClientDto;
import com.subciber.seguridad.exception.ClientException;

/**
 * @description Interface para enviar correo
 * @author David Villanueva
 * @version 0.1, 18/02/2019
 * @update
 */
public interface EmailClient {

	/**
	 * @param metodo para enviar correo
	 * @return devuelve objeto de auditoria
	 * @throws ClientException
	 */
	public abstract AuditResponseDto enviarCorreo(RequestGenericDto<EmailRequestClientDto> request) throws ClientException;
	
}
