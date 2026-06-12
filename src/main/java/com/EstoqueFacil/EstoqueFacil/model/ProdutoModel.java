package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;

    @Column(name="Nome_Produto",unique = true, nullable = false, length = 45)
    @NotBlank(message="É necessário pôr o nome do produto.")
    private String nome;

    @Column(name="Codigo_Barras",unique = true, nullable = false, length = 20)
    @NotBlank (message="O código de barras deve ser preenchido.")
    private String codigoProduto;

    @Column(name="Garantia", nullable = false)
    @NotNull (message="Não pode ser nulo.")
    @PositiveOrZero(message = "A garantia não pode ser negativa")
    private int garantia;

    @Column(name="Data_Cadastro", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataCadastro;

    @Column(name="Valor_Unitario", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valorUnitario;

    @Enumerated(EnumType.STRING)
    @Column(name="Classificacao", nullable = false)
    @NotBlank(message = "É necessário escrever a classificação do produto.")
    private ClassificacaoProduto classificacao;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nome;
    }

    public void setNomeProduto(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarras() {
        return codigoProduto;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoProduto = codigoProduto;
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
