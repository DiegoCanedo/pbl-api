package br.com.t2m.pblapi.domain.service;

import java.util.Calendar;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.t2m.pblapi.domain.model.Atividade;
import br.com.t2m.pblapi.domain.model.Tarefa;
import br.com.t2m.pblapi.domain.repository.IAtividadeRepository;
import br.com.t2m.pblapi.domain.repository.ITarefaRepository;
import br.com.t2m.pblapi.domain.service.dto.PostTarefaDTO;
import br.com.t2m.pblapi.domain.service.dto.PutTarefaDTO;
import br.com.t2m.pblapi.domain.service.dto.TarefaDTO;

@Service
public class TarefaService {
	
	@Autowired
	private ITarefaRepository tarefaRepository;
	
	@Autowired
	private IAtividadeRepository atividadeRepository;
	
	@Transactional
	public TarefaDTO postTarefa(Long atividadeId, PostTarefaDTO novaTarefa){
		
		Calendar c = Calendar.getInstance(); //Verificar existencia atividade
		Optional<Atividade> atividade = atividadeRepository.findById(atividadeId);
		Tarefa tarefa = new Tarefa();
		
		tarefa.setDataCriacao(c.toInstant());
		tarefa.setDescricao(novaTarefa.getDescricao());		
		tarefa.setDataConclusao(novaTarefa.getDataConclusao()); //exception aqui!!! Aluno não pode setar data tarefa maior que atividade!
		tarefa.setConcluido(false);
		
		tarefa = tarefaRepository.save(tarefa);
		atividade.get().addTarefa(tarefa);
		atividadeRepository.save(atividade.get());
		
		return new TarefaDTO(tarefa);		
		
	}
	
	@Transactional
	public TarefaDTO putTarefa(PutTarefaDTO novosDados) {
		Optional <Tarefa> tarefa = tarefaRepository.findById(novosDados.getId());
		tarefa.get().setDescricao(novosDados.getDescricao());
		tarefa.get().setDataConclusao(novosDados.getDataConclusao());// Exception data tarefa não pode ser maior que data conclusao trabalho
		tarefa.get().setConcluido(novosDados.isConcluido());		
		return new TarefaDTO(tarefaRepository.save(tarefa.get()));
	}
	
	@Transactional
	public void deleteTarefa(Long idTarefa) {
		tarefaRepository.deleteById(idTarefa); //verificar existencia da tarefa
	}
    
}
