package br.com.t2m.pblapi.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Contato;

@Repository
public interface IContatoRepository extends JpaRepository<Contato, Long>{

	List<Contato> findByNomeContato(String nomeContato);

	public boolean existsByNomeContato(String nomeContato);
}
