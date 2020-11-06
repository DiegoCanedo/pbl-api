package br.com.t2m.pblapi.domain.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.EPerfil;
import br.com.t2m.pblapi.domain.model.Empresa;
import br.com.t2m.pblapi.domain.model.Perfil;
import br.com.t2m.pblapi.domain.repository.IEmpresaRepository;
import br.com.t2m.pblapi.domain.repository.IPerfilRepository;
import br.com.t2m.pblapi.domain.service.dto.EmpresaDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsAtivoDTO;
import br.com.t2m.pblapi.domain.service.dto.UsuarioIsExcluidoDTO;
import br.com.t2m.pblapi.domain.service.mapper.EmpresaMapper;
import br.com.t2m.pblapi.exception.ResourceAlreadyExistsException;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;



@Service
@Transactional
public class EmpresaService {
	
	@Autowired
	IEmpresaRepository empresaRepository;
	
	@Autowired
	EmpresaMapper empresaMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	IPerfilRepository perfilRepository;

	@Transactional(readOnly = true)
	public List<EmpresaDTO> getAll() {
		return empresaRepository.findAll().stream().map(EmpresaDTO::new).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public EmpresaDTO getById(Long id) {
		Optional<EmpresaDTO> opt = empresaRepository.findById(id).map(EmpresaDTO::new);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.EMPRESA_NÃO_ENCONTRADA, id.toString());

		return opt.get();
	}

	public Empresa insert(EmpresaDTO empresaDTO, String senha) {
		
		Optional<Perfil> optPerfil = perfilRepository.findByNome(EPerfil.ROLE_EMPRESA);
		
		if(optPerfil.isEmpty())
		{
			throw new ResourceNotFoundException(Constants.PERFIL_NAO_ENCONTRADO, EPerfil.ROLE_EMPRESA.name());
		}

		if (empresaDTO.getId() != null)

			if (empresaRepository.existsByCnpj(empresaDTO.getCnpj()))
				throw new ResourceAlreadyExistsException(Constants.USUARIO_CNPJ_JA_EXISTE, empresaDTO.getCnpj());

		
		Empresa empresa = empresaMapper.EmpresaDTOTOEmpresa(empresaDTO);
		
		empresa.setSenha(passwordEncoder.encode(senha));
		Set<Perfil> perfis = new HashSet<>();

		perfis.add(optPerfil.get());
		empresa.setPerfil(perfis);
		
		empresa.setAtivo(false);
		empresa.setExcluido(false);
		return empresaRepository.save(empresa);

	}

	public EmpresaDTO update(EmpresaDTO empresaDTO) {
		Optional<Empresa> opt = empresaRepository.findById(empresaDTO.getId());

		opt.ifPresentOrElse(o -> this.validaUsuario(o, empresaDTO), () -> {
			throw new ResourceNotFoundException(Constants.EMPRESA_NÃO_ENCONTRADA, empresaDTO.getId().toString());
		});

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(empresa -> {
			empresa.setNome(empresaDTO.getNome());
			empresa.setEmail(empresaDTO.getEmail());
			empresa.setEndereco(empresaDTO.getEndereco());
			empresa.setCnpj(empresaDTO.getCnpj());
			empresa.setUrlLog(empresaDTO.getUrlLog());
			return empresa;
		}).map(EmpresaDTO::new).get();
	}
	
	public EmpresaDTO updateAtivo(UsuarioIsAtivoDTO empresaIsAtivoDTO, Long id) {
		Optional<Empresa> opt = empresaRepository.findById(id);

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(empresa -> {
			empresa.setId(id);
			empresa.setAtivo(empresaIsAtivoDTO.isAtivo());
			return empresa;
		}).map(EmpresaDTO::new).get();
	}
	
	public EmpresaDTO updateExcluido(UsuarioIsExcluidoDTO empresaIsExcluidoDTO, Long id) {
		Optional<Empresa> opt = empresaRepository.findById(id);

		// TODO Falta setar o array de perfil;
		return Optional.of(opt).filter(Optional::isPresent).map(Optional::get).map(empresa -> {
			empresa.setId(id);
			empresa.setExcluido(empresaIsExcluidoDTO.isExcluido());
			return empresa;
		}).map(EmpresaDTO::new).get();
	}

	public void delete(Long id) {
		Optional<Empresa> opt = empresaRepository.findById(id);

		if (opt.isEmpty())
			throw new ResourceNotFoundException(Constants.EMPRESA_NÃO_ENCONTRADA, id.toString());

		Empresa empresa = opt.get();
		empresa.setExcluido(true);
		empresa.setAtivo(false);
		empresaRepository.save(empresa);
	}

	private void validaUsuario(Empresa antigo, EmpresaDTO novo) {
		if (!novo.getCnpj().equalsIgnoreCase(antigo.getCnpj()) && empresaRepository.existsByCnpj(novo.getCnpj())) {
			throw new ResourceAlreadyExistsException(Constants.USUARIO_CNPJ_JA_EXISTE, novo.getCnpj());
		}
	}


}
