/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class RolComponenteDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer rolId;
	private Integer componenteId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRolId() {
		return rolId;
	}
	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}
	public Integer getComponenteId() {
		return componenteId;
	}
	public void setComponenteId(Integer componenteId) {
		this.componenteId = componenteId;
	}
	
}
