package br.com.t2m.pblapi.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Aluno;

@Repository
public interface IAlunoRepository extends JpaRepository<Aluno, Long> {

}