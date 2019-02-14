/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jose david villanueva villalobos
 * @Creacion 12 feb. 2019
 * @Update
 * 
 */
public class UsuarioGrupoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String codigoGrupo;
	private String nombreGrupo;
	private List<UsuarioAplicacionDto> aplicaciones;
	
	public UsuarioGrupoDto() {
		super();
		aplicaciones = new ArrayList<>();
	}
	
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	public String getNombreGrupo() {
		return nombreGrupo;
	}
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
	public List<UsuarioAplicacionDto> getAplicaciones() {
		return aplicaciones;
	}
	public void setAplicaciones(List<UsuarioAplicacionDto> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

}
