package br.com.t2m.pblapi.domain.service.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.service.dto.AlunoAtivoDTO;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;

@Service
public class AlunoAtivoMapper {

	
	public List<Aluno> alunoDTOsToalunos(List<AlunoAtivoDTO> alunosAtivosDTOs) {
		return alunosAtivosDTOs.stream().filter(Objects::nonNull).map(this::alunoAtivoDTOTOAluno)
				.collect(Collectors.toList());
	}

	public Aluno alunoAtivoDTOTOAluno(AlunoAtivoDTO alunoDTO) {
		if (alunoDTO == null)
			return null;

		Aluno aluno = new Aluno();
		aluno.setAtivo(alunoDTO.isAtivo());
		return aluno;
	}

	public Aluno alunoFromId(Long id) {
		if (id == null) {
			return null;
		}

		Aluno aluno = new Aluno();
		aluno.setId(id);
		return aluno;
	}
}
