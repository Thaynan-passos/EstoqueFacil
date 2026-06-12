package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
public class RelatorioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRelatorio;

    @Column(name="Data_Emissao", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataEmissao;

    @Column(name="Descricao", nullable = false, length = 45)
    @NotBlank (message = "É necessário escrever qual vai ser o tipo do relatório.")
    private String descricao;

    @Column(name="Valor_Entrada", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal valorTotalEntrada;

    @Column(name="Valor_Total_Saida", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal valorTotalSaida;

    @Column(name = "Data_Inicio", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataInicio;

    @Column(name = "Data_Fim", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataFim;

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorTotalEntrada() {
        return valorTotalEntrada;
    }

    public void setValorTotalEntrada(BigDecimal valorTotalEntrada) {
        this.valorTotalEntrada = valorTotalEntrada;
    }

    public BigDecimal getValorTotalSaida() {
        return valorTotalSaida;
    }

    public void setValorTotalSaida(BigDecimal valorTotalSaida) {
        this.valorTotalSaida = valorTotalSaida;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
