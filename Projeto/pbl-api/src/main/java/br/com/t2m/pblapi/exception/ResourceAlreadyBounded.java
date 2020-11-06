package br.com.t2m.pblapi.exception;

public class ResourceAlreadyBounded extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5073676557744801866L;

	public ResourceAlreadyBounded(String message) {
		this.message = message;
	}
	
	public ResourceAlreadyBounded(String message, String value) {
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
