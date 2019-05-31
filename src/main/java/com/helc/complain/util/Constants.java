package com.helc.complain.util;

public class Constants {

	private Constants() {

	}

	public static final String ERROR = "error";
	public static final String MESSAGE = "message";
	public static final String UPLOADS = "uploads";
	public static final String ENTITY = "complain";
	public static final String QUERY_ERROR = "Failed to perform the query in the database";
	public static final String EMPTY_OBJECT_ERROR = "You are sending empty object";
	public static final String NOT_FOUND_USER ="User does not exist";
	public static final String INVALID_USER_STATE = "User does not have valid state";
	public static final String NOT_SEND_EMAIL = "Email could not be sent";
	public static final String SUCCESSFUL_QUERY = "The query has been executed successful";

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	public static final String EMAIL_ADDRESS = "di.gital.33b@gmail.com";
	
	public static final String COMPLAIN_DESCRIPTION = "description";
	public static final String DEPUTY_CONTACT_TEMPLATE = "deputy_contact.ftl";
	public static final String INSTITUTION_CONTACT_TEMPLATE = "institution_contact.ftl";
	public static final String INSTITUTION_RESPONSE = "institution_response.ftl";
	public static final String EMAIL_REQUEST_SUBJECT = "Solicitud de apoyo ciudadano";
	
	public static final String NAME_PARAM = "name";
	public static final Integer NUMBER_PAGE = 10;
	
	public static final String DEFAULT_IMAGE = "no-evidence.jpg";
	public static final String IMAGE_PATH = "https://complain-service.herokuapp.com/api/v1/complain/uploads/img/";
	
	
}
