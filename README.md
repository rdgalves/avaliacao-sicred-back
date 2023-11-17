# Projeto de Avaliação Técnica SICRED

## 📌 Introdução

Este é um projeto de avaliação técnica para SICRED, desenvolvido utilizando Spring Boot, Spring Data JPA e o banco de dados PostgreSQL.

## ✅ Objetivo

O projeto tem como objetivo desenvolver um sistema back-end para gerir sessões de votação em uma organização cooperativista, onde cada associado tem direito a um voto. Implementado como uma aplicação na nuvem com uma API REST, o sistema oferecerá funcionalidades para cadastrar pautas, abrir sessões de votação com duração definida, coletar votos dos associados (opções 'Sim' ou 'Não') e, finalmente, contabilizar e apresentar os resultados das votações. O projeto foca na simplicidade e eficiência, garantindo a persistência dos dados e desconsiderando a segurança das interfaces para fins de exercício. É uma solução destinada a facilitar a tomada de decisões democráticas em cooperativas.

## 📋 Pré-requisitos

- **Java:** Versão 17.
- **Maven:** Para construção e gerenciamento de dependências.
- **PostgreSQL:** Necessário para armazenamento de dados.
- **Docker:** Necessário para execução do banco de dados e criação das tabelas (Apenas se o banco não estiver implementado localmente)


## 🔧 Tecnologias utilizadas

- **[Spring Boot](https://spring.io/projects/spring-boot) (Versão 3.1.5):** Framework utilizado para construir a aplicação.
- **[Spring Web](https://spring.io/projects/spring-boot) (Versão 3.1.5, herdada do Spring Boot):** Componente do Spring Boot para desenvolvimento de aplicações web e criação de endpoints RESTful.
- **[Spring Data JPA](https://spring.io/projects/spring-data-jpa) (Versão 3.1.5, herdada do Spring Boot):** Facilita a integração com o banco de dados e operações CRUD.
- **[PostgreSQL](https://www.postgresql.org/) (Driver Versão 42.6.0):** Sistema de gerenciamento de banco de dados relacional.
- **[Lombok](https://projectlombok.org/) (Versão 1.18.30):** Biblioteca que ajuda a reduzir o código boilerplate em Java, fornecendo anotações úteis.

## 🚀 Como rodar a aplicação

1. **Configuração do Banco de Dados:** Certifique-se de ter o PostgreSQL em execução. Configure as credenciais do banco de dados e a URL no arquivo `application.yml`.

   ```properties
    spring:
      datasource:
        url: jdbc:postgresql://db:5432/sicred  (Database configurado na imagem docker)
        username: sicred_user (User configurado na imagem docker)
        password: 123456 (Pass configurado na imagem docker)
   ```

2. **Construindo o Projeto:**

   Navegue até o diretório do projeto via terminal/cmd e execute:
   ```bash
   mvn clean install -DskipTests
   ```

3. **Executando a Aplicação:**

   Com o projeto construído, execute:
   ```bash
   mvn spring-boot:run
   ```

   Ou, você pode rodar a aplicação diretamente pelo seu IDE, executando a classe principal com o método `public static void main`.

4. Após isso, a aplicação estará rodando em `http://localhost:8080`.

## 🧪 Testando a Aplicação

Para executar os testes:
```bash
mvn test
```

---

# Deploy da aplicação Spring Boot e PostgreSQL no Docker

Este guia orienta sobre como dockerizar a aplicação Spring Boot com um banco de dados PostgreSQL e a execução automaticas das tabelas descritas no teste.

## Pré-requisitos

Antes de iniciar, certifique-se de que você possui instalado no seu sistema:

- Docker
- Docker Compose
- Maven 

## Início Rápido

Para colocar tudo para funcionar rapidamente, clone este repositório e navegue até o diretório que contém o arquivo `docker-compose.yml`. Depois execute:

```bash
docker-compose up --build -d



