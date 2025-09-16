package com.lucianodev.gerenciador_de_tarefas.services;


import com.lucianodev.gerenciador_de_tarefas.entities.Tarefa;

import java.util.List;

public interface TarefaService {

    Tarefa salvarTarefa(Tarefa tarefa);
    Tarefa buscarTarefaPorId(Long id);
    List<Tarefa> buscarTodasTarefas();
    Tarefa atualizarTarefa(Tarefa novaTarefa,Long id);


}
