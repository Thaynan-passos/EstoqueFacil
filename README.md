# EstoqueFacil

Sistema de Gerenciamento de Estoque desenvolvido como Projeto de Avaliação Profissional (APS). Uma aplicação web robusta para controlar inventário, requisições, fornecedores e movimentação de materiais.

## 📋 Visão Geral

**EstoqueFacil** é uma plataforma completa de gerenciamento de estoque que permite gerenciar:
- **Produtos** - Cadastro e controle de produtos com classificação e lotes
- **Fornecedores** - Gestão de fornecedores com informações de contato
- **Funcionários** - Cadastro de equipe com diferentes níveis de acesso
- **Requisições** - Solicitação e aprovação de materiais
- **Movimentação** - Entrada e saída de materiais com rastreamento
- **Relatórios** - Análises financeiras e de estoque
- **Inventário** - Controle físico e reconciliação de estoque

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 21 | Linguagem de programação principal |
| **Spring Boot** | 4.0.6 | Framework web e injeção de dependência |
| **PostgreSQL** | 17 | Banco de dados relacional |
| **Thymeleaf** | - | Template engine para views |
| **Jakarta Mail** | - | Envio de emails |
| **Maven** | - | Gerenciador de dependências |

---

## 📁 Estrutura do Projeto

```
EstoqueFacil/
├── src/
│   ├── main/
│   │   ├── java/com/EstoqueFacil/EstoqueFacil/
│   │   │   ├── model/              # Entidades JPA (Produto, Funcionario, etc)
│   │   │   ├── controller/         # Controllers Spring MVC
│   │   │   ├── service/            # Lógica de negócio
│   │   │   ├── repository/         # Acesso a dados (JPA)
│   │   │   ├── utils/              # Utilitários e helpers
│   │   │   ├── exceptions/         # Exceções customizadas
│   │   │   └── EstoqueFacilApplication.java
│   │   └── resources/
│   │       ├── application.properties   # Configurações Spring
│   │       ├── static/css/              # Estilos CSS
│   │       └── templates/               # Páginas HTML Thymeleaf
│   │           ├── login.html
│   │           ├── recuperar-senha.html
│   │           ├── telas-almoxarife/    # Views do almoxarife
│   │           ├── telas-funcionario/   # Views do funcionário
│   │           └── telas-gerente/       # Views do gerente
│   └── test/
│       └── java/                    # Testes unitários
├── sql/
│   └── EstoqueFacil.sql            # Script SQL do banco
├── pom.xml                          # Configuração Maven
├── mvnw / mvnw.cmd                 # Maven wrapper
└── run-app.ps1                     # Script para iniciar a aplicação

```

---

## 🗄️ Modelo de Dados

### Principais Entidades:

- **Funcionario** - Usuários do sistema com cargos (GERENTE, FINANCEIRO, ALMOXARIFADO)
- **Fornecedor** - Fornecedores de produtos com CNPJ e contato
- **Produto** - Itens de estoque com classificação e preço
- **Lote** - Grupos de produtos com data de validade
- **Requisicao** - Solicitações de materiais com status
- **Movimentacao** - Entrada e saída de produtos
- **Setor** - Setores da empresa para alocação de funcionários
- **Endereco** - Informações de endereço compartilhadas
- **Relatorio** - Relatórios gerados do sistema

---

## ⚙️ Instalação e Configuração

### Pré-requisitos
 #### Execução com Docker
- Docker Engine ou Docker Desktop com Docker Compose
 #### Execução local
- Java 21+
- MySQL 8.0+
- Maven 3.6+

### 1. Clonar e Configurar

```bash
git clone <url-do-repositorio>
cd EstoqueFacil
```

### 2. Configurar Banco de Dados

### Execução com Docker

O PostgreSQL é iniciado pelo Docker Compose. O Hibernate cria e atualiza as tabelas no primeiro início.

### Execução Local
Execute o script SQL para criar o banco:
```bash
mysql -u root -p < sql/EstoqueFacil.sql
```

### 3. Configurar Variáveis de Ambiente

Copie o modelo para `.env` na raiz do projeto e adicione os valores às variáveis
```bash
cp .env.example .env
```

### 4. Compilar a aplicação (Somente Execução Local)

```bash
./mvnw clean install
```

## 🚀 Executando a Aplicação

### Execução com Docker
```bash
docker compose up --build
```

### Execução Local

#### Via Maven

```bash
./mvnw spring-boot:run
```

#### Via PowerShell (Windows)

```powershell
.\run-app.ps1
```

#### Via JAR

```bash
java -jar target/EstoqueFacil-0.0.1-SNAPSHOT.jar
```

