package model;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.Valid;

public class FuncionarioModel {

    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message = "O ID deve ser maior que 0")
    private int idFuncionario;

    @NotBlank (message = "É necessário digitar o seu cpf.")
    @CPF (message = "O formato do cpf está equivocado")
    private String cpf;

    @NotBlank(message = "É necessário pôr o seu nome.")
    private String nome;

    @NotBlank(message = "O email deve ser preenchido.")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Por favor, insira um e-mail válido")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    private String senhaHash;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero(message = "O nivel de acesso deve ser 0 ou mais")
    private int nivelAcesso;

    @NotNull (message = "É necessário indicar o cargo.")
    private Cargo cargo;

    @NotNull(message = "Não pode ficar nulo.")
    @Valid
    private TelefoneModel telefone;

    @NotNull(message = "Não pode ficar nulo.")
    @Valid
    private EnderecoModel endereco;

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {

        this.cpf = cpf.replaceAll("\\D", "").trim();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenhaHash() {
        return senhaHash;
    }

    public void setSenhaHash(String senhaHash) {
        this.senhaHash = senhaHash;
    }
    
    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public TelefoneModel getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneModel telefone) {
        this.telefone = telefone;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }
    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }
}
