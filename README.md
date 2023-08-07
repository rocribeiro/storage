# Projeto Storage

O Projeto Storage é um aplicativo de gerenciamento de produtos e fornecedores construído com Spring Boot, MySQL e Docker Compose.

## Pré-requisitos

Certifique-se de ter as seguintes ferramentas instaladas em seu sistema:

- Java Development Kit (JDK) 11
- Maven
- Docker
- Docker Compose

## Iniciando o Projeto

1. Clone este repositório para o seu ambiente local:

```bash
git clone https://github.com/seu-usuario/storage.git
```

## 1 - Navegue até o diretório do projeto: 
```bash
cd storage
```
3 - (Opcional, mas recomendado) Execute o Maven para compilar e empacotar a aplicação:
```bash
mvn clean package
```

2 - Execute o Docker Compose para iniciar os serviços do aplicativo e do banco de dados: 
```bash
docker-compose up
```

    O aplicativo Spring Boot estará disponível em http://localhost:8080. Você pode acessar a API e usar as funcionalidades do projeto.

Configurações

    O serviço da aplicação Spring Boot está configurado para rodar na porta 8080.
    O serviço MySQL está configurado para rodar na porta 3306.
    O arquivo docker-compose.yml contém as configurações do Docker Compose para executar ambos os serviços.

Endpoints da API

A API oferece os seguintes endpoints para gerenciamento de produtos:

    - GET /products: Lista todos os produtos.
    - GET /products/{id}: Retorna um produto específico pelo ID.
    - POST /products: Cria um novo produto.
    - PUT /products/{id}: Atualiza um produto existente.
    - DELETE /products/{id}: Exclui um produto.
    - POST /products/{id}/increase: Aumenta a quantidade de estoque de um produto.
    - POST /products/{id}/decrease: Diminui a quantidade de estoque de um produto.
