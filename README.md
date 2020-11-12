### Banco de dados

**Agenda de contato + Banco de dados:** 
- Projeto de agenda de contato (id, nome, telefone, email);
- Banco de dados utilizado: PostgreSQL;
- Na aplicação é possível: 
-       adicionar e remover contatos;
-       listar contatos ordenados por nome ou id;
-       pesquisar contato por nome ou por id;

#### Passo a passo para a instalação do PostgreSQL (https://www.postgresql.org/download/linux/ubuntu/):

*# Create the file repository configuration:*
> sudo sh -c 'echo "deb http://apt.postgresql.org/pub/repos/apt $(lsb_release -cs)-pgdg main" > /etc/apt/sources.list.d/pgdg.list'

*# Import the repository signing key:*
> wget --quiet -O - https://www.postgresql.org/media/keys/ACCC4CF8.asc | sudo apt-key add -

*# Update the package lists:*
> sudo apt-get update

*# Install the latest version of PostgreSQL.*

*#If you want a specific version, use 'postgresql-12' or similar instead of 'postgresql':*
> sudo apt-get -y install postgresql

Download opcional do pgamin4:
> sudo apt install pgadmin4


#### Ok! O banco de dados já está instalado, agora faremos a configuração do mesmo:

Acessando o postgresql:
> sudo su - postgres

Acessando novo usuário privilegiado:
> psql -d postgres -U postgres

#### O comando abaixo irá alterar a senha do bd, é importante usar exatamente o comando abaixo:
> alter user postgres with password 'Tema@10';

Ok! Para sair
> \q

Criar banco de dados para o Tema 10:
> createdb db_tema10;

Selecionar o BD:
> psql db_tema10;

Criar uma Tabela contacts para o Tema 10:
```
create table contacts (
id serial PRIMARY KEY,
name varchar(50) NOT NULL,
phone varchar(20) NOT NULL,
email varchar(50) NOT NULL
);
``` 

#### Pronto, agora o nosso banco está criado e configurado!


**Referências de estudo e desenvolvimento:**

- Vídeo aula - Tema 10: Banco de dados;
- https://www.w3schools.com/sql/default.asp
- https://startdb.info/2019/12/postgresql-1-instalando-no-linux-ubuntu-18-e-derivados.html
- https://www.postgresql.org/download/linux/ubuntu/
- https://mvnrepository.com/artifact/org.postgresql/postgresql/9.3-1104-jdbc41
- http://www.bosontreinamentos.com.br/postgresql-banco-dados/como-criar-tabelas-com-create-table-no-postgresql/
- https://www.tutorialspoint.com/postgresql/postgresql_java.htm
- http://zetcode.com/java/postgresql/
- https://medium.com/@racc.costa/optional-no-java-8-e-no-java-9-7c52c4b797f1
- https://www.tutorialspoint.com/what-is-the-difference-between-execute-executequery-and-executeupdate-methods-in-jdbc
- https://www.postgresqltutorial.com/postgresql-rename-column/
