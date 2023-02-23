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

    /**
     * Buscar um endereço no banco de dados
     *
     * @param id Identificação do endereço
     * @return Os dados do endereço encontrado
     * ou uma exceção dizendo que não foi possivel
     * contrar o endereço
     */
    public Endereco findAddressById(Long id) {
        Optional<Endereco> endereco = this.enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new RuntimeException(
                "Endereco informado não foi encontrado! id: " + id + ", Tipo: " + Endereco.class.getName()
        ));
    }

    /**
     * Busca uma lista de endereços associados a uma pessoa
     *
     * @param id Identificação da pessoa
     * @return Uma lista com os dados dos endereços encontrados,
     * ou uma exceção caso a identificação da pessoa seja invalida
     */
    public List<Endereco> findAddressOfPeople(UUID id) {
        return this.pessoaService.findPeopleById(id).getEndereco();
    }

    /**
     * Cadastrar um endereço no banco de dados e associar ele a
     * pessoa que o esta cadastrando
     *
     * @param endereco Dados do endereço que será cadastrado
     * @param id       Identificação da pessoa que estra cadastrando o endreço
     * @return Dados do endereço cadastrado, ou uma exceção caso a
     * Identificação da pessoa seja inválida
     */
    @Transactional
    public Endereco saveAddress(Endereco endereco, UUID id) {
        Pessoa pessoa = this.pessoaService.findPeopleById(id);
        endereco.setPessoa(pessoa);
        return this.enderecoRepository.save(endereco);
    }

    /**
     * Atualizar os dados de um endereço
     *
     * @param pessoaId   Identificação da pessoa que está atualizando o endereço
     * @param enderecoId Identificação do endereço que sejá atualizado
     * @param endereco   Novos dados do endereço
     */
    @Transactional
    public void updateAddress(UUID pessoaId, Long enderecoId, Endereco endereco) {
        Pessoa pessoa = this.pessoaService.findPeopleById(pessoaId);
        Endereco newEndereco = findAddressById(enderecoId);
        endereco.setId(newEndereco.getId());
        endereco.setPessoa(pessoa);

        this.enderecoRepository.save(endereco);
    }

    /**
     * Atualizar o endereço principal de uma pessoa
     *
     * @param pessoaId   Identificação da pessoa que está atualizando o endereço
     * @param enderecoId Identificação do endereço que será o endereço principal
     */
    @Transactional
    public void updateMainAddress(UUID pessoaId, Long enderecoId) {
        Pessoa pessoa = this.pessoaService.findPeopleById(pessoaId);

        List<Endereco> enderecoList = this.findAddressOfPeople(pessoa.getId());

        enderecoList.forEach(oldEndereco -> {
            if (oldEndereco.isEnderecoPrincipal()) {
                oldEndereco.setEnderecoPrincipal(false);
                this.updateAddress(pessoa.getId(), oldEndereco.getId(), oldEndereco);
            }
        });

        Endereco newEndereco = findAddressById(enderecoId);
        newEndereco.setEnderecoPrincipal(true);
        this.updateAddress(pessoa.getId(), newEndereco.getId(), newEndereco);
    }

    /**
     * Apagar um endereço do banco de dados
     *
     * @param id Identificação do endereço
     */
    @Transactional
    public void deleteAddressById(Long id) {
        findAddressById(id);
        this.enderecoRepository.deleteById(id);
    }

}
