package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name="movimentacao")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMovimentacao;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private Status status;

    @Column(name="data_movimentacao", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataMovimentacao;

    @Column(name="descricao", length = 45,nullable = false)
    @NotBlank(message="É necessário ter a descrição")
    private String  descricao;


    public int  getIdMovimentacao() {
        return idMovimentacao;
    }

    public void setIdMovimentacao(int idMovimentacao) {
        this.idMovimentacao = idMovimentacao;
    }

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(LocalDate dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
