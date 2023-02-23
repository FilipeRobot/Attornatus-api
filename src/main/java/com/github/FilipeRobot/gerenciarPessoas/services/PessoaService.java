package com.github.FilipeRobot.gerenciarPessoas.services;

import com.github.FilipeRobot.gerenciarPessoas.models.Pessoa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.FilipeRobot.gerenciarPessoas.repositories.PessoaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Uma classe que representa um serviço que trada todos os dodos das pessoas cadastradas no banco
 */
@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * buscar todas as pessoas registrada no banco
     *
     * @return Uma lista de pessoas, com os endereços
     */
    public List<Pessoa> findAllPeople() {
        return this.pessoaRepository.findAll();
    }

    /**
     * Busca por uma unica pessoa pelo ID dela
     *
     * @param id Identificação da pessoa no banco de dados
     * @return Uma pessoa com os endereços registrados, ou uma
     * exceção informando que a pessoa não foi enconrada
     */
    public Pessoa findPeopleById(UUID id) {
        Optional<Pessoa> pessoa = this.pessoaRepository.findById(id);
        return pessoa.orElseThrow(() -> new RuntimeException(
                "Pessoa informada não encontrada! id: " + id + ", Tipo: " + Pessoa.class.getName()
        ));
    }

    /**
     * Cadastrar uma pessoa no banco de dados
     *
     * @param pessoa Dados da pessoa que vai ser registrada
     * @return os dados da pessoa registrada, com o ID gerado pelo banco de dados
     */
    @Transactional
    public Pessoa savePeople(Pessoa pessoa) {
        pessoa.setId(null);
        pessoa = this.pessoaRepository.save(pessoa);

        return pessoa;
    }

    /**
     * Atualizar os dados de uma pessoa
     *
     * @param id        Identificação da pessoa que será atualizada
     * @param newPeople Novos dados da pessoa
     */
    @Transactional
    public void updatePeople(UUID id, Pessoa newPeople) {
        Pessoa oldPeople = findPeopleById(id);
        newPeople.setId(oldPeople.getId());
        this.pessoaRepository.save(newPeople);
    }

}
