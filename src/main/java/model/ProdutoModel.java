package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import service.ClassificacaoProduto;


import java.math.BigDecimal;
import java.time.LocalDate;



public class ProdutoModel {

    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message = "O ID deve ser positivo")
    private int idProduto;

    @NotBlank(message="É necessário pôr o nome do produto.")
    private String nomeProduto;

    @NotBlank (message="O código de barras deve ser preenchido.")
    private String codigoBarras;


    @Positive(message = "A garantia deve ser maior que 0")
    private int garantia;

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataCadastro;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero(message = "O valor deve ser 0 ou positivo")
    private BigDecimal valorUnitario;

    @NotBlank(message = "É necessário escrever a classificação do produto.")
    private ClassificacaoProduto classificacao;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public int getGarantia() {
        return garantia;
    }

    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public ClassificacaoProduto getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(ClassificacaoProduto classificacao) {
        this.classificacao = classificacao;
    }
}
