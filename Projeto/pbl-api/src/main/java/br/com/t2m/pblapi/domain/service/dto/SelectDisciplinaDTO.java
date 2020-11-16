package br.com.t2m.pblapi.domain.service.dto;

import br.com.t2m.pblapi.domain.model.Disciplina;

public class SelectDisciplinaDTO {

	private String value;
	private String label;

	public SelectDisciplinaDTO(Disciplina disciplina) {
		this.value = disciplina.getId().toString();
		this.label = disciplina.getNome();
	}

	public SelectDisciplinaDTO() {

	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
