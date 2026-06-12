package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

@Entity
public class RequisicaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRequisicao;

    @Column(name="Data_Requisicao", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataRequisicao;

    @Enumerated(EnumType.STRING)
    @Column(name="Status", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private Status status;

    @Column(name="Motivo", nullable = false, length = 45)
    @NotBlank(message="É necessário ter o motivo")
    private String motivo;

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
}
