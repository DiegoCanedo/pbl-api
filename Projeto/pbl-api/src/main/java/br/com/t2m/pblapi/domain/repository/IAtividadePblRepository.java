package br.com.t2m.pblapi.domain.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.model.AtividadePbl;
import br.com.t2m.pblapi.domain.model.Pbl;

@Repository
public interface IAtividadePblRepository extends JpaRepository<AtividadePbl, Long> {

}
