package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class FuncionarioModel {

    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message = "O ID deve ser maior que 0")
    private int idFuncionario;

    @NotBlank (message = "É necessário digitar o seu cpf.")
    private final String cpf;

    @NotBlank(message = "É necessário pôr o seu nome.")
    private String nome;

    @NotBlank(message = "O email deve ser preenchido.")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    private String senha;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero(message = "O nivel de acesso deve ser 0 ou mais")
    private int nivelAcesso;

    @NotBlank (message = "Não pode ficar vazio.")
    private String turno;

    public FuncionarioModel(String cpf) {
        this.cpf = cpf;
    }


    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getCpf() {
        return cpf;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public int getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(int nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

}
