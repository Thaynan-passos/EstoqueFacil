package model;

import jakarta.validation.constraints.NotBlank;

public class EnderecoModel {

    @NotBlank  (message="É necessário indicar o bairro.")
    private String bairro;

    @NotBlank  (message="O cep deve ser preenchido.")
    private String cep;

    @NotBlank  (message="É necessário indicar o estado.")
    private String estado;

    @NotBlank (message="É necessário indicar a cidade.")
    private String cidade;

    @NotBlank  (message="O numero de casa deve ser preenchido.")
    private String numeroCasa;


    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;

    }

}
