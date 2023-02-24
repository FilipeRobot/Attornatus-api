package com.github.FilipeRobot.gerenciarPessoas.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Uma classe que representa uma entidade/tablea "pessoa" dentro do banco de dados
 */
@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataDeNascimento;

    @OneToMany(mappedBy = "pessoa")
    private List<Endereco> endereco;
}
