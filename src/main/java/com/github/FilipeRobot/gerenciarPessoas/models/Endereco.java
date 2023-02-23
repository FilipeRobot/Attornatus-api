package com.github.FilipeRobot.gerenciarPessoas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Uma classe que representa uma entidade/tablea "endereco" dentro do banco de dados
 */
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

    public Endereco() {
    }

    public Endereco(Long id, String logradouro, String cep, Integer numero, String cidade, boolean enderecoPrincipal,
                    Pessoa pessoa) {
        super();
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.enderecoPrincipal = enderecoPrincipal;
        this.pessoa = pessoa;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public boolean isEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    public void setEnderecoPrincipal(boolean enderecoPrincipal) {
        this.enderecoPrincipal = enderecoPrincipal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endereco other = (Endereco) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Endereco [id=" + id + ", logradouro=" + logradouro + ", cep=" + cep + ", numero=" + numero + ", cidade="
                + cidade + ", enderecoPrincipal=" + enderecoPrincipal + ", pessoa=" + pessoa + "]";
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
