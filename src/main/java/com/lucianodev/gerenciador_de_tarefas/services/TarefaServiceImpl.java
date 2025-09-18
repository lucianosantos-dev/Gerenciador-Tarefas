package com.lucianodev.gerenciador_de_tarefas.services;

import com.lucianodev.gerenciador_de_tarefas.entities.Tarefa;
import com.lucianodev.gerenciador_de_tarefas.repositories.TarefaRepository;
import com.lucianodev.gerenciador_de_tarefas.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Tarefa buscarTarefaPorId(Long id) {
        Optional<Tarefa> optional = repository.findById(id);
        return optional.orElseThrow(() -> new RuntimeException("Erro! Tarefa não encontrada com esse ID: " + id));
    }

    @Override
    public List<Tarefa> buscarTodasTarefas() {
        return repository.findAll();
    }

    @Override
    public Tarefa atualizarTarefa(Tarefa novaTarefa, Long id) {
        Optional<Tarefa> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException("ID não encontrado na lista de tarefas!");
        } else {
            Tarefa tarefaOriginal = optional.get();
            tarefaOriginal.setDescricao(novaTarefa.getDescricao());
            tarefaOriginal.setConcluida(novaTarefa.getConcluida());
            tarefaOriginal.setDataVencimento(novaTarefa.getDataVencimento());
            return repository.save(tarefaOriginal);
        }
    }

    @Override
    public void deletarTarefaPorId(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
}
