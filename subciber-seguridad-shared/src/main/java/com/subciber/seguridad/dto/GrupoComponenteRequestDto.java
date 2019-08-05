/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author josep
 *
 */
public class GrupoComponenteRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer grupoId;
	private List<Integer> componenteId;
	public Integer getGrupoId() {
		return grupoId;
	}
	public void setGrupoId(Integer grupoId) {
		this.grupoId = grupoId;
	}
	public List<Integer> getComponenteId() {
		return componenteId;
	}
	public void setComponenteId(List<Integer> componenteId) {
		this.componenteId = componenteId;
	}
	@Override
	public String toString() {
		return "GrupoComponenteDto [grupoId=" + grupoId + ", componenteId=" + componenteId + "]";
	}
	
}
