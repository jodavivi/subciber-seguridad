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
 * @author jose david villanueva villalobos
 * @Creacion 12 feb. 2019
 * @Update
 * 
 */
@Entity
@Table(schema="\"Seguridad\"", name="\"VAccesoComponente\"")
public class VAccesoComponente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"Id\"")
	private Integer id;
	
	@Column(name="\"CodigoComponente\"")
	private String codigoComponente;

	@Column(name="\"CodigoEncriptado\"")
	private String codigoEncriptado;

	@Column(name="\"Descripcion\"")
	private String descripcion;

	@Column(name="\"Nivel\"")
	private String nivel;

	@Column(name="\"Url\"")
	private String url;

	@Column(name="\"UsuarioId\"")
	private Integer usuarioId;

	@Column(name="\"Icono\"")
	private String icono;
	
	public VAccesoComponente() {
	}

	public String getCodigoComponente() {
		return this.codigoComponente;
	}

	public void setCodigoComponente(String codigoComponente) {
		this.codigoComponente = codigoComponente;
	}

	public String getCodigoEncriptado() {
		return this.codigoEncriptado;
	}

	public void setCodigoEncriptado(String codigoEncriptado) {
		this.codigoEncriptado = codigoEncriptado;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNivel() {
		return this.nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}
	
}