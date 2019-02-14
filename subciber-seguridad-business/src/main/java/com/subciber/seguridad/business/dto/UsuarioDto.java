/**
 * 
 */
package com.subciber.seguridad.business.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class UsuarioDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String usuario;
	private String codigoUsuario;
	private String emailUsuario;
	private String imagenUsuario;
	private String nombre;
	private String apellido;
	private String tokenUsuario;
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getImagenUsuario() {
		return imagenUsuario;
	}
	public void setImagenUsuario(String imagenUsuario) {
		this.imagenUsuario = imagenUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTokenUsuario() {
		return tokenUsuario;
	}
	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}
}
