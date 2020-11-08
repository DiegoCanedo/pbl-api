package br.com.t2m.pblapi.exception;

public class TaskRestrictionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public TaskRestrictionException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
