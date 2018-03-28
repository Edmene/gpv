CREATE TABLE motoristas (
  id  SERIAL NOT NULL PRIMARY KEY,
  nome VARCHAR(128),
  sobrenome VARCHAR(128),
  rg VARCHAR(20),
  cnh VARCHAR(20)
);