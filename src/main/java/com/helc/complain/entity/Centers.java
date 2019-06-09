package com.helc.complain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayDeque;
import java.util.List;

public class Centers implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private List<Center> complain_centers;
	
	private Date fecha;
	
	public List<Center> getComplain_centers() {
		return complain_centers;
	}
	public void setComplain_centers(List<Center> complain_centers) {
		this.complain_centers = complain_centers;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}	
}
