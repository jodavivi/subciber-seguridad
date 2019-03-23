package com.subciber.seguridad.dao.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.subciber.seguridad.entity.VUsuario;

public class ResponseUsuarioDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<VUsuario> usuarios;
	
	public ResponseUsuarioDto() {
		usuarios = new ArrayList<VUsuario>();
	}
	
	public List<VUsuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<VUsuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
