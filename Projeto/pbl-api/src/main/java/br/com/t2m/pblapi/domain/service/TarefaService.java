package br.com.t2m.pblapi.domain.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.config.Constants;
import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.model.Tarefa;
import br.com.t2m.pblapi.domain.repository.IAtividadeRepository;
import br.com.t2m.pblapi.domain.repository.ITarefaRepository;
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
	
	@Transactional
	public TarefaDTO postTarefa(Long atividadeId, PostTarefaDTO novaTarefa){		
		
		Calendar c = Calendar.getInstance();
		Optional<Atividade> atividade = atividadeRepository.findById(atividadeId);		
		
		if(atividade.isEmpty()) {
			throw new ResourceNotFoundException(Constants.ATIVIDADE_NAO_ENCONTRADA, atividadeId.toString());
		}
		
		if(atividade.get().getDataConclusao().compareTo(novaTarefa.getDataConclusao()) < 0) {		
			throw new RuntimeException("Data de conclusão da tarefa não pode ser superior a data de conclusão da atividade.");
		}		
		
		
		Tarefa tarefa = new Tarefa();		
		tarefa.setDataCriacao(c.toInstant());
		tarefa.setDescricao(novaTarefa.getDescricao());		
		tarefa.setDataConclusao(novaTarefa.getDataConclusao());
		tarefa.setConcluido(false);
		
		tarefa = tarefaRepository.save(tarefa);
		atividade.get().getTarefas().add(tarefa);
		atividadeRepository.save(atividade.get());
		
		return new TarefaDTO(tarefa);
		
	}
	
	@Transactional
	public TarefaDTO putTarefa(PutTarefaDTO novosDados) {
		Optional <Tarefa> tarefa = tarefaRepository.findById(novosDados.getId());
		
		if(tarefa.isEmpty()) {
			throw new ResourceNotFoundException(Constants.TAREFA_NAO_ENCONTRADA, novosDados.getId().toString());
		}		
		
		tarefa.get().setDescricao(novosDados.getDescricao());
		tarefa.get().setDataConclusao(novosDados.getDataConclusao());
		tarefa.get().setConcluido(novosDados.isConcluido());		
		return new TarefaDTO(tarefaRepository.save(tarefa.get()));
	}
	
	@Transactional
	public void deleteTarefa(Long idTarefa) {
		tarefaRepository.deleteById(idTarefa); //verificar existencia da tarefa
	}
	
	
    
}
