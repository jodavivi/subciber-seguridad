package com.subciber.seguridad.dto;

import java.io.Serializable;

public class ClaveUsuarioDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer usuarioId;
	private String nuevaClave;
	private String repetirNuevaClave;
	
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getNuevaClave() {
		return nuevaClave;
	}
	public void setNuevaClave(String nuevaClave) {
		this.nuevaClave = nuevaClave;
	}
	public String getRepetirNuevaClave() {
		return repetirNuevaClave;
	}
	public void setRepetirNuevaClave(String repetirNuevaClave) {
		this.repetirNuevaClave = repetirNuevaClave;
	}
	@Override
	public String toString() {
		return "ClaveUsuarioDtio [nuevaClave=" + nuevaClave + ", repetirNuevaClave=" + repetirNuevaClave + "]";
	}
	

}
