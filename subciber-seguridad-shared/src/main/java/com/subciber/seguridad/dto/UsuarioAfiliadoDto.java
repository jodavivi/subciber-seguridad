package com.subciber.seguridad.dto;

import java.io.Serializable;

public class UsuarioAfiliadoDto implements Serializable{ 
	
private static final long serialVersionUID = 1L;
	
	private String telefono;
	private Integer departamentoId;
	private Integer provinciaId;
	private Integer distritoId;
	private String identificacionBeneficiario;
	private String nonbreBeneficiario;
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public Integer getDepartamentoId() {
		return departamentoId;
	}
	public void setDepartamentoId(Integer departamentoId) {
		this.departamentoId = departamentoId;
	}
	public Integer getProvinciaId() {
		return provinciaId;
	}
	public void setProvinciaId(Integer provinciaId) {
		this.provinciaId = provinciaId;
	}
	public Integer getDistritoId() {
		return distritoId;
	}
	public void setDistritoId(Integer distritoId) {
		this.distritoId = distritoId;
	}
	public String getIdentificacionBeneficiario() {
		return identificacionBeneficiario;
	}
	public void setIdentificacionBeneficiario(String identificacionBeneficiario) {
		this.identificacionBeneficiario = identificacionBeneficiario;
	}
	public String getNonbreBeneficiario() {
		return nonbreBeneficiario;
	}
	public void setNonbreBeneficiario(String nonbreBeneficiario) {
		this.nonbreBeneficiario = nonbreBeneficiario;
	}
	@Override
	public String toString() {
		return "UsuarioAfiliadoDto [telefono=" + telefono + ", departamentoId=" + departamentoId + ", provinciaId="
				+ provinciaId + ", distritoId=" + distritoId + ", identificacionBeneficiario="
				+ identificacionBeneficiario + ", nonbreBeneficiario=" + nonbreBeneficiario + "]";
	}
	
}
