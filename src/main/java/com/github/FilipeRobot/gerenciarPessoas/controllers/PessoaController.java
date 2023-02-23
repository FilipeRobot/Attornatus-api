package com.github.FilipeRobot.gerenciarPessoas.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.FilipeRobot.gerenciarPessoas.services.EnderecoService;
import com.github.FilipeRobot.gerenciarPessoas.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.FilipeRobot.gerenciarPessoas.models.Pessoa;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping("/todas")
    public ResponseEntity<List<Pessoa>> buscarPessoas() {
        return ResponseEntity.ok().body(this.pessoaService.buscarTodasAsPessoas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable UUID id) {
        Pessoa pessoa = this.pessoaService.buscarPessoaPorId(id);
        return ResponseEntity.ok().body(pessoa);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> criarPessoaComEndereco(@RequestBody Pessoa pessoa) {
        if (pessoa.getEndereco() == null){
            pessoa.setEndereco(new ArrayList<>());
        }

        Pessoa newPessoa = this.pessoaService.registrarPessoa(pessoa);

        if (pessoa.getEndereco().size() > 0 || pessoa.getEndereco() != null) {
            pessoa.getEndereco().forEach(endereco -> {
                this.enderecoService.registrarEndereco(endereco, newPessoa.getId());
            });
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Void> editarPessoa(@RequestBody Pessoa pessoa, @PathVariable UUID id) {
        pessoa.setId(id);
        Pessoa oldPessoa = this.pessoaService.buscarPessoaPorId(pessoa.getId());

        if (pessoa.getEndereco().size() == 0 || pessoa.getEndereco() == null) {
            oldPessoa.getEndereco().forEach(endereco -> {
                this.enderecoService.removerEndereco(endereco.getId());
            });
        } else {
            pessoa.getEndereco().forEach(endereco -> {
                this.enderecoService.registrarEndereco(endereco, oldPessoa.getId());
            });
        }
        this.pessoaService.atualizarPessoa(pessoa.getId(), pessoa);
        return ResponseEntity.noContent().build();
    }
}
