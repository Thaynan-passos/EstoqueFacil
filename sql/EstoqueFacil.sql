CREATE SCHEMA IF NOT EXISTS EstoqueFacil DEFAULT CHARACTER SET utf8 ;
USE EstoqueFacil ;

-- -----------------------------------------------------
-- Table EstoqueFacil.`Endereco`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Endereco` (
  `ID_Endereco` INT NOT NULL AUTO_INCREMENT,
  `Bairro` VARCHAR(45) NOT NULL,
  `Rua` VARCHAR(45) NOT NULL,
  `Estado` VARCHAR(2) NOT NULL,
  `CEP` VARCHAR(8) NOT NULL,
  `Cidade` VARCHAR(45) NOT NULL,
  `Numero` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ID_Endereco`),
  UNIQUE INDEX `CEP_UNIQUE` (`CEP` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Funcionario` (
  `ID_Funcionario` INT NOT NULL AUTO_INCREMENT,
  `Cpf` VARCHAR(11) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `Nivel_Acesso` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Senha_Hash` VARCHAR(255) NOT NULL,
  `fk_ID_Endereco` INT NOT NULL,
  `Cargo` ENUM("GERENTE", "FINANCEIRO", "ALMOXARIFE") NOT NULL,
  UNIQUE INDEX `Cpf_UNIQUE` (`Cpf` ASC),
  PRIMARY KEY (`ID_Funcionario`),
  INDEX `fk_ID_Endereco` (`fk_ID_Endereco` ASC),
  CONSTRAINT `fk_Funcionario_Endereco`
    FOREIGN KEY (`fk_ID_Endereco`)
    REFERENCES EstoqueFacil.`Endereco` (`ID_Endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Dependente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Dependente` (
  `ID_Dependente` INT NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Sexo` ENUM("MASCULINO", "FEMININO", "NAO_BINARIO", "OUTRO") NULL,
  `Fk_ID_Funcionario` INT NOT NULL,
  PRIMARY KEY (`ID_Dependente`, `Fk_ID_Funcionario`),
  INDEX `fk_Dependente_Funcionario1_idx` (`Fk_ID_Funcionario` ASC),
  CONSTRAINT `fk_Dependente_Funcionario1`
    FOREIGN KEY (`fk_ID_Funcionario`)
    REFERENCES EstoqueFacil.`Funcionario` (`ID_Funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Fornecedor` (
  `ID_Fornecedor` INT NOT NULL AUTO_INCREMENT,
  `CNPJ` VARCHAR(14) NOT NULL,
  `Razao_Social` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(45) NOT NULL,
  `fk_Endereco_Fornecedor` INT NOT NULL,
  UNIQUE INDEX `Nome_UNIQUE` (`Razao_Social` ASC),
  INDEX `Email_idx` (`Email` ASC),
  INDEX `fk_Endereco_Fornecedor` (`fk_Endereco_Fornecedor` ASC),
  UNIQUE INDEX `CNPJ_UNIQUE` (`CNPJ` ASC),
  PRIMARY KEY (`ID_Fornecedor`),
  CONSTRAINT `fk_Fornecedor_Endereco`
    FOREIGN KEY (`fk_Endereco_Fornecedor`)
    REFERENCES EstoqueFacil.`Endereco` (`ID_Endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Telefone` (
  `ID_Telefone` INT NOT NULL AUTO_INCREMENT,
  `Telefone` VARCHAR(11) NOT NULL,
  `Tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID_Telefone`),
  UNIQUE INDEX `Telefone_UNIQUE` (`Telefone` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Funcionario_Telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Funcionario_Telefone` (
  `fk_ID_Funcionario` INT NOT NULL,
  `fk_ID_Telefone` INT NOT NULL,
  PRIMARY KEY (`fk_ID_Funcionario`, `fk_ID_Telefone`),
  INDEX `fk_Funcionario_Telefone_Telefone_idx` (`fk_ID_Telefone` ASC),
  INDEX `fk_Funcionario_Telefone_idx` (`fk_ID_Funcionario` ASC),
  CONSTRAINT `fk_Funcionario_Telefone_Telefone`
    FOREIGN KEY (`fk_ID_Telefone`)
    REFERENCES EstoqueFacil.`Telefone` (`ID_Telefone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionario_Telefone_Funcionario`
    FOREIGN KEY (`fk_ID_Funcionario`)
    REFERENCES EstoqueFacil.`Funcionario` (`ID_Funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Produto` (
  `ID_Produto` INT NOT NULL AUTO_INCREMENT,
  `Garantia` INT NULL,
  `Data_Cadastro` DATE NOT NULL,
  `Nome` VARCHAR(45) NOT NULL,
  `Codigo_Barras` VARCHAR(20) NOT NULL,
  `Valor_Unitario` DECIMAL(10,2) NOT NULL,
  `Classificacao` ENUM("CONSUMO", "REPARO", "LIMPEZA") NOT NULL,
  PRIMARY KEY (`ID_Produto`),
  UNIQUE INDEX `Nome_UNIQUE` (`Nome` ASC),
  UNIQUE INDEX `Codigo_Barras_UNIQUE` (`Codigo_Barras` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Setor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Setor` (
  `ID_Setor` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Capacidade` INT NOT NULL,
  `Orcamento_Mensal` DECIMAL(10,2) NOT NULL,
  `Tipo` ENUM("SETOR_ALMOXARIFE", "SETOR_FINANCEIRO", "SETOR_GERENCIA") NOT NULL,
  `fk_Endereco_Setor` INT NOT NULL,
  PRIMARY KEY (`ID_Setor`),
  UNIQUE INDEX `fk_Endereco_Setor_UNIQUE` (`fk_Endereco_Setor` ASC),
  CONSTRAINT `fk_Setor_Endereco`
    FOREIGN KEY (`fk_Endereco_Setor`)
    REFERENCES EstoqueFacil.`Endereco` (`ID_Endereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Relatorio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Relatorio` (
  `ID_Relatorio` INT NOT NULL AUTO_INCREMENT,
  `Data_Emissao` DATE NOT NULL,
  `Descricao` VARCHAR(45) NOT NULL,
  `Valor_Total_Entradas` DECIMAL(10,2) NOT NULL,
  `Valor_Total_Saidas` DECIMAL(10,2) NOT NULL,
  `Data_Inicio` DATE NOT NULL,
  `Data_Fim` DATE NOT NULL,
  `fk_ID_Funcionario` INT NOT NULL,
  PRIMARY KEY (`ID_Relatorio`),
  INDEX `fk_ID_Funcionario_idx` (`fk_ID_Funcionario` ASC),
  CONSTRAINT `fk_ID_Funcionario`
    FOREIGN KEY (`fk_ID_Funcionario`)
    REFERENCES EstoqueFacil.`Funcionario` (`ID_Funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Lote` (
  `ID_Lote` INT NOT NULL AUTO_INCREMENT,
  `Numero_Lote` INT NOT NULL,
  `Data_Fabricacao` DATE NOT NULL,
  `Data_Validade` DATE NOT NULL,
  `Quantidade` INT NOT NULL,
  `fk_ID_Produto` INT NOT NULL,
  `fk_ID_Fornecedor` INT NOT NULL,
  `Data_Fornecimento` DATE NOT NULL,
  PRIMARY KEY (`ID_Lote`),
  UNIQUE INDEX `Numero_Lote_UNIQUE` (`Numero_Lote` ASC),
  INDEX `fk_Lote_Produto_idx` (`fk_ID_Produto` ASC),
  INDEX `fk_Lote_Fornecedor_idx` (`fk_ID_Fornecedor` ASC),
  CONSTRAINT `fk_Lote_Produto`
    FOREIGN KEY (`fk_ID_Produto`)
    REFERENCES EstoqueFacil.`Produto` (`ID_Produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lote_Fornecedor`
    FOREIGN KEY (`fk_ID_Fornecedor`)
    REFERENCES EstoqueFacil.`Fornecedor` (`ID_Fornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Movimentacao` (
  `ID_Movimentacao` INT NOT NULL AUTO_INCREMENT,
  `Status` ENUM("PENDENTE", "APROVADO", "NEGADO") NOT NULL,
  `Data_Movimentacao` DATE NOT NULL,
  `Descricao` VARCHAR(45) NULL,
  `fk_ID_Funcionario_Aprovador` INT NOT NULL,
  `fk_ID_Funcionario_Executor` INT NOT NULL,
  PRIMARY KEY (`ID_Movimentacao`),
  INDEX `fk_Movimentacao_Almoxarife_idx` (`fk_ID_Funcionario_Executor` ASC),
  INDEX `fk_Movimentacao_Gerente_idx` (`fk_ID_Funcionario_Aprovador` ASC),
  CONSTRAINT `fk_Movimentacao_Gerente`
    FOREIGN KEY (`fk_ID_Funcionario_Aprovador`)
    REFERENCES EstoqueFacil.`Funcionario` (`ID_Funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movimentacao_Almoxarife`
    FOREIGN KEY (`fk_ID_Funcionario_Executor`)
    REFERENCES EstoqueFacil.`Funcionario` (`ID_Funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Requisicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Requisicao` (
  `ID_Requisicao` INT NOT NULL AUTO_INCREMENT,
  `Data_Requisicao` DATE NOT NULL,
  `Status` ENUM("PENDENTE", "APROVADO", "NEGADO") NOT NULL,
  `Motivo` VARCHAR(45) NOT NULL,
  `fk_ID_Setor` INT NOT NULL,
  PRIMARY KEY (`ID_Requisicao`),
  INDEX `fk_Requisicao_Setor_idx` (`fk_ID_Setor` ASC),
  CONSTRAINT `fk_Requisicao_Setor`
    FOREIGN KEY (`fk_ID_Setor`)
    REFERENCES EstoqueFacil.`Setor` (`ID_Setor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Produto_Movimentacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Produto_Movimentacao` (
  `fk_ID_Lote` INT NOT NULL,
  `fk_ID_Movimentacao` INT NOT NULL,
  `Quantidade` INT NOT NULL,
  PRIMARY KEY (`fk_ID_Lote`, `fk_ID_Movimentacao`),
  INDEX `fk_Produto_Movimentacao_Movimentacao_idx` (`fk_ID_Movimentacao` ASC),
  CONSTRAINT `fk_Lote_Movimentacao_Lote`
    FOREIGN KEY (`fk_ID_Lote`)
    REFERENCES EstoqueFacil.`Lote` (`ID_Lote`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Lote_Movimentacao_Movimentacao`
    FOREIGN KEY (`fk_ID_Movimentacao`)
    REFERENCES EstoqueFacil.`Movimentacao` (`ID_Movimentacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Requisicao_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Requisicao_Produto` (
  `fk_ID_Requisicao` INT NOT NULL,
  `fk_ID_Produto` INT NOT NULL,
  `Quantidade_Solicitada` INT NOT NULL,
  PRIMARY KEY (`fk_ID_Requisicao`, `fk_ID_Produto`),
  INDEX `fk_Requisicao_Produto_Produto_idx` (`fk_ID_Produto` ASC),
  INDEX `fk_Requisicao_Produto_Requisicao_idx` (`fk_ID_Requisicao` ASC),
  CONSTRAINT `fk_Requisicao_Produto_Requisicao`
    FOREIGN KEY (`fk_ID_Requisicao`)
    REFERENCES EstoqueFacil.`Requisicao` (`ID_Requisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicao_Produto_Produto`
    FOREIGN KEY (`fk_ID_Produto`)
    REFERENCES EstoqueFacil.`Produto` (`ID_Produto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Fornecedor_Telefone`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Fornecedor_Telefone` (
  `fk_ID_Fornecedor` INT NOT NULL,
  `fk_ID_Telefone` INT NOT NULL,
  PRIMARY KEY (`fk_ID_Fornecedor`, `fk_ID_Telefone`),
  INDEX `fk_Fornecedor_Telefone_Telefone_idx` (`fk_ID_Telefone` ASC),
  CONSTRAINT `fk_Fornecedor_Telefone_Fornecedor`
    FOREIGN KEY (`fk_ID_Fornecedor`)
    REFERENCES EstoqueFacil.`Fornecedor` (`ID_Fornecedor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Fornecedor_Telefone_Telefone`
    FOREIGN KEY (`fk_ID_Telefone`)
    REFERENCES EstoqueFacil.`Telefone` (`ID_Telefone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table EstoqueFacil.`Funcionario_Setor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS EstoqueFacil.`Funcionario_Setor` (
  `data_inicio` DATE NOT NULL,
  `fk_ID_Funcionario` INT NOT NULL,
  `fk_ID_Setor` INT NOT NULL,
  PRIMARY KEY (`data_inicio`),
  INDEX `Funcionario_Setor_Funcionario_idx` (`fk_ID_Funcionario` ASC),
  INDEX `Funcionario_Setor_Setor_idx` (`fk_ID_Setor` ASC),
  CONSTRAINT `fk_Funcionario_Setor_Funcionario`
    FOREIGN KEY (`fk_ID_Funcionario`)
    REFERENCES EstoqueFacil.`Funcionario` (`ID_Funcionario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Funcionario_Setor_Setor`
    FOREIGN KEY (`fk_ID_Setor`)
    REFERENCES EstoqueFacil.`Setor` (`ID_Setor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;