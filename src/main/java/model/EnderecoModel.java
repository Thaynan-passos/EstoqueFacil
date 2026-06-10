package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import service.EnderecoService;


public class EnderecoModel {

    @NotNull (message = "O ID não pode ser nulo.")
    @Positive (message= "O ID precisa ser positivo.")
    private int idEndereco;

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

        EnderecoService.bairroValidar(bairro);
        this.bairro = bairro.trim();
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {

        EnderecoService.cepValidar(cep);
        this.cep = cep.trim();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {

        EnderecoService.estadoValidar(estado);
        this.estado = estado.trim();
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {

        EnderecoService.cidadeValidar(cidade);
        this.cidade = cidade.trim();
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {

        EnderecoService.numeroCasaValidar(numeroCasa);
        this.numeroCasa = numeroCasa.trim();

    }

}
