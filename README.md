# Projeto de Avaliação Técnica SICRED

## 📌 Introdução
Este é um projeto de avaliação técnica para SICRED, desenvolvido utilizando Spring Boot, Spring Data JPA e o banco de dados PostgreSQL. O sistema é projetado para gerir sessões de votação em uma organização cooperativista.

## ✅ Objetivo
Desenvolver um sistema back-end para gerir sessões de votação em cooperativas, onde cada associado tem direito a um voto. Principais funcionalidades:
- Cadastro de pautas.
- Abertura de sessões de votação.
- Coleta e contabilização de votos.

## 📋 Pré-requisitos
- Java 17 (Opcional para caso nao queira utilizar o docker)
- Maven (Opcional para caso nao queira utilizar o docker)
- PostgreSQL (Opcional para caso nao queira utilizar o docker)
- Docker (Obrigatório pasa subida completa do ambiente local)

## 🔧 Tecnologias Utilizadas
- Spring Boot 3.1.5: Para construção da aplicação.
- Spring Web: Desenvolvimento web e endpoints RESTful.
- Spring Data JPA: Integração com banco de dados e operações CRUD.
- PostgreSQL: Sistema de gerenciamento de banco de dados.
- Lombok: Redução de código boilerplate.
- Kafka: Sistema de mensageria.

## 🚀 Como Rodar a Aplicação
### Configuração do Banco de Dados
Configure as credenciais no arquivo `application.yml`.

### Construindo o Projeto
Execute no terminal:
```bash
mvn clean install -DskipTests
```


### Executando a Aplicação
Após construir, execute:
```bash
mvn spring-boot:run
```
A aplicação estará disponível em `http://localhost:8080`.

Para consumir os endpoints basta importar o arquivo [sicred-avaliacao.postman_collection.json](postman%2Fsicred-avaliacao.postman_collection.json) no Postman localizado na pasta [postman](postman). 

## 🧪 Testando a Aplicação
Execute os testes com:
```bash
mvn test
```


## 🐳 Deploy com Docker
### Requisitos
- Docker
- Docker Compose

### Execução
No diretório do `docker-compose.yml`, execute:

```bash
docker-compose up --build -d
```

Ao executar o comando estará disponivel todo o ambiente necessário para execução da aplicação.

Caso queira visualizar os tópicos do kafka via UI (Kafka Manager) basta executar o arquivo [init-kafka-manager.sh](init-kafka-manager.sh).

## 🛠️ Estrutura do Projeto
Detalhes do `pom.xml`:
- Dependências para Spring Boot, Spring Web, Data JPA, PostgreSQL, Lombok e Kafka.
- Configurações para compilação com Java 17.

## 📂 Arquivos de Configuração
### Dockerfile
Usa a imagem do OpenJDK 17 e copia o jar do projeto para o container.

### Docker Compose
Define os serviços `app`, `db`, `zookeeper` e `kafka`, configurando o ambiente para a aplicação, PostgreSQL e Kafka.

## 📧 Contato
Rodrigo Silva - rdg.alvess@gmail.com
