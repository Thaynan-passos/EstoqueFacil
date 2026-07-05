package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEndereco;

    @Column(name="bairro",length = 45, nullable = false)
    @NotBlank  (message="É necessário indicar o bairro.")
    private String bairro;

    @Column(name="cep", length = 8, nullable = false)
    @NotBlank  (message="O cep deve ser preenchido.")
    private String cep;

    @Column(name="estado", length = 2, nullable = false)
    @NotBlank  (message="É necessário indicar o estado.")
    private String estado;

    @Column(name="cidade", length = 45, nullable = false)
    @NotBlank (message="É necessário indicar a cidade.")
    private String cidade;

    @Column(name="numero", length = 20, nullable =true)
    private String numeroCasa;

    @Column(name="rua",length = 45,nullable = false)
    @NotBlank (message="É necessário indicar a rua.")
    private String rua;


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

        cep = cep.trim();
        this.cep = cep.replace("-", "");

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

        this.numeroCasa = numeroCasa;

    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua.trim();
    }

}
