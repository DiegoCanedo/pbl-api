package br.com.t2m.pblapi.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Aluno;

@Repository
public interface IAlunoRepository extends IUsuarioBaseRepository<Aluno> {
	public boolean existsByEmail(String email);

}
