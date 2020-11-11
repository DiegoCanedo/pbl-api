package br.com.t2m.pblapi.config.services;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.EPerfil;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.repository.IPerfilRepository;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
public class PblUserRole {

	@Autowired
	IPerfilRepository perfilRepository;

	public Perfil setPerfil(EPerfil perfil) {

		Optional<Perfil> optPerfil = perfilRepository.findByNome(perfil);

		if (optPerfil.isEmpty()) {
			throw new ResourceNotFoundException(Constants.PERFIL_NAO_ENCONTRADO, perfil.name());
		}

		return optPerfil.get();
	}

}
