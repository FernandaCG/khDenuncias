package com.helc.complain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

public class Complain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String _id;

	@NotEmpty(message = "can't be empty")
	@Size(min = 1, max = 30, message = "must have a length between 1 and 30")
	private String titulo;

	@NotEmpty(message = "can't be empty")
	@Size(min = 1, max = 30, message = "must have a length between 1 and 30")
	private String descripcion;
	
	private Date sendDate;

	private GeoJsonPoint punto;
	
	@Size(min = 1, max = 30, message = "must have a length between 1 and 30")
	private String email;



	public Complain() {	}


	public String get_id() {
		return _id;
	}


	public void set_id(String _id) {
		this._id = _id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Date getSendDate() {
		return sendDate;
	}


	public void setSendDate(Date fecha) {
		this.sendDate = fecha;
	}


	public GeoJsonPoint getPunto() {
		return punto;
	}

	public void setPunto(GeoJsonPoint punto) {
		this.punto = punto;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Complain [_id=" + _id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", fecha=" + sendDate
				+ ", punto=" + punto + "]";
	}
	
}
