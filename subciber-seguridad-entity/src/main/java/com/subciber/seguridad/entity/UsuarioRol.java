/**
 * 
 */
package com.subciber.seguridad.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author josep
 *
 */
@Entity
@Table(schema="\"Seguridad\"", name="\"UsuarioRol\"")
public class UsuarioRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_usuario_rol", schema="\"Seguridad\"", sequenceName = "\"UsuarioRol_Id_seq\"", allocationSize = 1)
	@GeneratedValue(generator = "seq_usuario_rol")
	@Column(name="\"Id\"")
	private Integer id;
	
	@Column(name="\"EstadoId\"")
	private Integer estadoId;

	@Column(name="\"UsuarioCreador\"")
	private String usuarioCreador;

	@Column(name="\"FechaCreacion\"")
	private LocalDateTime fechaCreacion;

	@Column(name="\"TerminalCreacion\"")
	private String terminalCreacion;

	@Column(name="\"UsuarioModificador\"")
	private String usuarioModificador;
	
	@Column(name="\"FechaModificacion\"")
	private LocalDateTime fechaModificacion;

	@Column(name="\"TerminalModificador\"")
	private String terminalModificador;

	@Column(name="\"TransaccionId\"")
	private String transaccionId = "";

	@Column(name="\"UsuarioId\"")
	private Integer usuarioId;

	@Column(name="\"RolId\"")
	private Integer rolId;
	
	public UsuarioRol() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getUsuarioCreador() {
		return usuarioCreador;
	}

	public void setUsuarioCreador(String usuarioCreador) {
		this.usuarioCreador = usuarioCreador;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTerminalCreacion() {
		return terminalCreacion;
	}

	public void setTerminalCreacion(String terminalCreacion) {
		this.terminalCreacion = terminalCreacion;
	}

	public LocalDateTime getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(LocalDateTime fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getTerminalModificador() {
		return terminalModificador;
	}

	public void setTerminalModificador(String terminalModificador) {
		this.terminalModificador = terminalModificador;
	}

	public String getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(String transaccionId) {
		this.transaccionId = transaccionId;
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getUsuarioModificador() {
		return usuarioModificador;
	}

	public void setUsuarioModificador(String usuarioModificador) {
		this.usuarioModificador = usuarioModificador;
	}

	public Integer getRolId() {
		return rolId;
	}

	public void setRolId(Integer rolId) {
		this.rolId = rolId;
	}

	@Override
	public String toString() {
		return "UsuarioRol [id=" + id + ", estadoId=" + estadoId + ", usuarioCreador=" + usuarioCreador
				+ ", fechaCreacion=" + fechaCreacion + ", terminalCreacion=" + terminalCreacion
				+ ", usuarioModificador=" + usuarioModificador + ", fechaModificacion=" + fechaModificacion
				+ ", terminalModificador=" + terminalModificador + ", transaccionId=" + transaccionId + ", usuarioId="
				+ usuarioId + ", rolId=" + rolId + "]";
	}

}
