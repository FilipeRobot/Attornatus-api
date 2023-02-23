package com.github.FilipeRobot.gerenciarPessoas.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.FilipeRobot.gerenciarPessoas.models.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {
}
