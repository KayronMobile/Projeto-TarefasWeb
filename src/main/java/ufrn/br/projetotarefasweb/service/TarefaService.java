package ufrn.br.projetotarefasweb.service;


import java.util.List;

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

    public List<Tarefa> listarTodos(){
        return repository.findAll();
    }

}
