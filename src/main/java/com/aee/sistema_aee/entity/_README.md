# Entidades

Este pacote contém as classes de **Entidade** (`@Entity`).

## 1. O que é uma Entidade?

Uma Entidade é a classe Java que atua como um "molde" direto para uma tabela no nosso banco de dados. É a camada fundamental do **ORM (Mapeamento Objeto-Relacional)**.

O JPA/Hibernate usará esta classe para duas coisas:
1.  **Na Fase de Desenvolvimento (Módulo 3):** Ler as anotações e criar/atualizar automaticamente o banco de dados (graças ao `ddl-auto: update`).
2.  **Em Todas as Fases:** Ser o "mapa de tradução" para o `Repository` saber como gerar comandos `INSERT`, `SELECT`, `UPDATE`, etc.

## 2. Anotações-Chave (Guia Rápido)

* **`@Entity`**: "Esta classe é uma tabela no banco!"
* **`@Id`**: "Este campo é a Chave Primária (PK)."
* **`@GeneratedValue(strategy = GenerationType.IDENTITY)`**: "Deixe o próprio banco de dados gerar o número do `id`. **É por isso que o `id` NUNCA vai no construtor da classe!**"
* **`@Column`**: "Customiza a coluna." Usamos para regras como `nullable = false` (não pode ser nulo) ou `unique = true` (não pode ter duplicatas, como em `cpf` e `email`).
* **`@Enumerated(EnumType.STRING)`**: "Grave o nome do Enum (ex: 'PENDENTE') no banco, e não o número (0, 1)."
* **`@Lob`**: "Large Object". Usado para campos de texto muito longos (como o `conteudo` do Relatório).

## 3. Mapeamento de Relacionamentos (IMPORTANTE!)

Usamos anotações para ligar as entidades (tabelas) umas às outras.

**Exemplo:** `Relatorio` e `Aluno` (`@ManyToOne` / `@OneToMany`)

* **Em `Relatorio.java` (O Lado "Muitos" e "Dono" da Relação):**
  `@ManyToOne` e `@JoinColumn(name = "aluno_id")`
    * **O que faz:** Diz: "Muitos relatórios são de UM aluno. Por favor, crie a coluna `aluno_id` AQUI na minha tabela (`relatorio`) para guardar a chave estrangeira."

* **Em `Aluno.java` (O Lado "Um" e "Não-Dono" da Relação):**
  `@OneToMany(mappedBy = "aluno")`
    * **O que faz:** Diz: "Um aluno tem muitos relatórios. Eu NÃO sou o dono. Vá procurar o campo `aluno` lá na classe `Relatorio`, é ele quem manda no mapeamento."

## 4. Por que `@Getter`/`@Setter` e não `@Data`? (Decisão de Arquitetura)

Usar `@Data` (do Lombok) em classes `@Entity` com relacionamentos é **perigoso**, pois o método `toString()` automático pode causar um **loop infinito** (`StackOverflowError`) ao tentar imprimir relacionamentos bidirecionais (ex: `Professor` imprime `Aluno`, que imprime `Professor`).

**A Solução:** Nós usamos `@Getter`, `@Setter` e `@NoArgsConstructor` separadamente, pois eles não geram os métodos `toString()` ou `equals()` problemáticos.

## 5. A Estratégia de Herança (Por que `Pessoa`?)

Usamos `@Inheritance(strategy = InheritanceType.JOINED)` na classe-pai `Pessoa`.

**A Decisão Arquitetural:**
Isso cria uma tabela `pessoa` central com os dados comuns (nome, cpf, email) e tabelas-filhas (`professor`, `aluno`, `responsavel`) ligadas a ela.

* **Por quê?**
    1.  **Validação de Login:** Permite que `cpf` e `email` sejam `@Column(unique = true)` em **um único lugar**, garantindo que um `Professor` não possa ter o mesmo CPF que um `Aluno`.
    2.  **Autenticação:** Facilita o login (Módulo 7), pois nosso `Service` de segurança só precisa procurar o usuário em uma tabela (`pessoa`).

## 6. Nossas Entidades (O Modelo de Dados)

* **`Pessoa.java`**: A classe-pai abstrata (`JOINED`) que armazena dados comuns de login (email, cpf, senha) e garante sua unicidade.
* **`Professor.java`**: Herda de `Pessoa`. Representa o usuário que pode logar e criar `Relatorios` e `Atividades`.
* **`Responsavel.java`**: Herda de `Pessoa`. Representa o usuário que pode logar e ler os dados do `Aluno`.
* **`Aluno.java`**: Herda de `Pessoa` (mas não loga). É a entidade central que armazena a `turma` (String) e se conecta a `Professor`, `Responsavel`, `PDI`, `Relatorios` e `Atividades`.
* **`PDI.java`**: O "Plano de Desenvolvimento Individual" (`@OneToOne` com `Aluno`). Armazena os `objetivos` de longo prazo.
* **`Relatorio.java`**: O registro (`DIARIO`/`BIMESTRAL`) que um `Professor` escreve sobre um `Aluno` (`@ManyToOne` com ambos).
* **`Atividade.java`**: A tarefa (`PENDENTE`/`CONCLUIDA`) que um `Professor` cria para um `Aluno` (`@ManyToOne` com ambos).