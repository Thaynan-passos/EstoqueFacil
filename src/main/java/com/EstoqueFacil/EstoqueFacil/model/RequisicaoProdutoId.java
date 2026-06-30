package com.EstoqueFacil.EstoqueFacil.model;


import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RequisicaoProdutoId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer requisicaoId;

    private Integer produtoId;

    public Integer getRequisicaoId() {
        return requisicaoId;
    }

    public void setRequisicaoId(Integer requisicaoId) {
        this.requisicaoId = requisicaoId;
    }

    public Integer getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Integer produtoId) {
        this.produtoId = produtoId;
    }



    public RequisicaoProdutoId() {
    }

    public RequisicaoProdutoId(Integer requisicaoId, Integer produtoId) {
        this.requisicaoId = requisicaoId;
        this.produtoId = produtoId;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RequisicaoProdutoId)) return false;
        RequisicaoProdutoId that = (RequisicaoProdutoId) o;
        return Objects.equals(requisicaoId, that.requisicaoId) &&
                Objects.equals(produtoId, that.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisicaoId, produtoId);
    }
}