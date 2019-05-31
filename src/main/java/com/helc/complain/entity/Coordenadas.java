package com.helc.complain.entity;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.helc.complain.util.PointToJsonSerializer;
import com.helc.complain.util.GeoJsonDeserializer;
import com.vividsolutions.jts.geom.Point;

public class Coordenadas {

	@JsonSerialize(using = PointToJsonSerializer.class)
	@JsonDeserialize(using = GeoJsonDeserializer.class)
	private Point coordenada;

	public Point getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Point coordenada) {
		this.coordenada = coordenada;
	}
	
	public double getLatitud() {
		return this.coordenada.getX();
	}
	
	public double getLongitud() {
		return this.coordenada.getY();
	}

}
