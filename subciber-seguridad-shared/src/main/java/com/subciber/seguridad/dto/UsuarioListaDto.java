/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author josep
 *
 */
public class UsuarioListaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String usuario;
	private String codigoUsuario;
	private String emailUsuario;
	private String imagenUsuario;
	private String nombre;
	private String apellido;
	private List<Integer> rol;
	
	public UsuarioListaDto() {
		super();
		rol = new ArrayList<Integer>();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public List<Integer> getRol() {
		return rol;
	}
	public void setRol(List<Integer> rol) {
		this.rol = rol;
	}

}
