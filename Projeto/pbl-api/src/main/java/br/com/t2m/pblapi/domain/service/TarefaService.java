package br.com.t2m.pblapi.domain.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.AtividadePbl;
import br.com.t2m.pblapi.domain.model.Tarefa;
import br.com.t2m.pblapi.domain.repository.IAlunoRepository;
import br.com.t2m.pblapi.domain.repository.IAtividadePblRepository;
import br.com.t2m.pblapi.domain.repository.ITarefaRepository;
import br.com.t2m.pblapi.domain.service.dto.PostTarefaDTO;
import br.com.t2m.pblapi.domain.service.dto.PutTarefaDTO;
import br.com.t2m.pblapi.domain.service.dto.TarefaDTO;
import br.com.t2m.pblapi.exception.ResourceAlreadyExistsException;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;
import br.com.t2m.pblapi.exception.TaskRestrictionException;

@Service
public class TarefaService {
	
	@Autowired
	private ITarefaRepository tarefaRepository;
	
	@Autowired
	private IAtividadePblRepository atividadeRepository;
	
	@Autowired
	private IAlunoRepository alunoRepository;
	
	@Transactional
	public TarefaDTO postTarefa(PostTarefaDTO novaTarefa){
		
		Optional<AtividadePbl> atividade = atividadeRepository.findById(novaTarefa.getIdAtividade());
		
		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, novaTarefa.getIdAtividade().toString());
		}

		Tarefa tarefa = new Tarefa();
		tarefa.setTitulo(novaTarefa.getTitulo());
		tarefa.setDataCriacao(new Date());
		tarefa.setDescricao(novaTarefa.getDescricao());		
		tarefa.setDataConclusao(novaTarefa.getDataConclusao());
		tarefa.setConcluido(false);
		
		tarefa = tarefaRepository.save(tarefa);
		atividade.get().getTarefas().add(tarefa);
		atividadeRepository.save(atividade.get());
		
		return new TarefaDTO(tarefa);
		
	}
	
	@Transactional
	public TarefaDTO putTarefa(Long idAtividade, Long idTarefa, PutTarefaDTO novosDados){
		
		Optional <Tarefa> tarefa = tarefaRepository.findById(idTarefa);		
		Optional<AtividadePbl> atividade = atividadeRepository.findById(idAtividade);		
		
		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, idAtividade.toString());
		}		
		
		if(tarefa.isEmpty()) {
			throw new ResourceNotFoundException(Constants.TAREFA_NAO_ENCONTRADA, idTarefa.toString());
		}
		
		if(tarefa.get().isConcluido()) {
			throw new TaskRestrictionException("Tarefas concluídas não podem receber alterações.");
		}
		
		tarefa.get().setDescricao(novosDados.getDescricao());
		tarefa.get().setDataConclusao(novosDados.getDataConclusao());
		tarefa.get().setConcluido(novosDados.isConcluido());
		return new TarefaDTO(tarefaRepository.save(tarefa.get()));
	}
	
	@Transactional
	public void deleteTarefa(Long idAtividade, Long idTarefa) {
		Optional<AtividadePbl> atividade = atividadeRepository.findById(idAtividade);
		Optional <Tarefa> tarefa = tarefaRepository.findById(idTarefa);
		
		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, idAtividade.toString());
		}
		
		if(tarefa.isEmpty()) {
			throw new ResourceNotFoundException(Constants.TAREFA_NAO_ENCONTRADA, idTarefa.toString());
		}
		
		if(tarefa.get().isConcluido()) {
			throw new TaskRestrictionException("Tarefas concluídas não podem ser excluídas.");
		}
		
		tarefaRepository.deleteById(idTarefa); 
	}	
    
	@Transactional
	public TarefaDTO addAlunoIntoTarefa(Long idAtividade, Long idTarefa, Long idAluno) {
		Optional<AtividadePbl> atividade = atividadeRepository.findById(idAtividade);
		Optional<Tarefa> tarefa = tarefaRepository.findById(idTarefa);
		Optional<Aluno> aluno = alunoRepository.findById(idAluno);
		

		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, idAtividade.toString());
		}
		
		if(tarefa.isEmpty()) {
			throw new ResourceNotFoundException(Constants.TAREFA_NAO_ENCONTRADA, idTarefa.toString());
		}
		
		if(aluno.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ALUNO_NAO_ENCONTRADO, idAluno.toString());
		}
		
		if(tarefa.get().isConcluido()) {
			throw new TaskRestrictionException("Tarefas concluídas não podem receber novos alunos.");
		}
		
		if(verificaAluno(aluno.get(), tarefa.get())) {
			throw new ResourceAlreadyExistsException(Constants.ALUNO_JA_ATRIBUIDO);
		}
		
		tarefa.get().getAlunos().add(aluno.get());
		tarefaRepository.save(tarefa.get());
		return new TarefaDTO(tarefa.get());
	}
	
	private boolean verificaAluno(Aluno aluno, Tarefa tarefa) {
		if(tarefa.getAlunos().contains(aluno)) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public TarefaDTO removeAlunoFromTarefa(Long idAtividade, Long idTarefa, Long idAluno) {
		Optional<AtividadePbl> atividade = atividadeRepository.findById(idAtividade);
		Optional<Tarefa> tarefa = tarefaRepository.findById(idTarefa);
		Optional<Aluno> aluno = alunoRepository.findById(idAluno);
		
		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, idAtividade.toString());
		}
		
		if(tarefa.isEmpty()) {
			throw new ResourceNotFoundException(Constants.TAREFA_NAO_ENCONTRADA, idTarefa.toString());
		}
		
		if(aluno.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ALUNO_NAO_ENCONTRADO, idAluno.toString());
		}
		
		if(tarefa.get().getAlunos().isEmpty()) {
			throw new TaskRestrictionException(Constants.TAREFA_NAO_POSSUI_ALUNOS);
		}
		
		tarefa.get().getAlunos().remove(aluno.get());
		tarefaRepository.save(tarefa.get());
		return new TarefaDTO(tarefa.get());
	}
}
