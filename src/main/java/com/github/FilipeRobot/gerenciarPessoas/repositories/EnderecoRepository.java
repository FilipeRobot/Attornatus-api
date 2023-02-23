package com.github.FilipeRobot.gerenciarPessoas.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.FilipeRobot.gerenciarPessoas.models.Endereco;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
