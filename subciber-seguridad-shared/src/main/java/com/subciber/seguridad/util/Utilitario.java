/**
 * 
 */
package com.subciber.seguridad.util;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subciber.seguridad.base.dto.DatosTokenDto;
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
					MessageFormat.format(messageProvider.mensajeErrorIdt4, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return jsonStr;
	}

	public <T> RequestGenericDto<T> generateRequestAunteticar(T object, HttpHeaders httpHeaders, UriInfo uriInfo)
			throws GeneralException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		RequestGenericDto<T> response = null;
		try {
		 
			 response = new RequestGenericDto<>();
			 if(isNullOrEmpty(httpHeaders.getHeaderString("transaccionId"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"1"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("aplicacion"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"2"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("tokens"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"3"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("terminal"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"4"));
			 }
			 response.getAuditRequest().setAplicacion(httpHeaders.getHeaderString("aplicacion"));
			 response.getAuditRequest().setTerminal(httpHeaders.getHeaderString("terminal"));
			 response.getAuditRequest().setTransaccionId(httpHeaders.getHeaderString("transaccionId"));
			 response.getAuditRequest().setUsuario(httpHeaders.getHeaderString("tokens"));
			 response.setObjectRequest(object);

		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt4, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return response;
	}

	public <T> RequestGenericDto<T> generateRequest(T object, HttpHeaders httpHeaders, UriInfo uriInfo)
			throws GeneralException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		RequestGenericDto<T> response = null;
		try {
			 String tokenSession = "";
			 
			 response = new RequestGenericDto<>();
			 if(isNullOrEmpty(httpHeaders.getHeaderString("transaccionId"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"1"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("aplicacion"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"2"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("tokens"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"3"));
			 }
			 if(isNullOrEmpty(httpHeaders.getHeaderString("terminal"))){
				 throw new  GeneralException(messageProvider.codigoErrorIdf3, MessageFormat.format(messageProvider.mensajeErrorIdf3,"4"));
			 }
			 tokenSession = httpHeaders.getHeaderString("tokens");
			 DatosTokenDto responseToken = validarTokens(tokenSession);
			 
			 response.getAuditRequest().setAplicacion(httpHeaders.getHeaderString("aplicacion"));
			 response.getAuditRequest().setTerminal(httpHeaders.getHeaderString("terminal"));
			 response.getAuditRequest().setTransaccionId(httpHeaders.getHeaderString("transaccionId"));
			 response.getAuditRequest().setUsuario(responseToken.getUsuario());
			 response.getAuditRequest().setUsuarioId(Integer.parseInt(responseToken.getUsuarioId()));
			 response.getAuditRequest().setSession(tokenSession);
			 response.setObjectRequest(object);

		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt4, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}

		return response;
	}
	
	public String encriptarString(String request) throws GeneralException {
		metodo = Thread.currentThread().getStackTrace()[1].getMethodName();
		String response = null;
		try {
			response = encriptacionAES.encrypt(request, ConstantesConfig.claveEncripacionAES);
		} catch (Exception e) {
			throw new GeneralException(messageProvider.codigoErrorIdt4,
					MessageFormat.format(messageProvider.mensajeErrorIdt4, clase, metodo,
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
					MessageFormat.format(messageProvider.mensajeErrorIdt4, clase, metodo,
							e.getStackTrace()[0].getLineNumber(), e.getMessage()));
		}
		return result;
	}

	public boolean isNullOrEmpty(String str) {
		 boolean ok = true;
		 try {
	        if(str != null && !str.trim().isEmpty())
	        	ok = false;
		 }catch(Exception e) {
			 ok = false;
		 }
	        return ok;
	    }
	
	public DatosTokenDto validarTokens(String tokens) throws GeneralException{
		
		DatosTokenDto response = null;
		try {
			 response = new DatosTokenDto();
			 if(isNullOrEmpty(tokens)){
				throw new  GeneralException(messageProvider.codigoErrorIdf5, MessageFormat.format(messageProvider.mensajeErrorIdf5,"No existe el token"));
			 }
			
			 String[] parts = tokens.split(";");
			 String session = parts[0];  //Session
			 String datosUsuario = parts[1];  //datos de expiracion y datos de usuario
			 String datosToken = encriptacionAES.decrypt(datosUsuario, ConstantesConfig.claveEncripacionAES);
			 String[] datos =  datosToken.split(";");
			 LocalDateTime fechaActual = LocalDateTime.now(); 
			 LocalDateTime fechaSessionExpiracion = LocalDateTime.parse(datos[4]); 
			 if(fechaActual.compareTo(fechaSessionExpiracion) > 0) {
				 throw new  GeneralException(messageProvider.codigoErrorIdf5, MessageFormat.format(messageProvider.mensajeErrorIdf5,"Token Vencido"));
			 }
			 try {
				 String acceso = datos[5];
				 if(isNullOrEmpty(acceso)) {
					 throw new  GeneralException(messageProvider.codigoErrorIdf5, MessageFormat.format(messageProvider.mensajeErrorIdf5,"Accesos no configurados"));
				 }
			 }catch(Exception e) {
				 throw new  GeneralException(messageProvider.codigoErrorIdf5, MessageFormat.format(messageProvider.mensajeErrorIdf5,"Accesos no configurados"));
			 }
			 response.setSession(session);
			 response.setUsuarioId(datos[0]);
			 response.setUsuario(datos[1]);
			 response.setCorreo(datos[2]);
			 response.setAccesos(datos[5]);
			 
		}catch(Exception e) {
			throw new  GeneralException(messageProvider.codigoErrorIdf5, MessageFormat.format(messageProvider.mensajeErrorIdf5,e.getMessage()));
		}
		
		return response; 
	}
}
