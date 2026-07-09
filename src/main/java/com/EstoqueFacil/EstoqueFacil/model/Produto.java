package com.EstoqueFacil.EstoqueFacil.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name="produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProduto;

    @Column(name="nome",unique = true, nullable = false, length = 45)
    @NotBlank(message="É necessário pôr o nome do produto.")
    private String nome;

    @Column(name="codigo_barras",unique = true, nullable = false, length = 20)
    @NotBlank (message="O código de barras deve ser preenchido.")
    private String codigoProduto;

    @PositiveOrZero(message = "A garantia não pode ser negativa")
    @Column(name = "garantia", nullable = false)
    private int garantia;

    @Column(name="data_cadastro", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataCadastro;

    @Column(name="valor_unitario", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message = "O valor deve ser positivo")
    private BigDecimal valorUnitario;

    @Enumerated(EnumType.STRING)
    @Column(name="classificacao", nullable = false)
    @NotNull(message = "É necessário escrever a classificação do produto.")
    private ClassificacaoProduto classificacao;

    @OneToMany(
            mappedBy = "produto",
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<RequisicaoProduto> requisicoes = new ArrayList<>();

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarras() {
        return codigoProduto;
    }

    public void setCodigoBarras(String codigoProduto) {
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

    public List<RequisicaoProduto> getRequisicoes() {
        return requisicoes;
    }

    public void setRequisicoes(List<RequisicaoProduto> requisicoes) {
        this.requisicoes = requisicoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return idProduto == produto.idProduto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProduto);
    }
    public Produto() {
    }
    public Produto(String nome,
                   String codigoBarras,
                   BigDecimal valorUnitario) {

        this.nome = nome;
        this.codigoProduto = codigoBarras;
        this.valorUnitario = valorUnitario;
    }
}
