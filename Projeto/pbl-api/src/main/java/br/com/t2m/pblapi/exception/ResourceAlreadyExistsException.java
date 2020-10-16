package br.com.t2m.pblapi.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException(String message) {
		this.message = message;
	}
	
	public ResourceAlreadyExistsException(String message, String value) {
		this.value = value;
		this.message = message + value;
	}
	
	private String message;
	private String value;
	
	public String getMessage() {
		return message;
	}

	public String getValue() {
		return value;
	}
	
}
