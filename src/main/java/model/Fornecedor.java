package model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;



public class Fornecedor {

    @NotBlank(message = "É necessário digitar o seu CNPJ.")
    private final String cnpj;

    @NotNull(message = "Digite a sua razão social.")
    private String razaoSocial;

    @NotBlank(message = "O email deve ser preenchido.")
    private String email;

    public Fornecedor(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpj(){
        return cnpj;
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
}
