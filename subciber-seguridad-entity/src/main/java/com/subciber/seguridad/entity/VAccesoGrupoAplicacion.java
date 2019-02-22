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
	@Column(name="\"IdCorrelativo\"")
	private Integer idCorrelativo;
	
	@Column(name="\"UsuarioId\"")
	private Integer usuarioId;
	
	@Column(name="\"RolId\"")
	private Integer rolId;
	
	@Column(name="\"Rol\"")
	private String rol;
	
	@Column(name="\"GrupoId\"")
	private Integer grupoId;

	@Column(name="\"Grupo\"")
	private String grupo;
	
	@Column(name="\"ComponenteId\"")
	private Integer componenteId;

	@Column(name="\"AplicacionId\"")
	private Integer aplicacionId;
	
	@Column(name="\"TipoComponenteId\"")
	private String tipoComponenteId;

	@Column(name="\"Codigo\"")
	private String codigo;

	@Column(name="\"CodigoEncriptado\"")
	private String codigoEncriptado;
	
	@Column(name="\"Descripcion\"")
	private String descripcion;

	@Column(name="\"Nivel\"")
	private String nivel;

	@Column(name="\"PadreId\"")
	private Integer padreId;

	@Column(name="\"Icono\"")
	private String icono;
	
	@Column(name="\"Url\"")
	private String url;
	
	public VAccesoGrupoAplicacion() {
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

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Integer getGrupoId() {
		return grupoId;
	}

	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "VAccesoGrupoAplicacion [idCorrelativo=" + idCorrelativo + ", usuarioId=" + usuarioId + ", rolId="
				+ rolId + ", rol=" + rol + ", grupoId=" + grupoId + ", grupo=" + grupo + ", componenteId="
				+ componenteId + ", aplicacionId=" + aplicacionId + ", tipoComponenteId=" + tipoComponenteId
				+ ", codigo=" + codigo + ", codigoEncriptado=" + codigoEncriptado + ", descripcion=" + descripcion
				+ ", nivel=" + nivel + ", padreId=" + padreId + ", icono=" + icono + ", url=" + url + "]";
	}
}
