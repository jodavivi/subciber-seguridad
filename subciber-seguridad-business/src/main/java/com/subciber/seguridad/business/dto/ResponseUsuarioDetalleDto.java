package com.subciber.seguridad.business.dto;

import java.io.Serializable;
import java.util.List;

import com.subciber.seguridad.entity.VUsuario;
import com.subciber.seguridad.entity.VUsuarioRol;

public class ResponseUsuarioDetalleDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private VUsuario usuario;
	private List<VUsuarioRol> rol;
	public VUsuario getUsuario() {
		return usuario;
	}
	public void setUsuario(VUsuario usuario) {
		this.usuario = usuario;
	}
	public List<VUsuarioRol> getRol() {
		return rol;
	}
	public void setRol(List<VUsuarioRol> rol) {
		this.rol = rol;
	}
	
}
