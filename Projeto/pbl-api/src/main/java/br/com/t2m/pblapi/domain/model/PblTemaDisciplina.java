package br.com.t2m.pblapi.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pbl_tema_disciplina")
public class PblTemaDisciplina {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pbl_tema_disciplina")
	private Long Id;

	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "pblTemaDisciplina")
	private Pbl pbl;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_tema_pbl")
	private TemaPbl tema;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_disciplina")
	private Disciplina disciplina;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Pbl getPbl() {
		return pbl;
	}

	public void setPbl(Pbl pbl) {
		this.pbl = pbl;
	}

	public TemaPbl getTema() {
		return tema;
	}

	public void setTema(TemaPbl tema) {
		this.tema = tema;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		PblTemaDisciplina other = (PblTemaDisciplina) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	

}
