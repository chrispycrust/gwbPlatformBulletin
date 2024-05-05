package com.fdmgroup.gwbPlatformBulletin.exceptions;

public class NonexistentPostException extends Exception {

	private static final long serialVersionUID = 3864746056206432570L;

	public NonexistentPostException(String message) {
		super(message);
	}

}
