package model;


import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CNPJ;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class FornecedorModel {

    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message="O ID deve ser maior que 0")
    private int idFornecedor;

    @NotBlank(message = "É necessário digitar o seu CNPJ.")
    @CNPJ(message = "CNPJ inválido.")
    private  String cnpj;

    @NotNull(message = "Digite a sua razão social.")
    private String razaoSocial;

    @NotBlank(message = "O email deve ser preenchido.")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "Por favor, insira um e-mail válido")
    private String email;

    @NotNull(message = "Não pode ficar nulo.")
    @Valid
    private TelefoneModel telefone;

    @NotNull(message = "Não pode ficar nulo.")
    @Valid
    private EnderecoModel endereco;

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

    public TelefoneModel getTelefone() {
        return telefone;
    }

    public void setTelefone(TelefoneModel telefone) {
        this.telefone = telefone;
    }

    public EnderecoModel getEndereco() {
        return endereco;
    }
    public void setEndereco(EnderecoModel endereco) {
        this.endereco = endereco;
    }
}
