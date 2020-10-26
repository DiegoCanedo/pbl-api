package br.com.t2m.pblapi.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class ErrorBody {
	
	private Integer status;
	private  String mensage;
	private  LocalDateTime date;
	private  Map<String,String> fields;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMensage() {
		return mensage;
	}
	public void setMensage(String mensage) {
		this.mensage = mensage;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public Map<String, String> getFields() {
		return fields;
	}
	public void setFields(Map<String, String> fields) {
		this.fields = fields;
	}	
}