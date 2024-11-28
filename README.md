# Projeto Final - Usuário

## Descrição

Este projeto é uma aplicação **Spring Boot** que gerencia usuários. Ele permite realizar operações de CRUD (Criar, Ler, Atualizar e Deletar) para informações de usuários em um banco de dados **MongoDB**. A aplicação também inclui funcionalidades de login e verificação de e-mail.

---

## Tecnologias Utilizadas

- **Java**: Linguagem de programação principal.
- **Spring Boot**: Framework para criação de aplicações Java.
- **Maven**: Ferramenta de automação de compilação e gerenciamento de dependências.
- **MongoDB**: Banco de dados NoSQL utilizado para armazenar os dados dos usuários.
- **MapStruct**: Framework para mapeamento de objetos.

---

## Estrutura do Projeto

- **`src/main/java/com/example/usuario/projetofinal`**: Contém o código-fonte da aplicação.
  - **`api`**: Controladores e conversores de API.
  - **`business`**: Lógica de negócios.
  - **`infrastructure`**: Entidades e repositórios.
- **`src/main/resources`**: Arquivos de configuração.
  - **`application.properties`**: Configurações da aplicação, incluindo a conexão com o MongoDB.


## Configuração

### 1. Clone o Repositório

```
git clone https://github.com/Jpaulo47/projeto_final_backend.git
```

cd usuario.projetofinal

## Configuração do MongoDB

Atualize a URI do MongoDB no arquivo `src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb+srv://<username>:<password>@<cluster-url>/Projetofinal?retryWrites=true&w=majority&appName=Projetofinal
```

## 2. Compilar e Executar a Aplicação
Para compilar e rodar a aplicação, utilize os seguintes comandos:

```
sh
mvn clean install
mvn spring-boot:run
```

## Endpoints
A aplicação expõe os seguintes endpoints para interação com os dados:

- Usuários
GET /usuarios
Retorna todos os usuários.

- GET /usuarios/{email}
Retorna os dados de um usuário específico, identificado pelo e-mail.

- POST /usuarios
Cria um novo usuário. O corpo da requisição deve conter os dados do usuário a ser criado.

- PUT /usuarios
Atualiza os dados de um usuário existente. O corpo da requisição deve conter os dados atualizados do usuário.

- DELETE /usuarios/{email}
Deleta um usuário pelo e-mail.
