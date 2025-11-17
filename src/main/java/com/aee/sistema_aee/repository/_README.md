# Repositórios

Este pacote contém as interfaces de **Repositório**.

## 1. O que é um Repositório?

Um Repositório é a camada de **Acesso a Dados (Data Access Layer - DAL)**. É a única parte do nosso código que tem permissão para "falar" diretamente com o banco de dados.

## 2. A Mágica do `JpaRepository`

Nós não vamos escrever *nenhuma* implementação de código aqui. Nós apenas definimos uma `interface` e estendemos a interface `JpaRepository` do Spring Data.

**Exemplo:**
`public interface AlunoRepository extends JpaRepository<Aluno, Long>`

**O que isso faz?**
Ao fazer isso, o Spring Data JPA automaticamente cria uma classe em tempo de execução que nos dá, de graça, todos os métodos de banco de dados mais comuns para a entidade `Aluno`.

## 3. Anotação-Chave
* **`@Repository`**: Usamos esta anotação na interface para dizer ao Spring: "Ei, esta é uma interface de repositório. Por favor, crie uma implementação para mim e me permita injetá-la (`@Autowired`) em meus `Services`."

## 4. Métodos Customizados (Query Methods)

A melhor parte é que, se precisarmos de uma busca customizada, nós só precisamos *declarar o nome do método*. O Spring escreve o SQL para nós.

**Exemplo:**
Se eu adicionar o método `Optional<Pessoa> findByEmail(String email);` na `PessoaRepository`, o Spring entende e automaticamente cria a query: `SELECT * FROM pessoa WHERE email = ?`.

## 5. Por que usamos `Optional<>` em alguns métodos? (IMPORTANTE!)

**A Decisão Arquitetural (Por que `Optional` e não `null`?):**

Nós usamos `Optional` em métodos que buscam um **único item** (como `findById` ou `findByEmail`) porque esses métodos são **incertos**: eles podem encontrar o item, ou podem não encontrar.

Ao fazer o repositório retornar `Optional<Pessoa>`, nós estabelecemos um **contrato explícito e seguro**. Nós estamos dizendo ao `Service`: "Estou te entregando uma 'caixa'. Ela pode conter uma `Pessoa` ou pode estar `vazia`. É sua responsabilidade verificar."

Isso **força** a camada de `Service` a tratar ativamente o caso de "não encontrado" (usando `.orElseThrow()`), prevenindo o erro mais comum do Java (`NullPointerException`).

## 6. Nossos Repositórios (A Camada de Acesso)

* **`PessoaRepository.java`**: Busca na tabela `pessoa`. Contém métodos customizados (`findByEmail`, `findByCpf`) essenciais para **Login** e **Validação de Duplicidade**.
* **`ProfessorRepository.java`**: Gerencia o CRUD da entidade `Professor`. Herda todos os métodos (save, findById, etc.) do JpaRepository.
* **`AlunoRepository.java`**: Gerencia o CRUD da entidade `Aluno`. Herda todos os métodos padrão.
* **`ResponsavelRepository.java`**: Gerencia o CRUD da entidade `Responsavel`. Herda todos os métodos padrão.
* **`RelatorioRepository.java`**: Gerencia o CRUD da entidade `Relatorio`. Herda todos os métodos padrão.
* **`AtividadeRepository.java`**: Gerencia o CRUD da entidade `Atividade`. Herda todos os métodos padrão.
* **`PdiRepository.java`**: Gerencia o CRUD da entidade `PDI`. Contém um método customizado (`findByAlunoId`) para buscar o plano usando o ID do aluno.