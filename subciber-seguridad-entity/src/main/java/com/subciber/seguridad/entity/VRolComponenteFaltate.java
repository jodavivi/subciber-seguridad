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
 * @Creacion 04/08/2019
 * @Update
 * 
 */
@Entity
@Table(schema="\"Seguridad\"", name="\"VRolComponenteFaltante\"")
public class VRolComponenteFaltate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"IdCorrelativo\"")
	private Integer idCorrelativo;

	@Column(name="\"RolId\"")
	private Integer rolId;
	
	@Column(name="\"Rol\"")
	private String rol;
	
	@Column(name="\"EstadoId\"")
	private Integer estadoId;
	
	@Column(name="\"Estado\"")
	private String estado;
	
	@Column(name="\"ComponenteId\"")
	private Integer componenteId;

	@Column(name="\"Codigo\"")
	private String codigo;

	@Column(name="\"Nombre\"")
	private String nombre;

	@Column(name="\"Descripcion\"")
	private String descripcion;
	
	@Column(name="\"EstadoAplicacion\"")
	private String estadoAplicacion;
	
	@Column(name="\"CodigoId\"")
	private Integer codigoId;
	
	public VRolComponenteFaltate() {
	
	}

	public Integer getIdCorrelativo() {
		return idCorrelativo;
	}

	public void setIdCorrelativo(Integer idCorrelativo) {
		this.idCorrelativo = idCorrelativo;
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

	public Integer getComponenteId() {
		return componenteId;
	}

	public void setComponenteId(Integer componenteId) {
		this.componenteId = componenteId;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstadoAplicacion() {
		return estadoAplicacion;
	}

	public void setEstadoAplicacion(String estadoAplicacion) {
		this.estadoAplicacion = estadoAplicacion;
	}

	public Integer getCodigoId() {
		return codigoId;
	}

	public void setCodigoId(Integer codigoId) {
		this.codigoId = codigoId;
	}

	@Override
	public String toString() {
		return "VRolComponenteFaltate [idCorrelativo=" + idCorrelativo + ", rolId=" + rolId + ", rol=" + rol
				+ ", estadoId=" + estadoId + ", estado=" + estado + ", componenteId=" + componenteId + ", codigo="
				+ codigo + ", nombre=" + nombre + ", descripcion=" + descripcion + ", estadoAplicacion="
				+ estadoAplicacion + ", codigoId=" + codigoId + "]";
	}

	 
}