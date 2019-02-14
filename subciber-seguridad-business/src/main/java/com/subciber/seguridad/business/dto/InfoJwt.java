/**
 * 
 */
package com.subciber.seguridad.business.dto;

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
	private LocalDateTime 	 fechaInicioSession;
	private LocalDateTime 	 fechaFinSession;
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

	public LocalDateTime getFechaInicioSession() {
		return fechaInicioSession;
	}

	public void setFechaInicioSession(LocalDateTime fechaInicioSession) {
		this.fechaInicioSession = fechaInicioSession;
	}

	public LocalDateTime getFechaFinSession() {
		return fechaFinSession;
	}

	public void setFechaFinSession(LocalDateTime fechaFinSession) {
		this.fechaFinSession = fechaFinSession;
	}

}
