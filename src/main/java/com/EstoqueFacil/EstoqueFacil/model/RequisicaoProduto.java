package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Entity
@Table(name="requisicao_produto")
public class RequisicaoProduto {

    @EmbeddedId
    private RequisicaoProdutoId id = new RequisicaoProdutoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("requisicaoId")
    @JoinColumn(name = "fk_id_requisicao", nullable = false)
    @NotNull(message = "A requisição é obrigatória.")
    private Requisicao requisicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produtoId")
    @JoinColumn(name = "fk_id_produto", nullable = false)
    @NotNull(message = "O produto é obrigatório.")
    private Produto produto;
    @Positive(message = "A quantidade deve ser maior que zero")
    @Column(name="quantidade_solicitada", nullable = false)
    private int quantidadeSolicitada;


    public RequisicaoProdutoId getId() {
        return id;
    }

    public void setId(RequisicaoProdutoId id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;

        if (id == null)
            id = new RequisicaoProdutoId();

        if (produto != null)
            id.setProdutoId(produto.getIdProduto());
    }

    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;

        if (id == null)
            id = new RequisicaoProdutoId();

        if (requisicao != null)
            id.setRequisicaoId(requisicao.getIdRequisicao());
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }
    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequisicaoProduto)) return false;
        RequisicaoProduto that = (RequisicaoProduto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public RequisicaoProduto() {
    }
    public RequisicaoProduto(Requisicao requisicao,
                             Produto produto,
                             int quantidade) {

        setRequisicao(requisicao);
        setProduto(produto);
        this.quantidadeSolicitada = quantidade;
    }
}