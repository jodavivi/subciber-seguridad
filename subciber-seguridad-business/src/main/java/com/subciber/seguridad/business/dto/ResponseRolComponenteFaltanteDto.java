package com.subciber.seguridad.business.dto;

import java.io.Serializable;
import java.util.List;

import com.subciber.seguridad.entity.VRolComponenteFaltate;

public class ResponseRolComponenteFaltanteDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<VRolComponenteFaltate> componentes;
	public List<VRolComponenteFaltate> getComponentes() {
		return componentes;
	}
	public void setComponentes(List<VRolComponenteFaltate> componentes) {
		this.componentes = componentes;
	}
	@Override
	public String toString() {
		return "ResponseRolComponenteFaltanteDto [componentes=" + componentes + "]";
	}
	
}
