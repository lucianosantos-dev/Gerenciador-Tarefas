package com.lucianodev.gerenciador_de_tarefas.controllers;

import com.lucianodev.gerenciador_de_tarefas.entities.Tarefa;
import com.lucianodev.gerenciador_de_tarefas.services.TarefaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaController {

    private TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @PostMapping
    public ResponseEntity<Tarefa> criarTarefa(@RequestBody Tarefa tarefa) {
        Tarefa tarefaSalva = tarefaService.salvarTarefa(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(tarefaSalva);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tarefa> buscarTarefaPorId(@PathVariable Long id) {
        Tarefa tarefa = tarefaService.buscarTarefaPorId(id);
        return ResponseEntity.ok().body(tarefa);
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> buscarTodasTarefas() {
        List<Tarefa> listarTarefas = tarefaService.buscarTodasTarefas();
        return ResponseEntity.ok(listarTarefas);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@RequestBody Tarefa novaTarefa, @PathVariable Long id) {
        Tarefa tarefa = tarefaService.atualizarTarefa(novaTarefa, id);
        return ResponseEntity.ok(tarefa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus>  deletarTarefaPorId(@PathVariable Long id){
        tarefaService.deletarTarefaPorId(id);
        return ResponseEntity.noContent().build();
    }
}
