package br.com.t2m.pblapi.domain.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "professor")
@PrimaryKeyJoinColumn(name = "id_usuario")
public class Professor extends Usuario {
	private static final long serialVersionUID = 3672682498252245272L;

	@NotBlank
	private String nome;

	@ManyToMany
	@JoinTable(name = "professor_disciplina",
			   joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"), 
	           inverseJoinColumns = @JoinColumn(name = "id_disciplina", referencedColumnName = "id_disciplina"))
	private Set<Disciplina> disciplinas;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	private List<Publicacao> publicacoes;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(Set<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public List<Publicacao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Publicacao> publicacoes) {
		this.publicacoes = publicacoes;
	}
	
	
}
