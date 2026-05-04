package ufrn.br.projetotarefasweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ufrn.br.projetotarefasweb.domain.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
}
