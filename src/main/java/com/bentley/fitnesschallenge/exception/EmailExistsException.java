package com.bentley.fitnesschallenge.exception;

public class EmailExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmailExistsException( String msg ) {
		super( msg );
	}
}
