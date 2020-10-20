package br.com.t2m.pblapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.TemaPbl;

@Repository
public interface ITemaPblRepository extends JpaRepository<TemaPbl, Long> {
	public List<TemaPbl> findByNomeContainingIgnoreCase(String nome);
	public List<TemaPbl> findByDisciplinas_Id(Long id);
}