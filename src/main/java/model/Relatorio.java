package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;
import java.time.LocalDate;



public class Relatorio {

    @NotNull
    private int idRelatorio;
    private LocalDate dataEmissao;

    @NotBlank (message = "É necessário escrever qual vai ser o tipo do relatório.")
    private String tipoRelatorio;

    @NotNull
    private BigDecimal valorTotalEntrada;

    @NotNull
    private BigDecimal valorTotalSaida;

    @NotNull
    private LocalDate dataInicioPeriodo;

    @NotNull
    private LocalDate dataFimPeriodo;

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

    public String getTipoRelatorio() {
        return tipoRelatorio;
    }

    public void setTipoRelatorio(String tipoRelatorio) {
        this.tipoRelatorio = tipoRelatorio;
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

    public LocalDate getDataInicioPeriodo() {
        return dataInicioPeriodo;
    }

    public void setDataInicioPeriodo(LocalDate dataInicioPeriodo) {
        this.dataInicioPeriodo = dataInicioPeriodo;
    }

    public LocalDate getDataFimPeriodo() {
        return dataFimPeriodo;
    }

    public void setDataFimPeriodo(LocalDate dataFimPeriodo) {
        this.dataFimPeriodo = dataFimPeriodo;
    }
}
