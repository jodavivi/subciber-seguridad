package com.subciber.seguridad.business.dto;

import java.io.Serializable;
import java.util.List;

import com.subciber.seguridad.entity.VGrupoComponenteFaltate;

public class ResponseGrupoComponenteFaltanteDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<VGrupoComponenteFaltate> componentes;
	public List<VGrupoComponenteFaltate> getComponentes() {
		return componentes;
	}
	public void setComponentes(List<VGrupoComponenteFaltate> componentes) {
		this.componentes = componentes;
	}
	@Override
	public String toString() {
		return "ResponseGrupoComponenteFaltanteDto [componentes=" + componentes + "]";
	}
	
}
