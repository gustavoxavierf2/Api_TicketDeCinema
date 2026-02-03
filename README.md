# 🎬 Cinema Ticket API

> API RESTful para gerenciamento de tickets de cinema desenvolvida com **Spring Boot 3**

## 📋 Sobre o Projeto

Este projeto é uma **API REST completa** para gerenciamento de ingressos de cinema. Ela é responsável por:

- 🎫 Gerenciar operações **CRUD** de tickets de cinema
- 🔐 Autenticar usuários com **JWT** (JSON Web Token)
- 📖 Documentar endpoints automaticamente com **Swagger/OpenAPI**
- ⚠️ Tratar exceções globalmente com **Problem Details** (RFC 7807)

### Arquitetura

O projeto segue uma arquitetura em camadas bem definida, separando responsabilidades entre API, Domain e Web:

```
┌─────────────────────────────────────────────────────────────┐
│                        CLIENT                               │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                    SECURITY FILTER (JWT)                    │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                      CONTROLLER LAYER                       │
│              (TicketController, AuthController)             │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                       SERVICE LAYER                         │
│               (TicketService, UsuarioService)               │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                      REPOSITORY LAYER                       │
│             (TicketRepository, UsuarioRepo)                 │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                         MySQL DB                            │
└─────────────────────────────────────────────────────────────┘
```

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| Java | 17 | Linguagem de programação |
| Spring Boot | 3.3.1 | Framework principal |
| Spring Data JPA | - | Persistência de dados |
| Spring Security | - | Autenticação e autorização |
| Spring Validation | - | Validação de dados |
| Auth0 Java JWT | 4.4.0 | Geração e validação de tokens |
| MySQL | 8.x | Banco de dados relacional |
| Flyway | - | Controle de versão do banco |
| Springdoc OpenAPI | 2.0.4 | Documentação da API |
| Lombok | - | Redução de boilerplate |
| JUnit 5 | - | Framework de testes |
| Mockito | - | Mocking para testes |
| Docker | - | Containerização |

## 📁 Estrutura do Projeto

```
src/main/java/com/soluevo/entrevista/cinema_ticket/
├── CinemaTicketApplication.java        # Classe principal
├── api/
│   ├── request/
│   │   └── TicketRequest.java          # DTO de entrada
│   └── response/
│       └── TicketResponse.java         # DTO de saída
├── domain/
│   ├── handler/
│   │   ├── GlobalExceptionHandler.java # Handler global de exceções
│   │   ├── controllerException/        # Exceções do controller
│   │   └── serviceException/           # Exceções do service
│   ├── model/
│   │   └── Ticket.java                 # Entidade JPA
│   ├── repository/
│   │   └── TicketRepository.java       # Repositório Spring Data
│   ├── security/
│   │   ├── auth/                       # Autenticação (Controller, Service, Filter)
│   │   └── token/
│   │       └── TokenKeyService.java    # Serviço de geração de tokens JWT
│   └── service/
│       └── TicketService.java          # Lógica de negócio
└── web/
    ├── controller/
    │   └── TicketController.java       # Controller REST
    └── Mapper/
        └── TicketMapper.java           # Mapeamento Entity <-> DTO
```

## ⚙️ Configuração

### Pré-requisitos

- Java 17+
- Maven 3.8+
- MySQL 8.x rodando localmente (porta 3306)
- Docker (opcional)

### Variáveis de Ambiente

Configure o arquivo `application.properties`:

```properties
# Servidor
server.port=8080

# Banco de Dados
spring.datasource.url=jdbc:mysql://localhost:3306/cinema_tickets
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
api.security.token.secret=sua-chave-secreta
```

> ⚠️ **Importante**: Nunca commite credenciais reais no repositório. Use variáveis de ambiente em produção.

## 🚀 Como Executar

### 1. Clone o repositório
```bash
git clone https://github.com/seu-usuario/cinema-ticket-api.git
cd cinema-ticket-api
```

### 2. Inicie o MySQL
```bash
# Usando Docker
docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=cinema_tickets mysql:8
```

### 3. Execute o projeto
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

O serviço estará disponível na porta **8080**.

### 🐳 Executando com Docker

```bash
# Build do projeto
.\mvnw.cmd clean package -DskipTests

# Build da imagem
docker build -t cinema-ticket-api .

# Execute o container
docker run -p 8080:8080 cinema-ticket-api
```

## 📡 Endpoints da API

### 🎫 Tickets

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/soluevo/cinema/ticket` | Lista todos os tickets |
| `GET` | `/soluevo/cinema/ticket/{id}` | Busca ticket por ID |
| `POST` | `/soluevo/cinema/ticket` | Cria um novo ticket |
| `PUT` | `/soluevo/cinema/ticket/{id}` | Atualiza um ticket |
| `DELETE` | `/soluevo/cinema/ticket/{id}` | Remove um ticket |

### 🔐 Autenticação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/auth/login` | Realiza login e retorna token JWT |

## 📨 Formato das Requisições

### Criar/Atualizar Ticket

```json
{
    "personName": "João Silva",
    "cinemaName": "Cinemark",
    "movieName": "Avatar 3",
    "hour": "19:30",
    "sessionDate": "15/03/2026",
    "roomNumber": 5,
    "price": 32.50,
    "ticketType": "inteira",
    "seat": "F12"
}
```

### Login

```json
{
    "email": "usuario@email.com",
    "password": "senha123"
}
```

## 🧪 Testes

Execute os testes com:

```bash
# Windows
.\mvnw.cmd test

# Linux/Mac
./mvnw test
```

Os testes cobrem:
- **Controller Tests:** Validação dos endpoints REST
- **Service Tests:** Lógica de negócio

## 🔗 Links Úteis

- 📖 **Swagger UI**: http://localhost:8080/swagger-ui/index.html
- 🗄️ **API Docs**: http://localhost:8080/v3/api-docs

## 📚 Conceitos Aplicados

1. **API RESTful** com Spring Boot
2. **Autenticação JWT** com Spring Security
3. **Persistência de dados** com Spring Data JPA
4. **Migrations de banco** com Flyway
5. **Documentação automática** com OpenAPI/Swagger
6. **Tratamento de exceções** com Problem Details
7. **Testes unitários** com JUnit e Mockito
8. **Containerização** com Docker

## 📄 Licença

Este projeto está licenciado sob a **Licença MIT**.
