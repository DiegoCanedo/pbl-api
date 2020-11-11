package br.com.t2m.pblapi.domain.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Professor extends Usuario {

	@NotBlank
	private String nome;

	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	private List<Publicacao> publicacoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}
	
	
}
