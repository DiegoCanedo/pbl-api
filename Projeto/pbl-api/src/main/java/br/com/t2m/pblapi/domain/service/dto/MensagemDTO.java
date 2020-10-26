package br.com.t2m.pblapi.domain.service.dto;

public class MensagemDTO {

	private String message;

	public MensagemDTO(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
