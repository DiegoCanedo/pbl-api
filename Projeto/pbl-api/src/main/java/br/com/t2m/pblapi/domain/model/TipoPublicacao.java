package br.com.t2m.pblapi.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tipo_publicacao")
public class TipoPublicacao implements Serializable{

	private static final long serialVersionUID = -2981580599037921994L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_tipo_publicacao")
	private long idTipoPublicacao;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public long getIdTipoPublicacao() {
		return idTipoPublicacao;
	}

	public void setIdTipoPublicacao(long idTipoPublicacao) {
		this.idTipoPublicacao = idTipoPublicacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idTipoPublicacao ^ (idTipoPublicacao >>> 32));
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
		TipoPublicacao other = (TipoPublicacao) obj;
		if (idTipoPublicacao != other.idTipoPublicacao)
			return false;
		return true;
	}
	
}
