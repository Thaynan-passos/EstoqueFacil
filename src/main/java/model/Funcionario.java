package model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class Funcionario {

    @NotBlank (message = "É necessário digitar o seu cpf.")
    private final String cpf;

    @NotBlank(message = "É necessário pôr o seu nome.")
    private String nome;

    @NotBlank(message = "O email deve ser preenchido.")
    private String email;

    @NotBlank(message = "A senha não pode ser vazia.")
    private String senha;

    @NotBlank(message = "A login não pode ser vazio.")
    private String login;

    @NotNull
    private int nivelAcesso;
    private String turno;

    public Funcionario(String cpf) {
        this.cpf = cpf;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
