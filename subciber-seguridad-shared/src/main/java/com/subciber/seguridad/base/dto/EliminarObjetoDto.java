package com.subciber.seguridad.base.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EliminarObjetoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Integer> items;

	public EliminarObjetoDto() {
		super();
		items = new ArrayList<Integer>();
	}
	
	public List<Integer> getItems() {
		return items;
	}

	public void setItems(List<Integer> items) {
		this.items = items;
	}
	
}
