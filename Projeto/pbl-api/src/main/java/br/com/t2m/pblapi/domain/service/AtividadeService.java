package br.com.t2m.pblapi.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.el.stream.Stream;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.controller.AtividadeTarefaDTO;
import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.model.AtividadePbl;
import br.com.t2m.pblapi.domain.model.Disciplina;
import br.com.t2m.pblapi.domain.model.Pbl;
import br.com.t2m.pblapi.domain.model.Professor;
import br.com.t2m.pblapi.domain.model.Tarefa;
import br.com.t2m.pblapi.domain.repository.IAtividadePblRepository;
import br.com.t2m.pblapi.domain.repository.IAtividadeRepository;
import br.com.t2m.pblapi.domain.repository.IDisciplinaRepository;
import br.com.t2m.pblapi.domain.repository.IPblRepository;
import br.com.t2m.pblapi.domain.repository.IProfessorRepository;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
@Transactional
public class AtividadeService {

	@Autowired
	private IAtividadeRepository atividadeRepository;

	@Autowired
	private IAtividadePblRepository atividadePblRepository;

	@Autowired
	private IPblRepository pblRepository;

	@Autowired
	private IDisciplinaRepository disciplinaRepository;

	@Autowired
	private IProfessorRepository professorRepository;

	@Transactional(readOnly = true)
	public Atividade getById(Long idAtividade) {
		Optional<Atividade> optAtividade = atividadeRepository.findById(idAtividade);

		if (optAtividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, idAtividade.toString());
		}

