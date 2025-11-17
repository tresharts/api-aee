# Enums

Este pacote contém as classes de **Enum**.

## 1. O que é um Enum?

Um Enum (Enumeração) é uma classe especial em Java que define um **conjunto fixo de valores constantes**. Por exemplo, `StatusAtividadeEnum` só pode ser `PENDENTE` ou `CONCLUIDA`.

## 2. Por que usar Enums? (A Decisão de Arquitetura)

Poderíamos usar `String` para tudo, mas Enums são uma prática melhor por duas razões:

1.  **Segurança de Tipo (Type Safety):** O compilador Java nos protege. Se tentarmos salvar um status como `"PENDEMTE"` (erro de digitação), o código nem compila. Se usássemos `String`, isso iria para o banco e causaria um bug silencioso.
2.  **Legibilidade:** O código fica mais limpo e claro. `if (status == StatusAtividadeEnum.PENDENTE)` é muito mais seguro e legível do que `if (status.equals("PENDENTE"))`.

## 3. Anotação-Chave: `@Enumerated(EnumType.STRING)`

Quando usamos um Enum dentro de uma `@Entity` (como em `Relatorio.java`), nós **sempre** usamos esta anotação.

**A Decisão Arquitetural:**
* O padrão do JPA é `EnumType.ORDINAL`, que salva o Enum como um **número** (0, 1, 2...). Isso é péssimo para ler o banco e perigoso para refatorar.
* Ao usar `EnumType.STRING`, o JPA salva o **nome** (ex: "PENDENTE") no banco de dados. Isso torna o banco 100% legível e seguro.

## 4. Nossos Enums (Regras de Negócio)

* **`QualUsuario.java`**: Define os tipos de usuários que podem logar no sistema (`ADMIN`, `PROFESSOR`, `RESPONSAVEL`).
* **`StatusAtividadeEnum.java`**: Define os estados de uma `Atividade` (`PENDENTE`, `CONCLUIDA`).
* **`TipoRelatorioEnum.java`**: Define as categorias de `Relatorio` (`DIARIO`, `BIMESTRAL`).