package ufrn.br.projetotarefasweb.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ufrn.br.projetotarefasweb.domain.Tarefa;
import ufrn.br.projetotarefasweb.repository.TarefaRepository;

@Service
public class TarefaService {

    TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public Tarefa salvar(Tarefa tarefa){
        return repository.save(tarefa);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public Optional<Tarefa> atualizar(Long id, Tarefa novaTarefa) {
    return repository.findById(id).map(tarefa -> {
        tarefa.setDescricao(novaTarefa.getDescricao());
        tarefa.setAtivo(novaTarefa.getAtivo());

        return repository.save(tarefa);
    });
    }
    public Optional<Tarefa> buscarPorId(Long id) {
        return repository.findById(id);
    }
    public List<Tarefa> listarTodos(){
        return repository.findAll();
    }

}
