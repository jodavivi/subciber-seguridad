/**
 * 
 */
package com.subciber.seguridad.util;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subciber.seguridad.base.dto.RequestGenericDto;
import com.subciber.seguridad.exception.GeneralException;
import com.subciber.seguridad.property.MessageProvider;

/**
 * @author josep
 *
 */
@Dependent
public class Utilitario {

	@Inject
	private MessageProvider messageProvider;
	@Inject
	private EncriptacionAES encriptacionAES;

	String clase = Thread.currentThread().getStackTrace()[1].getClassName();
	String metodo = null;

	public <T> String convertObjectToJson(T object) throws GeneralException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		ObjectMapper Obj = new ObjectMapper();
		String jsonStr = "";
		try {
			jsonStr = Obj.writeValueAsString(object);
		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return jsonStr;
	}

	public <T> RequestGenericDto<T> generateRequest(T object, HttpHeaders httpHeaders, UriInfo uriInfo)
			throws GeneralException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		RequestGenericDto<T> request = null;
		try {

			request = new RequestGenericDto<>();
			request.getAuditRequest().setAplicacion(httpHeaders.getHeaderString("aplicacion"));
			request.getAuditRequest().setTerminal(httpHeaders.getHeaderString("terminal"));
			request.getAuditRequest().setTransaccionId(httpHeaders.getHeaderString("transaccionId"));
			request.getAuditRequest().setUsuario("David");
			request.getAuditRequest().setUsuarioId(1);
			request.setObjectRequest(object);

		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return request;
	}

	public String encriptarString(String request) throws GeneralException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		String response = null;
		try {
			response = encriptacionAES.encrypt(request, ConstantesConfig.claveEncripacionAES);
		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));

		}
		return response;
	}

	public List<String> quitarDuplicados(List<String> list) throws GeneralException{
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		List<String> result = new ArrayList<>();
		try {
			HashSet<String> set = new HashSet<>();
			for (String item : list) {

				if (!set.contains(item)) {
					result.add(item);
					set.add(item);
				}
			}
		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt3, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}
		return result;
	}

}
