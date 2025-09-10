DROP TABLE if exists teste_aluno.produto;

CREATE TABLE teste_aluno.produto (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(30) NOT NULL,
  lance_minimo DECIMAL(8, 2) NOT NULL,
  data_cadastro DATE NOT NULL,
  data_venda DATE DEFAULT NULL,
  PRIMARY KEY (id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;


DROP TABLE if exists teste_aluno.aluno;

CREATE TABLE teste_aluno.aluno(
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(30) NOT NULL,
    cpf VARCHAR(30) NOT NULL,
    email VARCHAR(30) NOT NULL,
    PRIMARY KEY(id)
)
ENGINE = INNODB
CHARACTER SET utf8mb4;

-- InnoDB is a general-purpose storage engine that balances high reliability and high
-- performance. In MySQL 8.0, InnoDB is the default MySQL storage engine. Unless you
-- have configured a different default storage engine, issuing a CREATE TABLE statement
-- without an ENGINE clause creates an InnoDB table.


INSERT INTO teste_aluno.PRODUTO(NOME, LANCE_MINIMO, DATA_CADASTRO)
VALUES('TV SAMSUNG 40 POL', 2000, curdate());

INSERT INTO teste_aluno.PRODUTO(NOME, LANCE_MINIMO, DATA_CADASTRO)
VALUES('TV SAMSUNG 55 POL', 2500, curdate());
