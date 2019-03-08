/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author josep
 *
 */
public class InfoJwt implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private String 			 session;
	private String 			 usuario;
	private String			 tokens;
	private String 			 fechaInicioSession;
	private String 	 		 fechaFinSession;
	private List<AccesosDto> accesosRecursos;

	public InfoJwt() {
		super();
	}
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public List<AccesosDto> getAccesosRecursos() {
		return accesosRecursos;
	}

	public void setAccesosRecursos(List<AccesosDto> accesosRecursos) {
		this.accesosRecursos = accesosRecursos;
	}

	public String getFechaInicioSession() {
		return fechaInicioSession;
	}

	public void setFechaInicioSession(String fechaInicioSession) {
		this.fechaInicioSession = fechaInicioSession;
	}

	public String getFechaFinSession() {
		return fechaFinSession;
	}

	public void setFechaFinSession(String fechaFinSession) {
		this.fechaFinSession = fechaFinSession;
	}

	public String getTokens() {
		return tokens;
	}

	public void setTokens(String tokens) {
		this.tokens = tokens;
	}
	
}
