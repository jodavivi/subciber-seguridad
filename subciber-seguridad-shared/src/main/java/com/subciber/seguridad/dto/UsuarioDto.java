/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author josep
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer usuarioId;
	private String usuario;
	private String codigo;
	private String email;
	private String imagen;
	private String nombre;
	private String apellido;
	private String tokenUsuario;
	private Integer estadoId;
	private Integer afiliadoId;
	private String codigoAfiliado;
	private Integer cambiarClave;
	private List<Integer> rol;
	
	public UsuarioDto() {
		super();
		rol = new ArrayList<Integer>();
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
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

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public List<Integer> getRol() {
		return rol;
	}

	public void setRol(List<Integer> rol) {
		this.rol = rol;
	}

	public Integer getAfiliadoId() {
		return afiliadoId;
	}

	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
	}

	public Integer getCambiarClave() {
		return cambiarClave;
	}

	public void setCambiarClave(Integer cambiarClave) {
		this.cambiarClave = cambiarClave;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getCodigoAfiliado() {
		return codigoAfiliado;
	}

	public void setCodigoAfiliado(String codigoAfiliado) {
		this.codigoAfiliado = codigoAfiliado;
	}

	@Override
	public String toString() {
		return "UsuarioDto [usuarioId=" + usuarioId + ", usuario=" + usuario + ", codigo=" + codigo + ", email=" + email
				+ ", imagen=" + imagen + ", nombre=" + nombre + ", apellido=" + apellido + ", tokenUsuario="
				+ tokenUsuario + ", estadoId=" + estadoId + ", afiliadoId=" + afiliadoId + ", codigoAfiliado="
				+ codigoAfiliado + ", cambiarClave=" + cambiarClave + ", rol=" + rol + "]";
	}

}
