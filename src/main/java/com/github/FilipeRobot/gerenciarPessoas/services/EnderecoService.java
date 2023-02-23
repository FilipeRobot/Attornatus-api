package com.github.FilipeRobot.gerenciarPessoas.services;

import com.github.FilipeRobot.gerenciarPessoas.models.Endereco;
import com.github.FilipeRobot.gerenciarPessoas.models.Pessoa;
import com.github.FilipeRobot.gerenciarPessoas.repositories.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaService pessoaService;

    public Endereco buscarEndereco(Long id){
        Optional<Endereco> endereco = this.enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new RuntimeException(
                "Endereco informado não foi encontrado! id: " + id + ", Tipo: " + Endereco.class.getName()
        ));
    }

    public List<Endereco> listarEnderecoDaPessoa(UUID id){
        return this.pessoaService.buscarPessoaPorId(id).getEndereco();
    }

    @Transactional
    public Endereco registrarEndereco(Endereco endereco, UUID id){
        Pessoa pessoa = this.pessoaService.buscarPessoaPorId(id);
        endereco.setPessoa(pessoa);
        return this.enderecoRepository.save(endereco);
    }

    @Transactional
    public void atualizarEndereco(UUID pessoaId, Long enderecoId, Endereco endereco){
        Pessoa pessoa = this.pessoaService.buscarPessoaPorId(pessoaId);
        Endereco newEndereco = buscarEndereco(enderecoId);
        endereco.setId(newEndereco.getId());
        endereco.setPessoa(pessoa);

        this.enderecoRepository.save(endereco);
    }

    @Transactional
    public void atualizarEnderecoPrincipal(UUID pessoaId, Long enderecoId){
        Pessoa pessoa = this.pessoaService.buscarPessoaPorId(pessoaId);

        List<Endereco> enderecoList = this.listarEnderecoDaPessoa(pessoa.getId());

        enderecoList.forEach(oldEndereco -> {
            if (oldEndereco.isEnderecoPrincipal()){
                oldEndereco.setEnderecoPrincipal(false);
                this.atualizarEndereco(pessoa.getId(), oldEndereco.getId(), oldEndereco);
            }
        });

        Endereco newEndereco = buscarEndereco(enderecoId);
        newEndereco.setEnderecoPrincipal(true);
        this.atualizarEndereco(pessoa.getId(), newEndereco.getId(), newEndereco);
    }

    public void removerEndereco(Long id){
        buscarEndereco(id);
        this.enderecoRepository.deleteById(id);
    }

}
