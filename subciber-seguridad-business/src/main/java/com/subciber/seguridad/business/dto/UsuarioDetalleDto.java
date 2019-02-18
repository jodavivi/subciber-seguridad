/**
 * 
 */
package com.subciber.seguridad.business.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class UsuarioDetalleDto extends UsuarioDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer usuarioId;
	
	private String clave;
	
	public Integer getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
}
