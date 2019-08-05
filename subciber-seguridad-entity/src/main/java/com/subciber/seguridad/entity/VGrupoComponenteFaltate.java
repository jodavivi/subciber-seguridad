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
@Table(schema="\"Seguridad\"", name="\"VGrupoComponenteFaltate\"")
public class VGrupoComponenteFaltate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"IdCorrelativo\"")
	private Integer idCorrelativo;

	@Column(name="\"GrupoId\"")
	private Integer grupoId;
	
	@Column(name="\"Grupo\"")
	private String grupo;
	
	@Column(name="\"GrupoDescripcion\"")
	private String grupoDescripcion;
	
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
	
	public VGrupoComponenteFaltate() {
	
	}

	public Integer getIdCorrelativo() {
		return idCorrelativo;
	}

	public void setIdCorrelativo(Integer idCorrelativo) {
		this.idCorrelativo = idCorrelativo;
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

	public String getGrupoDescripcion() {
		return grupoDescripcion;
	}

	public void setGrupoDescripcion(String grupoDescripcion) {
		this.grupoDescripcion = grupoDescripcion;
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

	@Override
	public String toString() {
		return "VGrupoComponenteFaltate [idCorrelativo=" + idCorrelativo + ", grupoId=" + grupoId + ", grupo=" + grupo
				+ ", grupoDescripcion=" + grupoDescripcion + ", estadoId=" + estadoId + ", estado=" + estado
				+ ", componenteId=" + componenteId + ", codigo=" + codigo + ", nombre=" + nombre + ", descripcion="
				+ descripcion + "]";
	}

}