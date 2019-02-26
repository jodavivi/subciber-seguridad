/**
 * 
 */
package com.subciber.seguridad.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class UsuarioDetalleFiltroDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer usuarioId;
	private Integer estadoId;
	
	public Integer getEstadoId() {
		return estadoId;
	}
	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

}
