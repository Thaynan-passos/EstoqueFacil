package model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class FornecedorModel {

    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message="O ID deve ser maior que 0")
    private int idFornecedor;

    @NotBlank(message = "É necessário digitar o seu CNPJ.")
    private final String cnpj;

    @NotNull(message = "Digite a sua razão social.")
    private String razaoSocial;

    @NotBlank(message = "O email deve ser preenchido.")
    private String email;

    public FornecedorModel(String cnpj) {
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
