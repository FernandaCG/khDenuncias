package com.helc.complain.entity;

import java.io.Serializable;
import java.util.Date;

public class InstitutionResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date responseDate;

	private String description;

	private String userScore;

	private Boolean legalRequest;

	private String deputyId;

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserScore() {
		return userScore;
	}

	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}

	public Boolean getLegalRequest() {
		return legalRequest;
	}

	public void setLegalRequest(Boolean legalRequest) {
		this.legalRequest = legalRequest;
	}

	public String getDeputyId() {
		return deputyId;
	}

	public void setDeputyId(String deputyId) {
		this.deputyId = deputyId;
	}

}
