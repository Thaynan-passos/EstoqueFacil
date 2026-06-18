package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="requisicao")
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRequisicao;

    @Column(name="data_requisicao", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataRequisicao;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private Status status;

    @Column(name="motivo", nullable = false, length = 45)
    @NotBlank(message="É necessário ter o motivo")
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "fk_id_setor", nullable = false)
    @NotNull
    private Setor setor;

    @OneToMany(mappedBy = "requisicao")
    private List<RequisicaoProduto> produtos;

    public int getIdRequisicao() {
        return idRequisicao;
    }

    public void setIdRequisicao(int idRequisicao) {
        this.idRequisicao = idRequisicao;
    }

    public LocalDate getDataRequisicao() {
        return dataRequisicao;
    }
    public void setDataRequisicao(LocalDate dataRequisicao) {
        this.dataRequisicao = dataRequisicao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Setor getSetor() {
        return setor;
    }
    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public List<RequisicaoProduto> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<RequisicaoProduto> produtos) {
        this.produtos = produtos;
    }
}
