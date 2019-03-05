/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;

/**
 * @author jose david villanueva villalobos
 * @Creacion 12 feb. 2019
 * @Update
 * 
 */
public class UsuarioAplicacionDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer aplicacionId;
	private String nombre;
	private String descripcion;
	private String url;
	private String icono;
	 
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIcono() {
		return icono;
	}
	public void setIcono(String icono) {
		this.icono = icono;
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
