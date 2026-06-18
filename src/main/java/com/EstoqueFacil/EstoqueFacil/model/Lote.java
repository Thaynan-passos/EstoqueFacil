package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


import java.time.LocalDate;

@Entity
@Table(name="lote")
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLote;

    @Column(name="numero_Lote",unique = true, nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message = "O numero do lote deve ser maior que 0 ")
    private int numeroLote;

    @Column(name="data_fabricacao", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataFabricacao;

    @Column(name="data_validade", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataValidade;

    @Column(name="quantidade", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero(message = "A quantidade deve ser 0 ou positivo")
    private int quantidade;

    @Column(name="data_fornecimento", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataFornecimento;

    @ManyToOne
    @JoinColumn(name = "fk_id_produto", nullable = false)
    @NotNull(message = "O produto do lote é obrigatório.")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fk_id_fornecedor", nullable = false)
    @NotNull(message = "O fornecedor do lote é obrigatório.")
    private Fornecedor fornecedor;

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(int numeroLote) {
        this.numeroLote = numeroLote;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataFornecimento() {
        return dataFornecimento;
    }

    public void setDataFornecimento(LocalDate dataFornecimento) {
        this.dataFornecimento = dataFornecimento;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
            this.produto = produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
