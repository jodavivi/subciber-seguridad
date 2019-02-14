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
@Table(schema="\"Seguridad\"", name="\"VAccesoGrupoAplicacion\"")
public class VAccesoGrupoAplicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"Id\"")
	private Integer id;
	
	@Column(name="\"Aplicacion\"")
	private String aplicacion;

	@Column(name="\"CodigoAplicacion\"")
	private String codigoAplicacion;

	@Column(name="\"ComponenteId\"")
	private Integer componenteId;

	@Column(name="\"DescripcionAplicacion\"")
	private String descripcionAplicacion;

	@Column(name="\"Grupo\"")
	private String grupo;

	@Column(name="\"GrupoDescripcion\"")
	private String grupoDescripcion;

	@Column(name="\"GrupoId\"")
	private Integer grupoId;

	@Column(name="\"NivelAplicacion\"")
	private String nivelAplicacion;

	@Column(name="\"UrlAplicacion\"")
	private String urlAplicacion;

	@Column(name="\"UsuarioId\"")
	private Integer usuarioId;

	@Column(name="\"IconoAplicacion\"")
	private String iconoAplicacion;

	public VAccesoGrupoAplicacion() {
	}

	public String getAplicacion() {
		return this.aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getCodigoAplicacion() {
		return this.codigoAplicacion;
	}

	public void setCodigoAplicacion(String codigoAplicacion) {
		this.codigoAplicacion = codigoAplicacion;
	}

	public Integer getComponenteId() {
		return this.componenteId;
	}

	public void setComponenteId(Integer componenteId) {
		this.componenteId = componenteId;
	}

	public String getDescripcionAplicacion() {
		return this.descripcionAplicacion;
	}

	public void setDescripcionAplicacion(String descripcionAplicacion) {
		this.descripcionAplicacion = descripcionAplicacion;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getGrupoDescripcion() {
		return this.grupoDescripcion;
	}

	public void setGrupoDescripcion(String grupoDescripcion) {
		this.grupoDescripcion = grupoDescripcion;
	}

	public Integer getGrupoId() {
		return this.grupoId;
	}

	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}

	public String getNivelAplicacion() {
		return this.nivelAplicacion;
	}

	public void setNivelAplicacion(String nivelAplicacion) {
		this.nivelAplicacion = nivelAplicacion;
	}

	public String getUrlAplicacion() {
		return this.urlAplicacion;
	}

	public void setUrlAplicacion(String urlAplicacion) {
		this.urlAplicacion = urlAplicacion;
	}

	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIconoAplicacion() {
		return iconoAplicacion;
	}

	public void setIconoAplicacion(String iconoAplicacion) {
		this.iconoAplicacion = iconoAplicacion;
	}
	
}
