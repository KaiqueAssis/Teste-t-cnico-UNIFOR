### Como rodar o projeto back-end

Este projeto é um backend desenvolvido com **Quarkus** que utiliza um banco de dados **PostgreSQL**. Além disso, ele está configurado para aceitar requisições CORS da URL do frontend.

## Pré-requisitos

Antes de rodar o projeto, você precisará configurar alguns itens:

- **Java 17 ou superior**: Para rodar o Quarkus.
- **PostgreSQL**: Banco de dados necessário para o projeto.
- **Maven**: Para compilar o projeto obs( versão utilizada 3.9.9).

## Passo 1: Configurando o Banco de Dados PostgreSQL

1. **Instale e configure o PostgreSQL** na sua máquina ou utilize um banco de dados remoto.
2. Crie um banco de dados e configure as credenciais necessárias (usuário e senha).

## Passo 2: Configurando o `application.properties`

No arquivo `src/main/resources/application.properties`, configure o banco de dados e o CORS da seguinte forma:
```properties
# Configurações do Banco de Dados
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=seu_usuario
quarkus.datasource.password=sua_senha
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/seu_banco
```
# Configuração de CORS (substitua a URL do frontend conforme necessário)
```
quarkus.http.cors=true
quarkus.http.cors.origins=http://localhost:4200
```
## Passo 3 comando para executar o projeto

Use esse comando para rodar o projeto
```
  mvn quarkus:dev
```

### Como rodar o projeto front-end

## Pré-requisitos
Antes de rodar o projeto, você precisará configurar alguns itens:
- **NPM**: Para installar os pacotes nodes.
- **NodeJs**: NodeJs necessaário versão que eu rodei foi 22,14.0.


## Passo 1:  Instalar dependências do projeto

Antes de roda o projeto é necessário instalar as depenências do packeage.json use o comando

```
npm install
```

### Passo 2: Configure a enviroments
Faça a configuração do arquivo enviroments e coloque a porta que você vai rodar o backend
```
export const environment = {
    apiUrl: 'http://localhost:8080/'
  };
```  

### Passo 3 Rodar a aplicação

Para inicar a aplicação user o comando

```
ng serve
```


    