package com.fdmgroup.gwbPlatformBulletin.exceptions;

public class ValidationException extends RuntimeException {
    
	private static final long serialVersionUID = 2169474973577603971L;

	public ValidationException(String message) {
        super(message);
    }
}
