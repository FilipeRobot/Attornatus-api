package com.github.FilipeRobot.gerenciarPessoas.models;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Uma classe que representa uma entidade/tablea "pessoa" dentro do banco de dados
 */
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

    public Pessoa() {
    }

    public Pessoa(UUID id, String nome, LocalDate dataDeNascimento) {
        super();
        this.id = id;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pessoa [id=" + id + ", nome=" + nome + ", dataDeNascimento=" + dataDeNascimento + ", endereco="
                + endereco + "]";
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }
}
