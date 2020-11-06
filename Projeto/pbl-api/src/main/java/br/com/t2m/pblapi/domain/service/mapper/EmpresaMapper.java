package br.com.t2m.pblapi.domain.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Empresa;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.service.dto.EmpresaDTO;


@Service
public class EmpresaMapper {

	public List<EmpresaDTO> EmpresasToEmpresasDTOs(List<Empresa> Empresas) {
		return Empresas.stream().filter(Objects::nonNull).map(this::EmpresaToEmpresaDTO).collect(Collectors.toList());
	}

	public List<Empresa> EmpresaDTOsToEmpresas(List<EmpresaDTO> EmpresasDTOs) {
		return EmpresasDTOs.stream().filter(Objects::nonNull).map(this::EmpresaDTOTOEmpresa).collect(Collectors.toList());
	}

	public EmpresaDTO EmpresaToEmpresaDTO(Empresa Empresa) {
		return new EmpresaDTO(Empresa);
	}

	public Empresa EmpresaDTOTOEmpresa(EmpresaDTO EmpresaDTO) {
		if (EmpresaDTO == null)
			return null;

		Empresa Empresa = new Empresa();
		Empresa.setId(EmpresaDTO.getId());
		Empresa.setEmail(EmpresaDTO.getEmail());
		Empresa.setNome(EmpresaDTO.getNome());
		Empresa.setCnpj(EmpresaDTO.getCnpj());
		Empresa.setEndereco(EmpresaDTO.getEndereco());
		Empresa.setUrlLog(EmpresaDTO.getUrlLog());
		Empresa.setAtivo(EmpresaDTO.isAtivo());
		Empresa.setExcluido(EmpresaDTO.isExcluido());
		Set<Perfil> perfis = EmpresaDTO.getPerfis();
		Empresa.setPerfil(perfis);
		return Empresa;
	}

	public Empresa EmpresaFromId(Long id) {
		if (id == null) {
			return null;
		}

		Empresa Empresa = new Empresa();
		Empresa.setId(id);
		return Empresa;
	}
}
