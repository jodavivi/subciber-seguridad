/**
 * 
 */
package com.subciber.seguridad.base.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class RequestGenericDto<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AuditRequestDto auditRequest;
	private T objectRequest;
	
	public RequestGenericDto() {
		super();
		auditRequest = new AuditRequestDto();
	}

	public AuditRequestDto getAuditRequest() {
		return auditRequest;
	}

	public void setAuditRequest(AuditRequestDto auditRequest) {
		this.auditRequest = auditRequest;
	}

	public T getObjectRequest() {
		return objectRequest;
	}

	public void setObjectRequest(T objectRequest) {
		this.objectRequest = objectRequest;
	}

}
