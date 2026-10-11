
# SERVIX API

API REST desenvolvida em Java com Spring Boot para gerenciamento de serviços.

## Objetivo

O projeto demonstra conceitos de desenvolvimento Back-End e Design Patterns,
com foco em organização, manutenção e expansão do código.

## Tecnologias

- Java 21
- Spring Boot
- Maven
- Spring Web
- Bean Validation
- Design Pattern Strategy
- API REST

## Funcionalidades

- Cadastrar serviços
- Listar serviços
- Consultar serviço por ID
- Atualizar serviços
- Excluir serviços
- Gerar descrição de serviços usando Strategy
- Validar dados recebidos

## Executar localmente

É necessário ter Java 21 e Maven instalados.

Na pasta do projeto, execute:

```bash
mvn spring-boot:run
```

A API ficará disponível em:

http://localhost:8080/api/servicos

## Endpoints

| Método | Endpoint | Função |
|---|---|---|
| GET | /api/servicos | Listar serviços |
| GET | /api/servicos/{id} | Consultar serviço |
| POST | /api/servicos | Cadastrar serviço |
| PUT | /api/servicos/{id} | Atualizar serviço |
| DELETE | /api/servicos/{id} | Excluir serviço |
| GET | /api/servicos/{id}/descricao | Descrição via Strategy |

## Exemplo de cadastro

Envie uma requisição POST para `/api/servicos` com:

```json
{
  "nome": "Desenvolvimento de site",
  "descricao": "Criação de site institucional",
  "categoria": "tecnologia",
  "preco": 500.0
}
```

## Design Pattern

O padrão Strategy permite selecionar comportamentos de acordo com a
categoria do serviço, facilitando adicionar novas estratégias.

## Persistência

Esta versão utiliza armazenamento em memória. Os registros são perdidos
quando a aplicação é reiniciada. Uma próxima evolução pode adicionar
Spring Data JPA e um banco de dados.

## Autor

Projeto de estudo e portfólio — SERVIX API.
