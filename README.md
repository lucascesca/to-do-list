# To-Do List App

Aplicativo de lista de tarefas desenvolvido para ser utilizado via terminal.

Este projeto, com exceção do método [`clearScreen()`](https://stackoverflow.com/questions/2979383/java-clear-the-console), foi completamente desenvolvido por mim, para aprimorar e aplicar alguns dos conhecimentos ensinados no curso de [Java COMPLETO 2023](https://www.udemy.com/course/java-curso-completo/).

## Funcionalidades

- Adicionar tarefas
- Visualizar todas as tarefas
- Filtrar tarefas por status (concluídas/em andamento/não concluídas)
- Remover tarefas
- Alterar status das tarefas


## Conceitos Utilizados

### Paradigma de programação orientado a objetos: Composição e Encapsulamento:
- **Composição:** utilizado para a criação de objetos `Task` dentro de `TaskManager`.
- **Encapsulamento:** implementado em métodos e atributos das classes para evitar a alteração externa destes.
### Estrutura de dados: Lista:
- Essa estrutura de dados foi usada para armazenar cada tarefa adicionada pelo usuário. Ela é implementada na classe `TaskManager`.
### Paradigma funcional: função lambda e interface funcional:
- Utilização da interface funcional Predicate implementada por meio de função lambda, passada como argumento da função `filter()` da Stream API. Dessa forma, foi possível criar filtros de maneira concisa e legível para retornar as tarefas que atendem à determinada condição. 
### Enums
- Enums são empregados para representar os valores constantes de Status.
### Tratamento de exceções
- As exceções `InputMismatchException`, `DateTimeException`, `NullPointerException` e `IndexOutOfBoundsException` são tratadas para evitar que o programa pare, caso ocorra algum erro de entrada feita pelo usuário.
### Programação defensiva
- Abordagem que busca a construção de código robusto, objetivando mitigar e antecipar possíveis problemas.
### Generics
- Implementados na lista de produtos para garantir o type safety, o reuso e a melhora de performance do programa.
### Manipulação de Datas
- A manipulação de datas foi feita por meio de calculos para ordenar as tarefas pela data e evitar que duas tarefas tenham o mesmo horário e data.
