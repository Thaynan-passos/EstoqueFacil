CREATE SCHEMA IF NOT EXISTS estoquefacil DEFAULT CHARACTER SET utf8;
USE estoquefacil ;

-- -----------------------------------------------------
-- Table estoquefacil.endereco
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.endereco (
  id_endereco INT NOT NULL AUTO_INCREMENT,
  bairro VARCHAR(45) NOT NULL,
  rua VARCHAR(45) NOT NULL,
  estado VARCHAR(2) NOT NULL,
  cep VARCHAR(8) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  numero VARCHAR(20),
  PRIMARY KEY (id_endereco),
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.funcionario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.funcionario (
  id_funcionario INT NOT NULL AUTO_INCREMENT,
  cpf VARCHAR(11) NOT NULL,
  email VARCHAR(45) NOT NULL,
  nome VARCHAR(45) NOT NULL,
  senha_hash VARCHAR(255) NOT NULL,
  fk_id_endereco INT NOT NULL,
  cargo ENUM('GERENTE', 'FINANCEIRO', 'ALMOXARIFADO') NOT NULL,
  UNIQUE INDEX cpf_unique (cpf ASC),
  PRIMARY KEY (id_funcionario),
  INDEX fk_id_endereco (fk_id_endereco ASC),
  CONSTRAINT fk_funcionario_endereco
    FOREIGN KEY (fk_id_endereco)
    REFERENCES estoquefacil.endereco (id_endereco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table estoquefacil.fornecedor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.fornecedor (
  id_fornecedor INT NOT NULL AUTO_INCREMENT,
  cnpj VARCHAR(14) NOT NULL,
  razao_social VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  fk_endereco_fornecedor INT NOT NULL,
  UNIQUE INDEX nome_unique (razao_social ASC),
  UNIQUE INDEX email_unique (email ASC),
  INDEX fk_endereco_fornecedor (fk_endereco_fornecedor ASC),
  UNIQUE INDEX cnpj_unique (cnpj ASC),
  PRIMARY KEY (id_fornecedor),
  CONSTRAINT fk_fornecedor_endereco
    FOREIGN KEY (fk_endereco_fornecedor)
    REFERENCES estoquefacil.endereco (id_endereco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.telefone
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.telefone (
  id_telefone INT NOT NULL AUTO_INCREMENT,
  telefone VARCHAR(11) NOT NULL,
  tipo VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_telefone),
  UNIQUE INDEX telefone_unique (telefone ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.funcionario_telefone
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.funcionario_telefone (
  fk_id_funcionario INT NOT NULL,
  fk_id_telefone INT NOT NULL,
  PRIMARY KEY (fk_id_funcionario, fk_id_telefone),
  INDEX fk_funcionario_telefone_telefone_idx (fk_id_telefone ASC),
  INDEX fk_funcionario_telefone_idx (fk_id_funcionario ASC),
  CONSTRAINT fk_funcionario_telefone_telefone
    FOREIGN KEY (fk_id_telefone)
    REFERENCES estoquefacil.telefone (id_telefone)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_funcionario_telefone_funcionario
    FOREIGN KEY (fk_id_funcionario)
    REFERENCES estoquefacil.funcionario (id_funcionario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.produto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.produto (
  id_produto INT NOT NULL AUTO_INCREMENT,
  garantia INT NULL,
  data_cadastro DATE NOT NULL,
  nome VARCHAR(45) NOT NULL,
  codigo_barras VARCHAR(20) NOT NULL,
  valor_unitario DECIMAL(10,2) NOT NULL,
  classificacao ENUM('CONSUMO', 'REPARO', 'LIMPEZA') NOT NULL,
  PRIMARY KEY (id_produto),
  UNIQUE INDEX nome_unique (nome ASC),
  UNIQUE INDEX codigo_barras_unique (codigo_barras ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.setor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.setor (
  id_setor INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  capacidade INT NOT NULL,
  tipo ENUM('SETOR_ALMOXARIFE', 'SETOR_FINANCEIRO', 'SETOR_GERENCIA') NOT NULL,
  fk_endereco_setor INT NOT NULL,
  PRIMARY KEY (id_setor),
  UNIQUE INDEX fk_endereco_setor_unique (fk_endereco_setor ASC),
  CONSTRAINT fk_setor_endereco
    FOREIGN KEY (fk_endereco_setor)
    REFERENCES estoquefacil.endereco (id_endereco)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.relatorio
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.relatorio (
  id_relatorio INT NOT NULL AUTO_INCREMENT,
  data_emissao DATE NOT NULL,
  descricao VARCHAR(500) NOT NULL,
  valor_total_entradas DECIMAL(10,2) NOT NULL,
  valor_total_saidas DECIMAL(10,2) NOT NULL,
  data_inicio DATE NOT NULL,
  data_fim DATE NOT NULL,
  fk_id_funcionario INT NOT NULL,
  PRIMARY KEY (id_relatorio),
  INDEX fk_id_funcionario_idx (fk_id_funcionario ASC),
  CONSTRAINT fk_id_funcionario
    FOREIGN KEY (fk_id_funcionario)
    REFERENCES estoquefacil.funcionario (id_funcionario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.lote
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.lote (
  id_lote INT NOT NULL AUTO_INCREMENT,
  numero_lote INT NOT NULL,
  data_fabricacao DATE NOT NULL,
  data_validade DATE NOT NULL,
  quantidade INT NOT NULL,
  fk_id_produto INT NOT NULL,
  fk_id_fornecedor INT NOT NULL,
  data_fornecimento DATE NOT NULL,
  PRIMARY KEY (id_lote),
  UNIQUE INDEX numero_lote_unique (numero_lote ASC),
  INDEX fk_lote_produto_idx (fk_id_produto ASC),
  INDEX fk_lote_fornecedor_idx (fk_id_fornecedor ASC),
  CONSTRAINT fk_lote_produto
    FOREIGN KEY (fk_id_produto)
    REFERENCES estoquefacil.produto (id_produto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_lote_fornecedor
    FOREIGN KEY (fk_id_fornecedor)
    REFERENCES estoquefacil.fornecedor (id_fornecedor)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.movimentacao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.movimentacao (
  id_movimentacao INT NOT NULL AUTO_INCREMENT,
  status ENUM('PENDENTE', 'APROVADO', 'NEGADO') NOT NULL,
  data_movimentacao DATE NOT NULL,
  descricao VARCHAR(500) NULL,
  fk_id_funcionario_aprovador INT NOT NULL,
  fk_id_funcionario_executor INT NOT NULL,
  PRIMARY KEY (id_movimentacao),
  INDEX fk_movimentacao_almoxarife_idx (fk_id_funcionario_executor ASC),
  INDEX fk_movimentacao_gerente_idx (fk_id_funcionario_aprovador ASC),
  CONSTRAINT fk_movimentacao_gerente
    FOREIGN KEY (fk_id_funcionario_aprovador)
    REFERENCES estoquefacil.funcionario (id_funcionario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_movimentacao_almoxarife
    FOREIGN KEY (fk_id_funcionario_executor)
    REFERENCES estoquefacil.funcionario (id_funcionario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.requisicao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.requisicao (
  id_requisicao INT NOT NULL AUTO_INCREMENT,
  data_requisicao DATE NOT NULL,
  status ENUM('PENDENTE', 'APROVADO', 'NEGADO') NOT NULL,
  motivo VARCHAR(150) NOT NULL,

  fk_id_setor INT NOT NULL,
  fk_id_funcionario INT NOT NULL,

  PRIMARY KEY (id_requisicao),

  INDEX fk_requisicao_setor_idx (fk_id_setor ASC),
  INDEX fk_requisicao_funcionario_idx (fk_id_funcionario ASC),

  CONSTRAINT fk_requisicao_setor
    FOREIGN KEY (fk_id_setor)
    REFERENCES estoquefacil.setor (id_setor),

  CONSTRAINT fk_requisicao_funcionario
    FOREIGN KEY (fk_id_funcionario)
    REFERENCES estoquefacil.funcionario (id_funcionario)
)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.produto_movimentacao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.produto_movimentacao (
  fk_id_lote INT NOT NULL,
  fk_id_movimentacao INT NOT NULL,
  quantidade INT NOT NULL,
  PRIMARY KEY (fk_id_lote, fk_id_movimentacao),
  INDEX fk_produto_movimentacao_movimentacao_idx (fk_id_movimentacao ASC),
  CONSTRAINT fk_lote_movimentacao_lote
    FOREIGN KEY (fk_id_lote)
    REFERENCES estoquefacil.lote (id_lote)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_lote_movimentacao_movimentacao
    FOREIGN KEY (fk_id_movimentacao)
    REFERENCES estoquefacil.movimentacao (id_movimentacao)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table estoquefacil.requisicao_produto
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.requisicao_produto (
  fk_id_requisicao INT NOT NULL,
  fk_id_produto INT NOT NULL,
  quantidade_solicitada INT NOT NULL,
  PRIMARY KEY (fk_id_requisicao, fk_id_produto),
  INDEX fk_requisicao_produto_produto_idx (fk_id_produto ASC),
  INDEX fk_requisicao_produto_requisicao_idx (fk_id_requisicao ASC),
  CONSTRAINT fk_requisicao_produto_requisicao
    FOREIGN KEY (fk_id_requisicao)
    REFERENCES estoquefacil.requisicao (id_requisicao)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_requisicao_produto_produto
    FOREIGN KEY (fk_id_produto)
    REFERENCES estoquefacil.produto (id_produto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.fornecedor_telefone
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.fornecedor_telefone (
  fk_id_fornecedor INT NOT NULL,
  fk_id_telefone INT NOT NULL,
  PRIMARY KEY (fk_id_fornecedor, fk_id_telefone),
  INDEX fk_fornecedor_telefone_telefone_idx (fk_id_telefone ASC),
  CONSTRAINT fk_fornecedor_telefone_fornecedor
    FOREIGN KEY (fk_id_fornecedor)
    REFERENCES estoquefacil.fornecedor (id_fornecedor)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_fornecedor_telefone_telefone
    FOREIGN KEY (fk_id_telefone)
    REFERENCES estoquefacil.telefone (id_telefone)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table estoquefacil.funcionario_setor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS estoquefacil.funcionario_setor (
  data_inicio DATE NOT NULL,
  fk_id_funcionario INT NOT NULL,
  fk_id_setor INT NOT NULL,
  PRIMARY KEY (fk_id_funcionario, fk_id_setor, data_inicio),
  INDEX funcionario_setor_funcionario_idx (fk_id_funcionario ASC),
  INDEX funcionario_setor_setor_idx (fk_id_setor ASC),
  CONSTRAINT fk_funcionario_setor_funcionario
    FOREIGN KEY (fk_id_funcionario)
    REFERENCES estoquefacil.funcionario (id_funcionario)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_funcionario_setor_setor
    FOREIGN KEY (fk_id_setor)
    REFERENCES estoquefacil.setor (id_setor)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;