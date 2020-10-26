package br.com.t2m.pblapi.domain.repository;

import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Professor;

@Repository
public interface IProfessorRepository extends IUsuarioBaseRepository<Professor> {

}
