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
@Table(schema="\"Seguridad\"", name="\"UsuarioDetalle\"")
public class UsuarioDetalle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_usuario_detalle", schema="\"Seguridad\"", sequenceName = "\"UsuarioDetalle_Id_seq\"", allocationSize = 1)
	@GeneratedValue(generator = "seq_usuario_detalle")
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

	@Column(name="\"Nombre\"")
	private String nombre;
	
	@Column(name="\"Apellido\"")
	private String apellido;
	
	@Column(name="\"Imagen\"")
	private String imagen;


	public UsuarioDetalle() {
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getUsuarioModificador() {
		return usuarioModificador;
	}

	public void setUsuarioModificador(String usuarioModificador) {
		this.usuarioModificador = usuarioModificador;
	}

	@Override
	public String toString() {
		return "UsuarioDetalle [id=" + id + ", estadoId=" + estadoId + ", usuarioCreador=" + usuarioCreador
				+ ", fechaCreacion=" + fechaCreacion + ", terminalCreacion=" + terminalCreacion
				+ ", usuarioModificador=" + usuarioModificador + ", fechaModificacion=" + fechaModificacion
				+ ", terminalModificador=" + terminalModificador + ", transaccionId=" + transaccionId + ", usuarioId="
				+ usuarioId + ", nombre=" + nombre + ", apellido=" + apellido + ", imagen=" + imagen + "]";
	}

}
