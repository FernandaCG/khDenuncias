package com.helc.complain.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.helc.complain.util.GeoJsonDeserializer;

public class Center implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@JsonDeserialize(using = GeoJsonDeserializer.class)
	private GeoJsonPoint center;
	
	private Double radio;
	
	private String estado="En espera";

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
}