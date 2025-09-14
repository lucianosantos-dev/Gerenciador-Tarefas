package com.lucianodev.gerenciador_de_tarefas.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "tb_tarefa")
public class Tarefa {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Boolean concluida;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Tarefa tarefa = (Tarefa) o;
        return id.equals(tarefa.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
