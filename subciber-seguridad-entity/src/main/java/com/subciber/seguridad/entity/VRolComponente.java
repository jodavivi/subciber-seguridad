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
@Table(schema="\"Seguridad\"", name="\"VRolComponente\"")
public class VRolComponente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"Id\"")
	private Integer id;

	@Column(name="\"EstadoId\"")
	private Integer estadoId;
	
	@Column(name="\"Estado\"")
	private String estado;
	
	@Column(name="\"RolId\"")
	private Integer rolId;

	@Column(name="\"Rol\"")
	private String rol;

	@Column(name="\"ComponenteId\"")
	private Integer componenteId;

	@Column(name="\"TipoComponenteId\"")
	private String tipoComponenteId;

	@Column(name="\"Codigo\"")
	private String codigo;

	@Column(name="\"CodigoEncriptado\"")
	private String codigoEncriptado;

	@Column(name="\"Descripcion\"")
	private String descripcion;
	
	@Column(name="\"Url\"")
	private String url;
	
	@Column(name="\"PadreId\"")
	private Integer padreId;
	
	@Column(name="\"Nivel\"")
	private String nivel;

	@Column(name="\"Icono\"")
	private String icono;
	
	@Column(name="\"Nombre\"")
	private String nombre;
	
	public VRolComponente() {
	
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

	public Integer getComponenteId() {
		return componenteId;
	}

	public void setComponenteId(Integer componenteId) {
		this.componenteId = componenteId;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getPadreId() {
		return padreId;
	}

	public void setPadreId(Integer padreId) {
		this.padreId = padreId;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}