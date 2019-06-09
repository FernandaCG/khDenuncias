package com.helc.complain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.helc.complain.util.GeoJsonDeserializer;

public class Center implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String _id;
	
	@JsonDeserialize(using = GeoJsonDeserializer.class)
	private GeoJsonPoint center;
	
	private Double radio;
	
	private String estado="Sin asignar";
	
	private Date fecha;
	
	private String asignadoSEDENA;
	
	private String asignadoPEMEX;
	
	public String getAsignadoSEDENA() {
		return asignadoSEDENA;
	}

	public void setAsignadoSEDENA(String asignadoSEDENA) {
		this.asignadoSEDENA = asignadoSEDENA;
	}

	public String getAsignadoPEMEX() {
		return asignadoPEMEX;
	}

	public void setAsignadoPEMEX(String asignadoPEMEX) {
		this.asignadoPEMEX = asignadoPEMEX;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public GeoJsonPoint getCenter() {
		return center;
	}

	public void setCenter(GeoJsonPoint center) {
		this.center = center;
	}

	public Double getRadio() {
		return radio;
	}

	public void setRadio(Double radio) {
		this.radio = radio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
}
