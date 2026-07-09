package com.EstoqueFacil.EstoqueFacil.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "requisicao")
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRequisicao;

    @Column(name = "data_requisicao", nullable = false)
    @NotNull(message = "Não pode ficar nulo.")
    private LocalDate dataRequisicao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NotNull(message = "Não pode ficar nulo.")
    private Status status;

    @Column(name = "motivo", nullable = false, length = 500)
    @NotBlank(message = "É necessário ter o motivo")
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "fk_id_setor", nullable = false)
    @NotNull
    private Setor setor;

    @ManyToOne
    @JoinColumn(name = "fk_id_funcionario", nullable = false)
    @NotNull
    private Funcionario funcionario;

    @OneToMany(
            mappedBy = "requisicao",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<RequisicaoProduto> produtos = new ArrayList<>();

    public Integer getIdRequisicao() {
        return idRequisicao;
    }

    public void setIdRequisicao(Integer idRequisicao) {
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
}
