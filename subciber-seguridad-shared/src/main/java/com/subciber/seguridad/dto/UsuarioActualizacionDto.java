/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author josep
 *
 */
public class UsuarioActualizacionDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private UsuarioDetalleDto datosUsuario;
	private List<Integer> rol;
	public UsuarioDetalleDto getDatosUsuario() {
		return datosUsuario;
	}
	public void setDatosUsuario(UsuarioDetalleDto datosUsuario) {
		this.datosUsuario = datosUsuario;
	}
	public List<Integer> getRol() {
		return rol;
	}
	public void setRol(List<Integer> rol) {
		this.rol = rol;
	}

	
}
