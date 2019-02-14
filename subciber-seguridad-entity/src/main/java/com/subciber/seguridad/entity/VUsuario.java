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
	
	@Column(name="\"Apellido\"")
	private String apellido;

	@Column(name="\"AplicacionId\"")
	private Integer aplicacionId;

	@Column(name="\"Clave\"")
	private String clave;

	@Column(name="\"CodigoAplicacion\"")
	private String codigoAplicacion;

	@Column(name="\"CodigoUsuario\"")
	private String codigoUsuario;

	@Column(name="\"Email\"")
	private String email;

	@Column(name="\"EmailNotificacion\"")
	private String emailNotificacion;

	@Column(name="\"EstadoId\"")
	private Integer estadoId;

	@Column(name="\"EstadoUsuario\"")
	private String estadoUsuario;
	
	@Column(name="\"Imagen\"")
	private String imagen;

	@Column(name="\"Logo\"")
	private String logo;

	@Column(name="\"Nombre\"")
	private String nombre;

	@Column(name="\"NumeroIdentificacion\"")
	private String numeroIdentificacion;

	@Column(name="\"Path\"")
	private String path;

	@Column(name="\"RazonSocial\"")
	private String razonSocial;

	@Column(name="\"Url\"")
	private String url;

	@Column(name="\"Usuario\"")
	private String usuario;

	public VUsuario() {
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getAplicacionId() {
		return this.aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCodigoAplicacion() {
		return this.codigoAplicacion;
	}

	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}

	public String getCodigoUsuario() {
		return this.codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailNotificacion() {
		return this.emailNotificacion;
	}

	public void setEmailNotificacion(String emailNotificacion) {
		this.emailNotificacion = emailNotificacion;
	}

	public Integer getEstadoId() {
		return this.estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstadoUsuario() {
		return this.estadoUsuario;
	}

	public void setEstadoUsuario(String estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumeroIdentificacion() {
		return this.numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
