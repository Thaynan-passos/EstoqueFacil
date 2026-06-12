package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEndereco;

    @Column(name="Bairro",length = 45, nullable = false)
    @NotBlank  (message="É necessário indicar o bairro.")
    private String bairro;

    @Column(name="CEP",unique = true, length = 8, nullable = false)
    @NotBlank  (message="O cep deve ser preenchido.")
    private String cep;

    @Column(name="Estado", length = 2, nullable = false)
    @NotBlank  (message="É necessário indicar o estado.")
    private String estado;

    @Column(name="Cidade", length = 45, nullable = false)
    @NotBlank (message="É necessário indicar a cidade.")
    private String cidade;

    @Column(name="Numero", length = 20, nullable = false)
    @NotBlank  (message="O numero de casa deve ser preenchido.")
    private String numeroCasa;


    public int  getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {

        this.bairro = bairro.trim();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep.trim();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {

        this.estado = estado.trim();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {

        this.cidade = cidade.trim();
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {

        this.numeroCasa = numeroCasa.trim();

    }

}
