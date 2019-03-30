/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class GrupoComponenteDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer grupoId;
	private Integer componenteId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGrupoId() {
		return grupoId;
	}
	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}
	public Integer getComponenteId() {
		return componenteId;
	}
	public void setComponenteId(Integer componenteId) {
		this.componenteId = componenteId;
	}
	
}
