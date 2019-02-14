/**
 * 
 */
package com.subciber.seguridad.business.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.subciber.seguridad.dto.UsuarioGrupoDto;

/**
 * @author josep
 *
 */
public class InfoUsuarioDto implements Serializable{

	private static final long 		serialVersionUID = 1L;
	private UsuarioDto 				usuario;
	private List<UsuarioGrupoDto> 	grupoAplicaciones;
	private List<AccesosDto> 		accesosRecursos;
	
	public InfoUsuarioDto() {
		super();
		usuario 			= new UsuarioDto();
		grupoAplicaciones 	= new ArrayList<>();
		accesosRecursos 	= new ArrayList<>();
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public List<AccesosDto> getAccesosRecursos() {
		return accesosRecursos;
	}

	public void setAccesosRecursos(List<AccesosDto> accesosRecursos) {
		this.accesosRecursos = accesosRecursos;
	}

	public List<UsuarioGrupoDto> getGrupoAplicaciones() {
		return grupoAplicaciones;
	}

	public void setGrupoAplicaciones(List<UsuarioGrupoDto> grupoAplicaciones) {
		this.grupoAplicaciones = grupoAplicaciones;
	}

}
