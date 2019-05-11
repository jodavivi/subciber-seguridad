package com.subciber.seguridad.dto;

import java.io.Serializable;

public class RecuperarCuentaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String usuario ;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	

}
