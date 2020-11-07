package br.com.t2m.pblapi.domain.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Aluno;
import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.model.Tarefa;
import br.com.t2m.pblapi.domain.repository.IAlunoRepository;
import br.com.t2m.pblapi.domain.repository.IAtividadeRepository;
import br.com.t2m.pblapi.domain.repository.ITarefaRepository;
import br.com.t2m.pblapi.domain.service.dto.AlunoDTO;
import br.com.t2m.pblapi.domain.service.dto.PostTarefaDTO;
import br.com.t2m.pblapi.domain.service.dto.PutTarefaDTO;
import br.com.t2m.pblapi.domain.service.dto.TarefaDTO;
import br.com.t2m.pblapi.exception.ResourceNotFoundException;

@Service
public class TarefaService {
	
	@Autowired
	private ITarefaRepository tarefaRepository;
	
	@Autowired
	private IAtividadeRepository atividadeRepository;
	
	@Autowired
	private IAlunoRepository alunoRepository;
	
	@Transactional
	public TarefaDTO postTarefa(Long idAtividade, PostTarefaDTO novaTarefa){		
		
		
		Optional<Atividade> atividade = atividadeRepository.findById(idAtividade);		
		
		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, idAtividade.toString());
		}		
		
		if(atividade.get().getDataConclusao().compareTo(novaTarefa.getDataConclusao()) < 0) {		
			throw new RuntimeException("Data de conclusão da tarefa não pode ser superior a data de conclusão da atividade.");
		}			
		
		Tarefa tarefa = new Tarefa();		
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
	public TarefaDTO putTarefa(Long idAtividade, PutTarefaDTO novosDados) {
		
		Optional <Tarefa> tarefa = tarefaRepository.findById(novosDados.getId());		
		Optional<Atividade> atividade = atividadeRepository.findById(idAtividade);		
		
		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, idAtividade.toString());
		}		
		
		if(tarefa.isEmpty()) {
			throw new ResourceNotFoundException(Constants.TAREFA_NAO_ENCONTRADA, novosDados.getId().toString());
		}
		
		if(atividade.get().getDataConclusao().compareTo(novosDados.getDataConclusao()) < 0) {
			throw new RuntimeException("Data de conclusão da tarefa não pode ser superior a data de conclusão da atividade.");
		}
		
		tarefa.get().setDescricao(novosDados.getDescricao());
		tarefa.get().setDataConclusao(novosDados.getDataConclusao()); 
		tarefa.get().setConcluido(novosDados.isConcluido());		
		return new TarefaDTO(tarefaRepository.save(tarefa.get()));
	}
	
	@Transactional
	public void deleteTarefa(Long idAtividade, Long idTarefa) {
		Optional<Atividade> atividade = atividadeRepository.findById(idAtividade);
		Optional <Tarefa> tarefa = tarefaRepository.findById(idTarefa);
		
		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, idAtividade.toString());
		}
		
		if(tarefa.isEmpty()) {
			throw new ResourceNotFoundException(Constants.TAREFA_NAO_ENCONTRADA, idTarefa.toString());
		}
		
		if(tarefa.get().isConcluido()) {
			throw new RuntimeException("Tarefas concluídas não podem ser excluídas.");
		}
		
		tarefaRepository.deleteById(idTarefa); 
	}
	
	
    
	@Transactional
	public TarefaDTO addAlunoIntoTarefa(Long idAtividade, Long idTarefa, AlunoDTO alunoDTO) {
		Optional<Atividade> atividade = atividadeRepository.findById(idAtividade);
		Optional<Tarefa> tarefa = tarefaRepository.findById(idTarefa);
		Optional<Aluno> aluno = alunoRepository.findById(alunoDTO.getId());
		

		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, idAtividade.toString());
		}
		
		if(tarefa.isEmpty()) {
			throw new ResourceNotFoundException(Constants.TAREFA_NAO_ENCONTRADA, idTarefa.toString());
		}
		
		if(aluno.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ALUNO_NAO_ENCONTRADO, alunoDTO.getId().toString());
		}
		
		if(tarefa.get().isConcluido()) {
			throw new RuntimeException("Tarefas concluídas não podem receber novos alunos.");
		}
		
		tarefa.get().getAlunos().add(aluno.get());
		tarefaRepository.save(tarefa.get());
		return new TarefaDTO(tarefa.get());
	}
	
	@Transactional
	public TarefaDTO removeAlunoFromTarefa(Long idAtividade, Long idTarefa, Long idAluno) {
		Optional<Atividade> atividade = atividadeRepository.findById(idAtividade);
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
		
		tarefa.get().getAlunos().remove(aluno.get()); //verificar se o método "remove" esta sendo usado de forma correta
		tarefaRepository.save(tarefa.get());
		return new TarefaDTO(tarefa.get());
	}
	
	
}
