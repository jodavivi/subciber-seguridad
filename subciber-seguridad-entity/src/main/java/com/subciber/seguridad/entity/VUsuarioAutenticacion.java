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
@Table(schema="\"Seguridad\"", name="\"VUsuarioAutenticacion\"")
public class VUsuarioAutenticacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"Id\"")
	private Integer id;
	
	@Column(name="\"EstadoId\"")
	private Integer estadoId;
	
	@Column(name="\"Estado\"")
	private String estado;

	@Column(name="\"Usuario\"")
	private String usuario;
	
	@Column(name="\"Email\"")
	private String email;
	
	@Column(name="\"AplicacionId\"")
	private Integer aplicacionId;

	@Column(name="\"Clave\"")
	private String clave;


	public VUsuarioAutenticacion() {
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

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}


	@Override
	public String toString() {
		return "VUsuarioAutenticacion [id=" + id + ", estadoId=" + estadoId + ", estado=" + estado + ", usuario="
				+ usuario + ", email=" + email + ", aplicacionId=" + aplicacionId + ", clave=" + clave + "]";
	}

}
