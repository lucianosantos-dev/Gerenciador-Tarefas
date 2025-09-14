package com.lucianodev.gerenciador_de_tarefas.services;

import com.lucianodev.gerenciador_de_tarefas.entities.Tarefa;
import com.lucianodev.gerenciador_de_tarefas.repositories.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TarefaServiceImpl implements TarefaService {

    @Autowired
    private TarefaRepository repository;



    @Override
    public Tarefa salvarTarefa(Tarefa tarefa) {

        if (tarefa.getDataVencimento() != null && tarefa.getDataVencimento().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Erro! Data de vencimento não pode ser no passado.");
        }
        if (tarefa.getDescricao() == null || tarefa.getDescricao().isBlank()) {
            throw new IllegalArgumentException("Erro! Descrição é obrigatória.");
        }

        tarefa.setDataCriacao(LocalDate.now());
        tarefa.setConcluida(false);
        return repository.save(tarefa);
    }
}
