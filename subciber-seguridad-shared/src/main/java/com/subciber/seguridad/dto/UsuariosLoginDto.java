package com.subciber.seguridad.dto;

import java.io.Serializable;
import java.util.List;

public class UsuariosLoginDto implements Serializable{
  
	private static final long serialVersionUID = 1L;
	
	private List<InfoJwt> usuariosLogueados;
	
	private int totalLogueados;

	public List<InfoJwt> getUsuariosLogueados() {
		return usuariosLogueados;
	}

	public void setUsuariosLogueados(List<InfoJwt> usuariosLogueados) {
		this.usuariosLogueados = usuariosLogueados;
	}

	public int getTotalLogueados() {
		return totalLogueados;
	}

	public void setTotalLogueados(int totalLogueados) {
		this.totalLogueados = totalLogueados;
	}
	
}
