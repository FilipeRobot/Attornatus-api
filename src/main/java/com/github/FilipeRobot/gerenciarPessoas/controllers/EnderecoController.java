package com.github.FilipeRobot.gerenciarPessoas.controllers;

import java.util.List;
import java.util.UUID;

import com.github.FilipeRobot.gerenciarPessoas.services.EnderecoService;
import com.github.FilipeRobot.gerenciarPessoas.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.github.FilipeRobot.gerenciarPessoas.models.Endereco;

@RestController
@RequestMapping("/{pessoaId}/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/")
    public ResponseEntity<List<Endereco>> ListarEnderecos(@PathVariable UUID pessoaId) {
        return ResponseEntity.ok().body(enderecoService.listarEnderecoDaPessoa(pessoaId));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> criarEndereco(@RequestBody Endereco endereco, @PathVariable UUID pessoaId) {
        Endereco newEndereco = this.enderecoService.registrarEndereco(endereco, pessoaId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/atualizar/{enderecoId}")
    public ResponseEntity<Void> atualizarEndereco(@PathVariable UUID pessoaId, @PathVariable Long enderecoId, @RequestBody Endereco newEndereco) {
        newEndereco.setId(enderecoId);
        this.enderecoService.atualizarEndereco(pessoaId, newEndereco.getId(), newEndereco);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/principal/{enderecoId}")
    public ResponseEntity<Void> atualizarEnderecoPrincipal(@PathVariable UUID pessoaId, @PathVariable Long enderecoId) {
        this.enderecoService.atualizarEnderecoPrincipal(pessoaId, enderecoId);

        return ResponseEntity.ok().build();
    }
}
