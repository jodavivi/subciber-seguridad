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
	@Column(name="\"IdCorrelativo\"")
	private Integer idCorrelativo;

	@Column(name="\"UsuarioId\"")
	private Integer usuarioId;
	
	@Column(name="\"CodigoUsuario\"")
	private String codigoUsuario;
	
	@Column(name="\"ComponenteId\"")
	private Integer componenteId;

	@Column(name="\"AplicacionId\"")
	private Integer aplicacionId;

	@Column(name="\"TipoComponenteId\"")
	private String tipoComponenteId;

	@Column(name="\"CodigoComponente\"")
	private String codigoComponente;

	@Column(name="\"CodigoEncriptado\"")
	private String codigoEncriptado;

	@Column(name="\"Descripcion\"")
	private String descripcion;

	@Column(name="\"Nivel\"")
	private String nivel;
	
	@Column(name="\"PadreId\"")
	private Integer padreId;
	
	@Column(name="\"Url\"")
	private String url;
	
	public VAccesoComponente() {
	}

	public Integer getIdCorrelativo() {
		return idCorrelativo;
	}

	public void setIdCorrelativo(Integer idCorrelativo) {
		this.idCorrelativo = idCorrelativo;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public Integer getComponenteId() {
		return componenteId;
	}

	public void setComponenteId(Integer componenteId) {
		this.componenteId = componenteId;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
	}

	public String getTipoComponenteId() {
		return tipoComponenteId;
	}

	public void setTipoComponenteId(String tipoComponenteId) {
		this.tipoComponenteId = tipoComponenteId;
	}

	public String getCodigoComponente() {
		return codigoComponente;
	}

	public void setCodigoComponente(String codigoComponente) {
		this.codigoComponente = codigoComponente;
	}

	public String getCodigoEncriptado() {
		return codigoEncriptado;
	}

	public void setCodigoEncriptado(String codigoEncriptado) {
		this.codigoEncriptado = codigoEncriptado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Integer getPadreId() {
		return padreId;
	}

	public void setPadreId(Integer padreId) {
		this.padreId = padreId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}