		return optAtividade.get();
	}

	@Transactional(readOnly = true)
	public Set<Atividade> getByPbl(Long idPbl) {
		Optional<Pbl> optPbl = pblRepository.findById(idPbl);

		if (optPbl.isEmpty()) {
			throw new ResourceNotFoundException(Constants.PBL_NAO_ENCONTRADO, idPbl.toString());
		}

		Set<Atividade> atividades = atividadeRepository.findByAtividadePbls_Pbl(optPbl.get());

		atividades.forEach(a -> {
			a.setAtividadePbls(a.getAtividadePbls().stream().filter(f -> f.getPbl().getIdPbl().equals(idPbl))
					.collect(Collectors.toSet()));
		});

		return atividades;
	}

	@Transactional(readOnly = true)
	public Set<Atividade> getByDisciplina(Long idDisciplina) {
		Optional<Disciplina> optDisciplina = disciplinaRepository.findById(idDisciplina);

		if (optDisciplina.isEmpty()) {
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA, idDisciplina.toString());
		}

		return atividadeRepository.findByDisciplina(optDisciplina.get());
	}

	@Transactional(readOnly = true)
	public List<AtividadeTarefaDTO> getByIdAluno(Long idAluno, Long idPbl) {

		Optional<Pbl> optPbl = pblRepository.findById(idPbl);

		List<Atividade> atividades = atividadeRepository.findByIdUsuarioAndIdPbl(idAluno, idPbl);
		List<AtividadeTarefaDTO> atividadeTarefaDTOs = new ArrayList<>();

		Function<AtividadePbl, List<Aluno>> alunosFunction = a -> a.getPbl().getAluno();

		if (atividades.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_ALUNO_NAO_ENCONTRADA);
		}

		atividades.forEach(a -> {
			AtividadeTarefaDTO at = new AtividadeTarefaDTO();

			List<Tarefa> tarefas = a.getAtividadePbls().parallelStream().filter(f -> f.getIdAtividade() == a.getId())
					.filter(f -> f.getPbl() == optPbl.get()).map(AtividadePbl::getTarefas)
					.flatMap(Collection<Tarefa>::stream).collect(Collectors.toList());

			List<Aluno> alunos = (List<Aluno>) a.getAtividadePbls().parallelStream()
					.filter(f -> f.getIdAtividade() == a.getId()).filter(f -> f.getPbl() == optPbl.get())
					.map(alunosFunction).flatMap(Collection<Aluno>::stream).collect(Collectors.toList());

			at.setId(a.getId());
			at.setTitulo(a.getTitulo());
			at.setDataConclusao(a.getDataConclusao());
			at.setIdPbl(optPbl.get().getIdPbl());
			at.setTarefas(tarefas);
			at.setAlunos(alunos);
			atividadeTarefaDTOs.add(at);

		});

		return atividadeTarefaDTOs;
	}

	/**
	 * Insere uma nova atividade, faz o vinculo se existir PBLs na mesma disciplina.
	 *
	 * @param atividade .
	 * @return atividade incluida.
	 */
	public Atividade insert(Atividade atividade) {

		Optional<Disciplina> optDisciplina = disciplinaRepository.findById(atividade.getDisciplina().getId());

		if (optDisciplina.isEmpty()) {
			throw new ResourceNotFoundException(Constants.DISCIPLINA_NAO_ENCONTRADA,
					atividade.getDisciplina().getId().toString());
		}

		Set<Pbl> pbls = pblRepository.findAllByPblTemaDisciplina_Disciplina_Id(atividade.getDisciplina().getId());

		if (pbls.isEmpty()) {
			return atividadeRepository.save(atividade);
		}

		Set<AtividadePbl> atividadePbls = new HashSet<AtividadePbl>();
		pbls.stream().forEach(p -> {
			AtividadePbl atividadePbl = new AtividadePbl();
			atividadePbl.setPbl(p);
			atividadePbls.add(atividadePbl);
		});

		atividade.setAtividadePbls(atividadePbls);

		return atividadeRepository.save(atividade);
	}

	/**
	 * Ao iniciar um PBL, faz o vinculo de atividades existentes para aquela
	 * disciplina.
	 *
	 * @param pbl.
	 * @return atividades vinculadas.
	 */
	public void bindPblToAtividadePBl(Pbl pbl) {

		Set<Atividade> atividades = this.getByDisciplina(pbl.getPblTemaDisciplina().getDisciplina().getId());

		atividades.forEach(a -> {
			atividadePblRepository.bindPblToAtividade(pbl.getIdPbl(), a.getId());
		});
	}

	public Atividade update(Atividade atividade, Long id) {
		Optional<Professor> optProfessor = professorRepository.findById(atividade.getProfessor().getId());
		Optional<Atividade> optAtividade = atividadeRepository.findById(id);

		if (optProfessor.isEmpty())
			throw new ResourceNotFoundException("Professor não existe");

		if (optAtividade.isEmpty())
			throw new ResourceNotFoundException("Atividade não existe");

		if (atividadeRepository.existsByAtividadePbls_AlunoIsNotNullAndId(optAtividade.get().getId()))
			throw new RuntimeException("Atividade não pode ser editada, pois ja esta vinculada à um aluno");

		return atividadeRepository.save(atividade);
	}

	public AtividadePbl updateAtividadePbl(Atividade atividade, Long id) {
		Optional<Professor> optProfessor = professorRepository.findById(atividade.getProfessor().getId());
		Optional<Atividade> optAtividade = atividadeRepository.findById(id);

		if (optProfessor.isEmpty())
			throw new ResourceNotFoundException("Professor não existe");

		if (optAtividade.isEmpty())
			throw new ResourceNotFoundException("Atividade não existe");

		return atividadePblRepository.save(atividade.getAtividadePbls().stream().findFirst().get());

	}

	public void delete(Long idAtividade) {
		Optional<Atividade> optAtividade = atividadeRepository.findById(idAtividade);

		if (optAtividade.isEmpty())
			throw new ResourceNotFoundException("Atividade não existe");

		if (atividadeRepository.existsByAtividadePbls_AlunoIsNotNullAndId(optAtividade.get().getId()))
			throw new RuntimeException("Atividade não pode ser excluida, pois ja esta vinculada à um aluno");

		atividadeRepository.delete(optAtividade.get());
	}
}
