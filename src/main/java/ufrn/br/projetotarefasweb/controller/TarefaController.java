package ufrn.br.projetotarefasweb.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ufrn.br.projetotarefasweb.domain.Tarefa;
import ufrn.br.projetotarefasweb.service.TarefaService;

@Controller
public class TarefaController {

    TarefaService service;

    public TarefaController(TarefaService tarefaService){
        this.service = tarefaService;
    }

    //zona dos GetMapping

    @GetMapping(value = {"/dashboard", "/"})
    public String getDashboardPage(Model model){

        model.addAttribute("tarefas", service.listarTodos());
        return "dashboard";
    }

    @GetMapping("/cadastro")
    public String getCadastroPage(Model model){
        model.addAttribute("tarefa", new Tarefa());
        return "cadastro";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Tarefa tarefa = service.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        model.addAttribute("tarefa", tarefa);
        return "editar";
    }
    @GetMapping("/deletar/{id}")
    public String doProcessarDelete(@PathVariable("id") Long id){
        service.deletar(id);
        return "redirect:/dashboard";
    }
    //Zona dos PostMapping
    @PostMapping("/salvar")
    public String doProcessSalvar(@ModelAttribute Tarefa tarefa){
        service.salvar(tarefa);
        return "redirect:/dashboard";
    }
    @PostMapping("/editar/{id}")
        public String atualizarTarefa(@PathVariable Long id, Tarefa tarefa) {
        service.atualizar(id, tarefa);
        return "redirect:/dashboard";
    }
    
    //zona dos PutMapping

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(
        @PathVariable Long id,
        @RequestBody Tarefa tarefaAtualizada) {

        Optional<Tarefa> tarefa = service.atualizar(id, tarefaAtualizada);

    return tarefa.map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }  
}
