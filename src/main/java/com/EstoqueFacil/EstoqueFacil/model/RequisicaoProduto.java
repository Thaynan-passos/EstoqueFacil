package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name="requisicao_produto")
public class RequisicaoProduto {




    @EmbeddedId
    private RequisicaoProdutoId id;

    @ManyToOne
    @MapsId("requisicaoId")
    @JoinColumn(name = "fk_id_requisicao")
    private Requisicao requisicao;

    @ManyToOne
    @MapsId("produtoId")
    @JoinColumn(name = "fk_id_produto")
    private Produto produto;
    @Column(name="quantidade_solicitada", nullable = false)
    @NotNull(message="Não pode ficar vazio")
    @PositiveOrZero
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
    }
    public Requisicao getRequisicao() {
        return requisicao;
    }

    public void setRequisicao(Requisicao requisicao) {
        this.requisicao = requisicao;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }
    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }
}