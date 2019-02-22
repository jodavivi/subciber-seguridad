/**
 * 
 */
package com.subciber.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author josep
 *
 */
@Entity
@Table(schema="\"Seguridad\"", name="\"VUsuario\"")
public class VUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"EstadoId\"")
	private Integer estadoId;

	@Column(name="\"Estado\"")
	private String estado;

	@Column(name="\"Codigo\"")
	private String codigo;

	@Column(name="\"Usuario\"")
	private String usuario;

	@Column(name="\"Email\"")
	private String email;
	
	@Column(name="\"Imagen\"")
	private String imagen;

	@Column(name="\"AplicacionId\"")
	private Integer aplicacionId;

	@Column(name="\"Nombre\"")
	private String nombre;

	@Column(name="\"Apellido\"")
	private String apellido;

	public VUsuario() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
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

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
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

	@Override
	public String toString() {
		return "VUsuario [id=" + id + ", estadoId=" + estadoId + ", estado=" + estado + ", codigo=" + codigo
				+ ", usuario=" + usuario + ", email=" + email + ", imagen=" + imagen + ", aplicacionId=" + aplicacionId
				+ ", nombre=" + nombre + ", apellido=" + apellido + "]";
	}

}
