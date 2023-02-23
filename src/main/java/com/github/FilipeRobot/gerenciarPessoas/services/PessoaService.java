package com.github.FilipeRobot.gerenciarPessoas.services;

import com.github.FilipeRobot.gerenciarPessoas.models.Pessoa;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.FilipeRobot.gerenciarPessoas.repositories.PessoaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> buscarTodasAsPessoas() {
        return this.pessoaRepository.findAll();
    }

    public Pessoa buscarPessoaPorId(UUID id) {
        Optional<Pessoa> pessoa = this.pessoaRepository.findById(id);
        return pessoa.orElseThrow(() -> new RuntimeException(
                "Pessoa informada não encontrada! id: " + id + ", Tipo: " + Pessoa.class.getName()
        ));
    }

    @Transactional
    public Pessoa registrarPessoa(Pessoa pessoa) {
        pessoa.setId(null);
        pessoa = this.pessoaRepository.save(pessoa);

        return pessoa;
    }

    @Transactional
    public void atualizarPessoa(UUID id, Pessoa pessoa) {
        Pessoa newPessoa = buscarPessoaPorId(id);
        pessoa.setId(newPessoa.getId());
        this.pessoaRepository.save(pessoa);
    }

}
