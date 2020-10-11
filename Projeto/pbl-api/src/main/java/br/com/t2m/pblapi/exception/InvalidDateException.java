package br.com.t2m.pblapi.exception;

public class InvalidDateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDateException(String message) {
		this.message = message;
	}

	private String message;

	public String getMessage() {
		return message;
	}
}
