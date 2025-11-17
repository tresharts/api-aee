# DTOs de Requisição (Request)

Este pacote contém os **DTOs (Data Transfer Objects)** de Requisição.

## 1. O que é um DTO de Requisição?

É a "caixa" que usamos para **RECEBER** dados de fora. É o contrato em JSON que esperamos que um usuário nos envie quando ele quer **criar** (`POST`) ou **atualizar** (`PUT`) algo.

## 2. Por que usar `RequestDTO`? (A Decisão de Arquitetura)

Usamos `RequestDTO` (e não a `@Entity` direto) por três razões principais:

1.  **Validação (O "Guarda da Portaria"):** Esta é a classe onde (no Módulo 4) colocaremos as anotações de validação (`@NotBlank`, `@Email`, `@Size`). Se o usuário enviar um `cpf` inválido, rejeitaremos a requisição aqui.
2.  **Segurança:** O DTO nos permite controlar o que o usuário pode nos enviar. Por exemplo, nosso `AlunoRequestDTO` **não tem** o campo `id`, pois o usuário não pode definir o ID de um novo aluno (isso é trabalho do banco).
3.  **Simplicidade (O "Contrato Limpo"):** Para criar um `Aluno` e vinculá-lo a um `Professor`, o usuário só precisa nos enviar o `professorId`. O `RequestDTO` é perfeito para receber esses IDs de forma simples.

## 3. O Padrão: "ID de Relacionamento" vs. "ID do Objeto"

Esta é a lógica mais importante dos `RequestDTOs`:

* **`id` (do Próprio Objeto):** **NÃO ESTÁ AQUI.** (Ex: `AlunoRequestDTO` não tem `private Long id;`). O motivo é que, ao criar um novo aluno, o `id` dele ainda não existe (será gerado pelo banco).
* **`...Id` (de Relacionamentos):** **ESTÁ AQUI.** (Ex: `AlunoRequestDTO` tem `private Long professorId;`). O motivo é que, ao criar um `Aluno` *novo*, o `Professor` *já existe*. O usuário precisa nos dizer os IDs deles para podermos fazer a vinculação.

## 4. Nossos DTOs de Requisição (O que recebemos)

* **`ProfessorRequestDTO.java`**: Recebe os dados (`nome`, `email`, `cpf`, `senha`) para criar um novo `Professor`.
* **`ResponsavelRequestDTO.java`**: Recebe os dados (`nome`, `email`, `cpf`, `senha`, `telefone`) para criar um novo `Responsavel`.
* **`AlunoRequestDTO.java`**: Recebe os dados (`nome`, `email`, `cpf`, `turma`...) e os IDs de vínculo (`professorId`, `responsavelId`) para criar um novo `Aluno`.
* **`RelatorioRequestDTO.java`**: Recebe os dados (`tipo`, `conteudo`, `alunoId`) para que um `Professor` possa postar um novo `Relatorio`.
* **`AtividadeRequestDTO.java`**: Recebe os dados (`titulo`, `descricao`, `dataEntrega`, `alunoId`) para que um `Professor` possa criar uma nova `Atividade`.
* **`PDIRequestDTO.java`**: Recebe os dados (`objetivos`, `datas`, `alunoId`) para criar ou atualizar o `PDI` de um aluno.