#### **A aplicação estará disponível em: http://localhost:8080**

---

## 🔐 Autenticação e Níveis de Acesso

O sistema possui três níveis de cargo:

| Cargo | Permissões |
|-------|-----------|
| **GERENTE** | Acesso total - Gerencia funcionários, fornecedores, relatórios e análises |
| **FINANCEIRO** | Gerencia entrada de materiais, requisições e relatórios financeiros |
| **ALMOXARIFADO** | Controle de estoque - Entrada/saída de materiais e movimentação |

---

## 📋 Funcionalidades Principais

### Dashboard
- Visão geral do sistema por perfil
- Métricas de estoque e requisições

### Gerenciamento de Produtos
- ✅ Cadastrar produtos com classificação
- ✅ Gerenciar lotes e validade
- ✅ Controlar quantidade em estoque
- ✅ Definir preços e custos

### Requisições
- ✅ Criar solicitações de materiais
- ✅ Aprovar/rejeitar requisições
- ✅ Acompanhar histórico

### Controle de Estoque
- ✅ Entrada de materiais de fornecedores
- ✅ Saída de materiais para setores
- ✅ Movimentação de produtos
- ✅ Inventário físico

### Relatórios
- ✅ Análise financeira
- ✅ Relatório de movimentação
- ✅ Histórico de requisições

### Gerenciamento de Dados
- ✅ Cadastro de funcionários
- ✅ Cadastro de fornecedores
- ✅ Gestão de setores
- ✅ Endereços

---

## 🧪 Validações e Exceções

O projeto implementa validações robustas:

- **CampoPreenchimento** - Validação de campos obrigatórios
- **ErroDePreenchimentoInvalidoException** - Dados em formato inválido
- **TelefoneInvalidoException** - Telefone inválido (11 dígitos)
- **ValorEquivocado** - Valores inconsistentes
- **ObjetoNaoEncontrado** - Recurso não encontrado

---

## 📧 Notificações por Email

O sistema envia notificações automáticas:
- ✉️ Confirmação de requisições
- ✉️ Aviso de estoque baixo
- ✉️ Notificações para fornecedores
- ✉️ Recuperação de senha

---

## 📜 Estilos e Interface

Os estilos CSS estão organizados por funcionalidade:
- `login.css` - Página de login
- `dashboard.css` - Painéis do sistema
- `cadastrar-produto.css` - Formulário de produtos
- `controle-estoque.css` - Controle de estoque
- `relatorio-financeiro.css` - Relatórios
- E outros específicos por função
---

## 📝 Scripts Úteis

### Executar testes
```bash
./mvnw test
```

### Gerar JAR executável
```bash
./mvnw clean package
```

### Limpar e reconstruir
```bash
./mvnw clean install -DskipTests
```

---

## 🔄 Endpoints Principais (Exemplos)

```
GET  /login                    - Página de login
GET  /dashboard                - Dashboard principal
GET  /produtos                 - Listar produtos
POST /produtos                 - Criar novo produto
GET  /requisicoes              - Listar requisições
GET  /relatorios               - Gerar relatórios
```

---

## 🛡️ Segurança

- Spring Security desabilitado (conforme configuração)
- Validação de entrada em todas as camadas
- Senha com hash (SHA/bcrypt)
- Senhas de email em variáveis de ambiente
- Validação de CPF/CNPJ

---

## 📞 Suporte e Contribuição

Para reportar bugs ou sugerir melhorias, entre em contato ou abra uma issue no repositório.

---

## 📄 Comandos Ùteis

```bash
# Iniciar e construir a aplicação
docker compose up --build

# Iniciar em segundo plano
docker compose up --build -d

# Ver status dos serviços
docker compose ps

# Acompanhar os logs da aplicação
docker compose logs -f app

# Parar os serviços mantendo os dados
docker compose down

# Apagar também os dados persistidos do PostgreSQL
docker compose down -v
```



## 📄 Licença

Este projeto foi desenvolvido como trabalho acadêmico para a matéria de Análise e Projeto de Sistemas (APS) do curso de Análise e Desenvolvimento de Sistemas (ADS).

Projeto para o Instituto Federal De Pernambuco (IFPE - Campus Paulista).


# Desenvolvido por: Gabriel Veríssimo e Thaynan Passos

 Github dos desenvolvedores: GabVr e Thaynan-Passos 

 Link pro GitHub de GabVr: https://github.com/GabVr

 Link pro GitHub de Thaynan-Passos:  https://github.com/Thaynan-passos

---

**Versão:** 0.0.1-SNAPSHOT  
**Status:** Em Desenvolvimento  
**Data Atualização:** 2026
