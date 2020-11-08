package br.com.t2m.pblapi.domain.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Contato;
import br.com.t2m.pblapi.domain.model.Empresa;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.model.TipoContato;
import br.com.t2m.pblapi.domain.service.dto.ContatoDTO;
import br.com.t2m.pblapi.domain.service.dto.EmpresaDTO;

@Service
public class ContatoMapper {

	public List<ContatoDTO> ContatosToContatosDTOs(List<Contato> Contato) {
		return Contato.stream().filter(Objects::nonNull).map(this::ContatoToContatoDTO).collect(Collectors.toList());
	}

	public List<Contato> ContatoDTOsToContatos(List<ContatoDTO> ContatoDTOs) {
		return ContatoDTOs.stream().filter(Objects::nonNull).map(this::ContatoDTOTOContato)
				.collect(Collectors.toList());
	}

	public ContatoDTO ContatoToContatoDTO(Contato contato) {
		return new ContatoDTO(contato);
	}

	public Contato ContatoDTOTOContato(ContatoDTO ContatoDTO) {
		if (ContatoDTO == null)
			return null;

		Contato contato = new Contato();
		contato.setId(ContatoDTO.getId());
		contato.setEmail(ContatoDTO.getEmail());
		contato.setNomeContato(ContatoDTO.getNomeContato());
		contato.setTipoContato(ContatoDTO.getTipoContato());

		return contato;
	}
}
