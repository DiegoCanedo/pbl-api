package br.com.t2m.pblapi.domain.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.model.Professor;

public class ProfessorDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8369244573575768444L;

	private Long id;

	@NotBlank
	private String nome;
	
	@NotBlank
	@Email(regexp = Constants.EMAIL_REGEX, message = "e-mail deve estar em um formato válido.")
	@Size(min = 5, max = 254)
	private String email;

	private boolean ativo;
	
	private boolean excluido;
	
	private Instant createdDate = new Date().toInstant();
	@NotNull
	private Disciplina disciplina;
	
	private Set<Perfil> perfis;

	public ProfessorDTO(Professor professor) {
		this.id = professor.getId();
		this.email = professor.getEmail();
		this.nome = professor.getNome();
		this.disciplina = professor.getDisciplina();
		this.perfis = professor.getPerfil();
		this.ativo = professor.isAtivo();
		this.excluido = professor.isExcluido();
	}
	
	public ProfessorDTO() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Set<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(Set<Perfil> perfis) {
		this.perfis = perfis;
	}

}
