package com.subciber.seguridad.dao.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.subciber.seguridad.entity.VRolComponente;

public class ResponseRolComponenteDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<VRolComponente> rolComponente;
	
	public ResponseRolComponenteDto() {
		rolComponente = new ArrayList<VRolComponente>();
	}

	public List<VRolComponente> getRolComponente() {
		return rolComponente;
	}

	public void setRolComponente(List<VRolComponente> rolComponente) {
		this.rolComponente = rolComponente;
	}

}
