/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class UsuarioFiltroDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String usuario;
	private String correo;
	private Integer estadoId;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	
}
