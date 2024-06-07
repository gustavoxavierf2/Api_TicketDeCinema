# Bem-vindo à Minha API de CRUD de Tickets de Cinema!

Esta é uma ferramenta que criei para facilitar o gerenciamento de tickets de cinema. Desenvolvi-a com base nas melhores práticas de API RESTful, visando oferecer uma forma simples e direta de lidar com operações básicas como criar, listar, atualizar e excluir tickets.

Ao utilizar esta API, você poderá gerenciar seus tickets de cinema de forma eficiente, sem complicações desnecessárias.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Lombok
- MySQL
- Validation
- Tratamento de Exceções com Handler
- Problem details
- Springdoc OpenAPI
- Junit
- Mock

## Executando o Projeto

### Pré-requisitos

- JDK (Java Development Kit)
- Maven
- MySQL Server

### Configuração do Banco de Dados

1. Crie um banco de dados MySQL chamado `cinema_tickets`.
2. Edite o arquivo `application.properties` localizado em `src/main/resources` e configure as credenciais do seu banco de dados:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/cinema_tickets
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

### Executando o Projeto

1. Clone este repositório em sua máquina local.
2. Navegue até o diretório raiz do projeto.
3. Execute o seguinte comando para compilar e executar o projeto:

    ```bash
    mvn spring-boot:run
    ```

Isso iniciará a aplicação Spring Boot. Aguarde até ver a mensagem indicando que a aplicação foi iniciada com sucesso.

## Documentação da API

Após a execução do projeto, você pode acessar a documentação da API em:

http://localhost:8080/swagger-ui/index.html


Aqui você encontrará uma visão detalhada de todos os endpoints disponíveis, parâmetros aceitos e exemplos de uso.

Agora você está pronto para começar a explorar e gerenciar seus tickets de cinema de forma eficiente e sem complicações!


## Autor

gustavo xavier farias

## Licença

Este projeto está licenciado sob a Licença MIT.
