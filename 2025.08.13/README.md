# Aula 13.08.2025

Primeiros passos da linguagem Kotlin, execução com gradle. Seguindo os passos do slides da aula `app/src/main/resources/mov-kotlin-introducao.pdf`

- Definição de funções palavra reservada: `fun nomeFuncao(){}`
- Definição de variáveis, palavra reservada:
    - val nome: String (IMUTÁVEL)
    - var idade: Int (MUTÁVEL)
- Leitura de teclado:
  - readln() para ler strings
  - readlnOrNull() para ler strings ou retornar nullo (pode ser usado em conjunto com o operador ?:)
  - readlnOrNull()?.toIntOrNull() para fazer tratativa, mesmo se o usuário digitar uma string ele converter para inteiro (usa o operador ?. de seguro)

