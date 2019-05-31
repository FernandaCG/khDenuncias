package com.helc.complain.entity;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.geo.GeoJsonLineString;

public class Ductos implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	private String _id;
	
	private String nombre;
	
	private GeoJsonLineString location;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public GeoJsonLineString getLocation() {
		return location;
	}

	public void setLocation(GeoJsonLineString location) {
		this.location = location;
	}
	
}
