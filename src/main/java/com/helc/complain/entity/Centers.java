package com.helc.complain.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayDeque;

public class Centers implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private ArrayDeque<Center> complain_centers;
	
	private Date fecha;

	public ArrayDeque<Center> getComplain_centers() {
		return complain_centers;
	}

	public void setComplain_centers(ArrayDeque<Center> complain_centers) {
		this.complain_centers = complain_centers;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}