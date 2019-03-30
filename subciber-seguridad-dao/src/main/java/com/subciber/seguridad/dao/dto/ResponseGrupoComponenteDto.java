package com.subciber.seguridad.dao.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.subciber.seguridad.entity.VGrupoComponente;

public class ResponseGrupoComponenteDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<VGrupoComponente> grupoComponente;
	
	public ResponseGrupoComponenteDto() {
		grupoComponente = new ArrayList<VGrupoComponente>();
	}

	public List<VGrupoComponente> getGrupoComponente() {
		return grupoComponente;
	}

	public void setGrupoComponente(List<VGrupoComponente> grupoComponente) {
		this.grupoComponente = grupoComponente;
	}

}
