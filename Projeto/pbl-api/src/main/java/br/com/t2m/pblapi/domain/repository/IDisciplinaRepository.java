package br.com.t2m.pblapi.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Disciplina;

@Repository
public interface IDisciplinaRepository extends JpaRepository<Disciplina, Long> {
	public Optional<Disciplina> findByNomeContainingIgnoreCase(String nome);
	public boolean existsByNome(String nome);
}
