package com.helc.complain.util;

public enum Numbers {
	CERO(0), UNO(1), DOS(2), TRES(3), CUATRO(4), CINCO(5), SEIS(6), SIETE(7), OCHO(8), NUEVE(9),
	DIEZ(10), ONCE(11), DOCE(12), TRECE(13), CATORCE(14), QUINCE(15), TREINTA_UNO(31), CINCUENTA(50), CIEN(100);

	private int value;

	private Numbers(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public float getFlotante() {
		return (float) value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Integer getValueInteger() {
		return new Integer(this.value);
	}

	public Double getValueDouble() {
		return new Double(this.value);
	}
}
