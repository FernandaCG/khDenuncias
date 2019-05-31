package com.helc.complain.util;

public enum ComplainState {

	E0("e0", ""), E1("e1", "");

	private String idState;

	private String description;

	private ComplainState(String idState, String description) {
		this.idState = idState;
		this.description = description;
	}

	public String getIdState() {
		return idState;
	}

	public void setIdState(String idState) {
		this.idState = idState;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
