package br.com.t2m.pblapi.domain.service.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;

@Service
public class AlunoMapper {

	public List<AlunoDTO> alunosToAlunosDTOs(List<Aluno> alunos) {
		return alunos.stream().filter(Objects::nonNull).map(this::alunoToAlunoDTO).collect(Collectors.toList());
	}

	public List<Aluno> alunoDTOsToalunos(List<AlunoDTO> alunosDTOs) {
		return alunosDTOs.stream().filter(Objects::nonNull).map(this::alunoDTOTOAluno).collect(Collectors.toList());
	}

	public AlunoDTO alunoToAlunoDTO(Aluno aluno) {
		return new AlunoDTO(aluno);
	}

	public Aluno alunoDTOTOAluno(AlunoDTO alunoDTO) {
		if (alunoDTO == null)
			return null;

		Aluno aluno = new Aluno();
		aluno.setId(alunoDTO.getId());
		aluno.setEmail(alunoDTO.getEmail());
		aluno.setAtivo(alunoDTO.isAtivo());
		aluno.setNome(alunoDTO.getNome());
		aluno.setMatricula(alunoDTO.getMatricula());
		aluno.setAtivo(alunoDTO.isAtivo());
		aluno.setExcluido(alunoDTO.isExcluido());
		Set<Perfil> perfis = this.perfilsFromString(alunoDTO.getPerfil());
		aluno.setPerfil(perfis);
		return aluno;
	}

	private Set<Perfil> perfilsFromString(Set<String> perfisAsString) {
		Set<Perfil> perfis = new HashSet<>();

		if (perfisAsString != null) {
			perfis = perfisAsString.stream().map(string -> {
				Perfil perfil = new Perfil();
				perfil.setRole(string);
				return perfil;
			}).collect(Collectors.toSet());
		}
		return perfis;
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
