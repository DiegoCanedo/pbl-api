package br.com.t2m.pblapi.domain.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Professor;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.service.dto.ProfessorDTO;

@Service
public class ProfessorMapper {

	public List<ProfessorDTO> ProfessorsToProfessorsDTOs(List<Professor> Professors) {
		return Professors.stream().filter(Objects::nonNull).map(this::ProfessorToProfessorDTO).collect(Collectors.toList());
	}

	public List<Professor> ProfessorDTOsToProfessors(List<ProfessorDTO> ProfessorsDTOs) {
		return ProfessorsDTOs.stream().filter(Objects::nonNull).map(this::ProfessorDTOTOProfessor).collect(Collectors.toList());
	}

	public ProfessorDTO ProfessorToProfessorDTO(Professor Professor) {
		return new ProfessorDTO(Professor);
	}

	public Professor ProfessorDTOTOProfessor(ProfessorDTO ProfessorDTO) {
		if (ProfessorDTO == null)
			return null;

		Professor Professor = new Professor();
		Professor.setId(ProfessorDTO.getId());
		Professor.setEmail(ProfessorDTO.getEmail());
		Professor.setAtivo(ProfessorDTO.isAtivo());
		Professor.setNome(ProfessorDTO.getNome());
		Professor.setExcluido(ProfessorDTO.isExcluido());
		Professor.setDisciplinas(ProfessorDTO.getDisciplinas());
		Set<Perfil> perfis = ProfessorDTO.getPerfis();
		Professor.setPerfil(perfis);
		return Professor;
	}

	public Professor ProfessorFromId(Long id) {
		if (id == null) {
			return null;
		}

		Professor Professor = new Professor();
		Professor.setId(id);
		return Professor;
	}
}
