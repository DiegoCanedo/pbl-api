package br.com.t2m.pblapi.domain.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tarefa_atividade")
public class Tarefa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarefa")
	private Long id;
	
	@NotNull	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Instant dataCriacao;
	
	@NotNull
	@Column(name = "descricao")
	private String descricao;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataConclusao;
	
	@NotNull
	@Column(name = "concluido")
	private boolean concluido;
	
	@ManyToMany	
	@JoinTable(name = "tarefa_aluno", 
		joinColumns = @JoinColumn(name = "id_tarefa", referencedColumnName = "id_tarefa"), 
			inverseJoinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"))
	private List<Aluno> alunos;	
	
	public Tarefa() {
		
	}	

	public Tarefa(Long id, @NotNull Instant dataCriacao, @NotNull String descricao, @NotNull Date dataConclusao,
			@NotNull boolean concluido, List<Aluno> alunos) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.descricao = descricao;
		this.dataConclusao = dataConclusao;
		this.concluido = concluido;
		this.alunos = alunos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDataCriacao() {
		return dataCriacao;
	}	

	public String getDescricao() {
		return descricao;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}	

	public boolean isConcluido() {
		return concluido;
	}

	public void setDataCriacao(Instant dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}
	
	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}		

}