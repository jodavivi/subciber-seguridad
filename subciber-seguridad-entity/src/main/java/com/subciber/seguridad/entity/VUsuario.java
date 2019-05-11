/**
 * 
 */
package com.subciber.seguridad.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author josep
 *
 */
@Entity
@Table(schema="\"Seguridad\"", name="\"VUsuario\"")
public class VUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"Id\"")
	private Integer usuarioId;

	@Column(name="\"EstadoId\"")
	private Integer estadoId;

	@Column(name="\"Estado\"")
	private String estado;

	@Column(name="\"Codigo\"")
	private String codigo;

	@Column(name="\"Usuario\"")
	private String usuario;

	@Column(name="\"Email\"")
	private String email;
	
	@Column(name="\"Imagen\"")
	private String imagen;

	@Column(name="\"AplicacionId\"")
	private Integer aplicacionId;

	@Column(name="\"UsuarioDetalleId\"")
	private Integer usuarioDetalleId;
	
	@Column(name="\"Nombre\"")
	private String nombre;

	@Column(name="\"Apellido\"")
	private String apellido;

	@Column(name="\"CambiarClave\"")
	private Integer cambiarClave;
	
	@Column(name="\"AfiliadoId\"")
	private Integer afiliadoId;
	
	@Column(name="\"CodigoAfiliado\"")
	private String codigoAfiliado;
	
	@Column(name="\"NombreAfiliado\"")
	private String nombreAfiliado;
	
	@Column(name="\"ApellidoAfiliado\"")
	private String apellidoAfiliado;
	
	@Column(name="\"NumeroIdentificacion\"")
	private String numeroIdentificacion;
	
	@Column(name="\"TelefonoAfiliado\"")
	private String telefonoAfiliado;
	
	@Column(name="\"PaisId\"")
	private Integer paisId;
	
	@Column(name="\"Pais\"")
	private String pais;
	
	@Column(name="\"DepartamentoId\"")
	private Integer departamentoId;
	
	@Column(name="\"Departamento\"")
	private String Departamento;
	
	@Column(name="\"ProvinciaId\"")
	private Integer provinciaId;
	
	@Column(name="\"Provincia\"")
	private String Provincia;
	
	@Column(name="\"DistritoId\"")
	private Integer distritoId;
	
	@Column(name="\"Distrito\"")
	private String distrito;
	
	@Column(name="\"Direccion\"")
	private String direccion;
	
	@Column(name="\"IdentificacionBeneficiario\"")
	private String identificacionBeneficiario;
	
	@Column(name="\"NombreBeneficiario\"")
	private String nombreBeneficiario;
	
	public VUsuario() {
	}

	public Integer getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Integer usuarioId) {
		this.usuarioId = usuarioId;
	}


	public Integer getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(Integer estadoId) {
		this.estadoId = estadoId;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Integer getAplicacionId() {
		return aplicacionId;
	}

	public void setAplicacionId(Integer aplicacionId) {
		this.aplicacionId = aplicacionId;
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

	public Integer getUsuarioDetalleId() {
		return usuarioDetalleId;
	}

	public void setUsuarioDetalleId(Integer usuarioDetalleId) {
		this.usuarioDetalleId = usuarioDetalleId;
	}

	public Integer getCambiarClave() {
		return cambiarClave;
	}

	public void setCambiarClave(Integer cambiarClave) {
		this.cambiarClave = cambiarClave;
	}

	public Integer getAfiliadoId() {
		return afiliadoId;
	}

	public void setAfiliadoId(Integer afiliadoId) {
		this.afiliadoId = afiliadoId;
	}

	public String getCodigoAfiliado() {
		return codigoAfiliado;
	}

	public void setCodigoAfiliado(String codigoAfiliado) {
		this.codigoAfiliado = codigoAfiliado;
	}

	public String getNombreAfiliado() {
		return nombreAfiliado;
	}

	public void setNombreAfiliado(String nombreAfiliado) {
		this.nombreAfiliado = nombreAfiliado;
	}

	public String getApellidoAfiliado() {
		return apellidoAfiliado;
	}

	public void setApellidoAfiliado(String apellidoAfiliado) {
		this.apellidoAfiliado = apellidoAfiliado;
	}

	public String getNumeroIdentificacion() {
		return numeroIdentificacion;
	}

	public void setNumeroIdentificacion(String numeroIdentificacion) {
		this.numeroIdentificacion = numeroIdentificacion;
	}

	public String getTelefonoAfiliado() {
		return telefonoAfiliado;
	}

	public void setTelefonoAfiliado(String telefonoAfiliado) {
		this.telefonoAfiliado = telefonoAfiliado;
	}

	public Integer getPaisId() {
		return paisId;
	}

	public void setPaisId(Integer paisId) {
		this.paisId = paisId;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Integer getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(Integer departamentoId) {
		this.departamentoId = departamentoId;
	}

	public String getDepartamento() {
		return Departamento;
	}

	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}

	public Integer getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Integer provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getProvincia() {
		return Provincia;
	}

	public void setProvincia(String provincia) {
		Provincia = provincia;
	}

	public Integer getDistritoId() {
		return distritoId;
	}

	public void setDistritoId(Integer distritoId) {
		this.distritoId = distritoId;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getIdentificacionBeneficiario() {
		return identificacionBeneficiario;
	}

	public void setIdentificacionBeneficiario(String identificacionBeneficiario) {
		this.identificacionBeneficiario = identificacionBeneficiario;
	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public void setNombreBeneficiario(String nombreBeneficiario) {
		this.nombreBeneficiario = nombreBeneficiario;
	}

	@Override
	public String toString() {
		return "VUsuario [usuarioId=" + usuarioId + ", estadoId=" + estadoId + ", estado=" + estado + ", codigo="
				+ codigo + ", usuario=" + usuario + ", email=" + email + ", imagen=" + imagen + ", aplicacionId="
				+ aplicacionId + ", usuarioDetalleId=" + usuarioDetalleId + ", nombre=" + nombre + ", apellido="
				+ apellido + ", cambiarClave=" + cambiarClave + ", afiliadoId=" + afiliadoId + ", codigoAfiliado="
				+ codigoAfiliado + ", nombreAfiliado=" + nombreAfiliado + ", apellidoAfiliado=" + apellidoAfiliado
				+ ", numeroIdentificacion=" + numeroIdentificacion + ", telefonoAfiliado=" + telefonoAfiliado
				+ ", paisId=" + paisId + ", pais=" + pais + ", departamentoId=" + departamentoId + ", Departamento="
				+ Departamento + ", provinciaId=" + provinciaId + ", Provincia=" + Provincia + ", distritoId="
				+ distritoId + ", distrito=" + distrito + ", direccion=" + direccion + ", identificacionBeneficiario="
				+ identificacionBeneficiario + ", nombreBeneficiario=" + nombreBeneficiario + "]";
	}

}
