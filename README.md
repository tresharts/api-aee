# 📘 API Sistema AEE (Atendimento Educacional Especializado)

Bem-vindo à documentação oficial da API do Sistema AEE. Este projeto fornece o back-end robusto para a gestão de alunos, relatórios e planos pedagógicos, focado em professores de educação inclusiva.

## 🚀 Tecnologias Utilizadas

* **Java 21** e **Spring Boot 3**
* **Banco de Dados:** PostgreSQL 15
* **DevOps:** Docker & Docker Compose
* **Segurança:** Spring Security + JWT
* **Versionamento de Banco:** Flyway Migrations
* **Documentação:** OpenAPI (Swagger UI)

---

## 🛠️ Como Rodar o Projeto

Você não precisa ter Java ou PostgreSQL instalados na sua máquina. O projeto é totalmente conteinerizado.

### Pré-requisitos
* **Docker** e **Docker Compose** instalados.

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/tresharts/api-aee.git](https://github.com/tresharts/api-aee.git)
    cd api-aee
    ```

2.  **Configure as Variáveis de Ambiente:**
    Crie um arquivo chamado `.env` na raiz do projeto e defina as senhas (ou use o modelo abaixo):
    ```env
    DB_PASSWORD=senha123
    JWT_SECRET=segredo123
    ```

3.  **Suba a aplicação:**
    Execute o comando abaixo para construir o `.jar`, criar as imagens e subir os containers:
    ```bash
    docker-compose up -d --build
    ```

4.  **Pronto!**
    A API estará rodando em: `http://localhost:8080`

---

## 📖 Documentação da API (Swagger)

Para visualizar todos os endpoints, payloads e testar as requisições, acesse a documentação interativa:

👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

---

## 🔐 Guia de Autenticação

A API é protegida. Para consumir os dados, você precisa de um Token JWT.

### 1. Criar o Primeiro Usuário (Admin/Professor)
Como o banco inicia vazio, o endpoint de criação de professores é público para o primeiro cadastro.

* **POST** `/api/professores`
    ```json
    {
      "nome": "Administrador",
      "email": "admin@escola.com",
      "cpf": "000.000.000-00",
      "senha": "123"
    }
    ```

### 2. Fazer Login (Obter Token)
* **POST** `/auth/login`
    ```json
    {
      "email": "admin@escola.com",
      "senha": "123"
    }
    ```
* **Resposta:**
    ```json
    {
      "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
    }
    ```

### 3. Acessar Rotas Protegidas
Em todas as outras requisições, adicione o token no **Header**:

* **Authorization:** `Bearer <seu_token_aqui>`

*(No Swagger, basta clicar no botão verde "Authorize" e colar o token).*

---

## 📡 Endpoints Principais

A API segue o padrão RESTful.

| Recurso | Rota Base | Descrição |
| :--- | :--- | :--- |
| **Auth** | `/auth` | Login e autenticação |
| **Professores** | `/api/professores` | Gestão de professores |
| **Alunos** | `/api/alunos` | Gestão de alunos (vinculados a prof/resp) |
| **Responsáveis** | `/api/responsaveis` | Gestão de pais/responsáveis |
| **Relatórios** | `/api/relatorios` | Criação de relatórios diários/bimestrais |
| **Atividades** | `/api/atividades` | Gestão de atividades pedagógicas |
| **PDI** | `/api/pdi` | Plano de Desenvolvimento Individual |

> **Nota:** Listagens (`GET`) utilizam paginação. Ex: `?page=0&size=10&sort=nome,asc`.

---

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas bem definida para garantir escalabilidade e manutenção:

* **Controller:** Camada de entrada (REST), lida apenas com DTOs.
* **Service:** Camada de regras de negócio, validações e transações.
* **Repository:** Camada de acesso a dados (Spring Data JPA).
* **Mapper:** Camada de conversão isolada (Entity <-> DTO).
* **Security:** Filtros e configurações de segurança (Stateless).

---

## 🤝 Contribuição

Desenvolvido por Arthur Bruno P. Gomes.