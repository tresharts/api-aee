# DTOs de Resposta (Response)

Este pacote contém os **DTOs (Data Transfer Objects)** de Resposta.

## 1. O que é um DTO de Resposta?

É a "caixa" que usamos para **ENVIAR** dados para fora da nossa API. É o contrato em JSON que o usuário (front-end) receberá quando fizer uma consulta (`GET`).

## 2. Por que usar `ResponseDTO`? (A Decisão de Arquitetura)

Usamos `ResponseDTO` (e não a `@Entity` direto) por três razões principais:

1.  **Segurança (O Motivo Nº 1):** A entidade `Professor` tem um campo `senha`. Se retornássemos a entidade, exporíamos a senha criptografada de todos os usuários. O `ResponseDTO` é nosso "filtro" que **garante que campos sensíveis nunca saiam** da nossa API.
2.  **Desacoplamento (O "Contrato"):** O `ResponseDTO` é o "contrato" com o front-end. Se um dia precisarmos renomear uma coluna no banco de dados (na `@Entity`), podemos manter o nome no DTO. O front-end nem perceberá a mudança.
3.  **Eficiência (Achatamento de Dados):** Nossas entidades são objetos complexos (`Aluno` tem um objeto `Professor` dentro). Para o front-end, muitas vezes é mais fácil receber os dados "achatados" (ex: `private Long professorId;` em vez do objeto `Professor` inteiro).

## 3. O Padrão: "ID de Relacionamento" vs. "ID do Objeto"

Ao contrário do `RequestDTO`, o `ResponseDTO` **sempre** contém o `id` do objeto principal (ex: `AlunoResponseDTO` tem `private Long id;`).

* **Por quê?** O front-end precisa saber o `id` do objeto que ele está vendo para poder tomar ações futuras, como editar (`PUT /api/alunos/42`) ou deletar (`DELETE /api/alunos/42`).

## 4. Nossos DTOs de Resposta (O que enviamos)

* **`ProfessorResponseDTO.java`**: Formata a resposta (`id`, `nome`, `email`) ao buscar um `Professor`, **omitindo a senha** por segurança.
* **`ResponsavelResponseDTO.java`**: Formata a resposta (`id`, `nome`, `email`, `telefone`) ao buscar um `Responsavel`, **omitindo a senha**.
* **`AlunoResponseDTO.java`**: Formata a "ficha" completa do `Aluno` (`id`, `nome`, `turma`, `professorId`, `responsavelId`) para ser exibida.
* **`RelatorioResponseDTO.java`**: Formata a resposta ao buscar um `Relatorio`, incluindo todos os seus dados e IDs de vínculo.
* **`AtividadeResponseDTO.java`**: Formata a resposta ao buscar uma `Atividade`, incluindo seu `status` e IDs de vínculo.
* **`PDIResponseDTO.java`**: Formata a resposta ao buscar um `PDI`, mostrando seus `objetivos` e o `alunoId`.