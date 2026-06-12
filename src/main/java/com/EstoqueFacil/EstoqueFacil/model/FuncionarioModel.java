package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.Valid;

import java.util.List;

@Entity
public class FuncionarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFuncionario;

    @Column(name="CPF", length = 11, nullable = false, unique = true)
    @NotBlank (message = "É necessário digitar o seu cpf.")
    @CPF (message = "O formato do cpf está equivocado")
    private String cpf;

    @Column(name="Nome", length = 45, nullable = false)
    @NotBlank(message = "É necessário pôr o seu nome.")
    private String nome;

    @Column(name="Email",    unique = true,length = 45, nullable = false)
    @NotBlank(message = "O email deve ser preenchido.")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Por favor, insira um e-mail válido")
    private String email;

    @Column(name="SenhaHash", nullable = false)
    @NotBlank(message = "A senha não pode ser vazia.")
    private String senhaHash;

    @Column(name="Nivel_Acesso", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero(message = "O nivel de acesso deve ser 0 ou mais")
    private int nivelAcesso;

    @Enumerated(EnumType.STRING)
    @Column(name="Cargo", nullable = false)
    @NotNull (message = "É necessário indicar o cargo.")
    private Cargo cargo;


    @ManyToMany
    @JoinTable(
            name = "Funcionario_Telefone",
            joinColumns = @JoinColumn(name = "fk_ID_Funcionario"),
            inverseJoinColumns = @JoinColumn(name = "fk_ID_Telefone")
    )
    @NotNull(message = "Não pode ficar nulo.")
    @Valid
    private List<TelefoneModel> telefone;

    @OneToOne
    @JoinColumn(name = "fk_ID_Endereco")
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

    public List<TelefoneModel> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<TelefoneModel> telefone) {
        this.telefone = telefone;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }
    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }

}
