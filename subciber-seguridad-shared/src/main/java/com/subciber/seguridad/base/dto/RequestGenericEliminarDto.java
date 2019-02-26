/**
 * 
 */
package com.subciber.seguridad.base.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author josep
 *
 */
public class RequestGenericEliminarDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private AuditRequestDto auditRequest;
	private List<Integer> id;
	
	public RequestGenericEliminarDto() {
		super();
		auditRequest = new AuditRequestDto();
		id = new ArrayList<Integer>();
	}

	public AuditRequestDto getAuditRequest() {
		return auditRequest;
	}

	public void setAuditRequest(AuditRequestDto auditRequest) {
		this.auditRequest = auditRequest;
	}

	public List<Integer> getId() {
		return id;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}
	

}
