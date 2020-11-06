package br.com.t2m.pblapi.domain.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.t2m.pblapi.domain.model.Empresa;

@Transactional
@Repository
public interface IEmpresaRepository extends IUsuarioBaseRepository<Empresa> {
	public boolean existsByCnpj(String cnpj); 


}