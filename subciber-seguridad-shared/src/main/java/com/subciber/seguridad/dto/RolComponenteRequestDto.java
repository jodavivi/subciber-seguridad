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
public class RolComponenteRequestDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer rolId;
	private List<Integer> componenteId;
	public Integer getRolId() {
		return rolId;
	}
	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}
	public List<Integer> getComponenteId() {
		return componenteId;
	}
	public void setComponenteId(List<Integer> componenteId) {
		this.componenteId = componenteId;
	}
	@Override
	public String toString() {
		return "RolComponenteRequestDto [rolId=" + rolId + ", componenteId=" + componenteId + "]";
	}
	
}
