package com.subciber.seguridad.dto;

import java.io.Serializable;

public class AccesosDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nombreControl;
	private String codigoControl;
	private String urlControl;
	
	public String getNombreControl() {
		return nombreControl;
	}
	public void setNombreControl(String nombreControl) {
		this.nombreControl = nombreControl;
	}
	public String getCodigoControl() {
		return codigoControl;
	}
	public void setCodigoControl(String codigoControl) {
		this.codigoControl = codigoControl;
	}
	public String getUrlControl() {
		return urlControl;
	}
	public void setUrlControl(String urlControl) {
		this.urlControl = urlControl;
	}
	@Override
	public String toString() {
		return "AccesosDto [nombreControl=" + nombreControl + ", codigoControl=" + codigoControl + ", urlControl="
				+ urlControl + "]";
	}
}
