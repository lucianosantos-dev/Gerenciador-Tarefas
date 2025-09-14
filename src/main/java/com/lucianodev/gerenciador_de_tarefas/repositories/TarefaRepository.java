package com.lucianodev.gerenciador_de_tarefas.repositories;

import com.lucianodev.gerenciador_de_tarefas.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
