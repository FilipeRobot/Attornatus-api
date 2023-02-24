package com.github.FilipeRobot.gerenciarPessoas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * Uma classe que representa uma entidade/tablea "endereco" dentro do banco de dados
 */
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String logradouro;

    @Column(nullable = false, length = 8)
    private String cep;

    @Column(nullable = false)
    private Integer numero;

    @Column(nullable = false)
    private String cidade;

    private boolean enderecoPrincipal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
}
