# Attornatus API

Uma API em java para gerenciar pessoas.

Feito em resposta ao desafio proposto na Avaliação de Desenvolvedor Back-End da Attornatus.

## Proposta do desafio

Usando Spring boot, crie uma API para gerenciar Pessoas a API deve permitir:

- Criar uma pessoa
  - Nome
  - Data de nascimento
  - Endereço
    - Logradouro
    - CEP
    - Número
    - Cidade
- Editar uma pessoa
- Consultar uma pessoa
- Listar pessoas
- Criar endereço para pessoa
- Listar Endereços da pessoa
- Poder informar qual é o endereço principal da pessoa

## Requisisões aceitas

#### GET

- ```text
  /pessoa/lista : Retorna uma lista de todas as pessoas cadastradas com seus endereços 
  ```

- ```text
  /pessoa/{id} : Retorna os dados de uma pessoa cadastradas com seus endereços
  ```

- ```text
  /pessoa/{id}/endereco : Retorna uma lista de todos os enderecos da pessoa
  ```

- ```text
  /pessoa/{id}/endereco/{enderecoId} : Retorna os dados do endereco da pessoa
  ```

#### POST

- ```text
  /pessoa/ : Cadastra uma pessoa e retorna no "headers" da requisição uma URL para buscar a pessoa cadastrada
  ```

- ```text
  /pessoa/{id}/endereco/ : Cadastra um endereco para uma pessoa e retorna no "headers" da requisição uma URL para buscar o endereco cadastrado
  ```

- ```text
  /pessoa/{id}/endereco/principal/{enderecoId} : Marca o endereco como o endereco principal da pessoa
  ```

#### PUT

- ```text
  /pessoa/editar/{id} : Atualiza os dados de uma pessoa
  ```

- ```text
  /pessoa/{id}/endereco/atualizar/{enderecoId} : Atualiza os dados do endereço da pessoa
  ```

## Sobre mim e o processo de construção da API

Apesar de ja ter tido algum contado com o java, eu não tinha nenhuma experiencia com Spring Boot e suas tecnologias adjacentes,
então o início foi um desafio para mim, mas após alguma pesquisa e força de vontade, eu comecei melhorar gradualmente,
percebo que ainda tenho um longo caminho de aprendizado a frente, mas espero que esse projeto inicial seja a minha base
e possa ser bem avalido.
