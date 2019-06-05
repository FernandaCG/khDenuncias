package com.helc.complain.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.List;

public class Centers implements Serializable{

	private static final long serialVersionUID = 1L;
		
	private List<Center> complain_centers;
	
	public List<Center> getComplain_centers() {
		return complain_centers;
	}
	public void setComplain_centers(List<Center> complain_centers) {
		this.complain_centers = complain_centers;
	}
	
	
}
