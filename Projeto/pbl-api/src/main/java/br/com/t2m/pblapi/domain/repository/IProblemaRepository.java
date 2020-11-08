package br.com.t2m.pblapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Problema;

@Repository
public interface IProblemaRepository extends JpaRepository<Problema, Long> {

	boolean existsByDescricao(String descricao);
	Optional<Problema> findByDescricao(String descricao);

}
