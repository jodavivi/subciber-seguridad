/**
 * 
 */
package com.subciber.seguridad.base.dto;

import java.io.Serializable;

/**
 * @author josep
 *
 */
public class ResponseGenericDto<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AuditResponseDto auditResponse;
	private T objectResponse;
	
	public ResponseGenericDto() {
		super();
		auditResponse = new AuditResponseDto();
	}

	public AuditResponseDto getAuditResponse() {
		return auditResponse;
	}

	public void setAuditResponse(AuditResponseDto auditResponse) {
		this.auditResponse = auditResponse;
		
	}

	public T getObjectResponse() {
		return objectResponse;
	}

	public void setObjectResponse(T objectResponse) {
		this.objectResponse = objectResponse;
	}
	
}
