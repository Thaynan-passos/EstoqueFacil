package com.EstoqueFacil.EstoqueFacil.model;



import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CNPJ;
import jakarta.validation.Valid;

import java.util.List;


@Entity
@Table(name="Fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFornecedor;

    @Column(name="CNPJ",unique=true, length = 14, nullable = false)
    @NotBlank(message = "É necessário digitar o seu CNPJ.")
    @CNPJ(message = "CNPJ inválido.")
    private  String cnpj;

    @Column(name="Razao_Social", length=45, nullable = false)
    @NotBlank(message = "Digite a sua razão social.")
    @Size(min = 3, max = 100)
    @Pattern(
            regexp = "[\\p{L} ]+",
            message = "O nome deve conter apenas letras"
    )
    private String razaoSocial;

    @Column(name="Email", unique = true,length = 45, nullable = false)
    @NotBlank(message = "O email deve ser preenchido.")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Por favor, insira um e-mail válido")
    private String email;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Fornecedor_Telefone",
            joinColumns = @JoinColumn(name = "fk_ID_Fornecedor"),
            inverseJoinColumns = @JoinColumn(name = "fk_ID_Telefone")
    )
    @NotNull(message = "Não pode ficar nulo.")
    @Valid
    private List<Telefone> telefone;

    @ManyToOne
    @JoinColumn(name = "fk_Endereco_Fornecedor")
    @NotNull(message = "Não pode ficar nulo.")
    @Valid
    private Endereco endereco;

    public int getIdFornecedor() {
        return idFornecedor;
    }
    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getCnpj(){
        return cnpj;
    }

    public void setCnpj(String cnpj){

        this.cnpj =cnpj.trim();
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Telefone> getTelefone() {
        return telefone;
    }

    public void setTelefone(List<Telefone> telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
