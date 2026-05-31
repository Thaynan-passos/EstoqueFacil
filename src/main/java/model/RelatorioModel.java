package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;
import java.time.LocalDate;



public class RelatorioModel {

    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message = "O ID deve ser positivo")
    private int idRelatorio;

    private LocalDate dataEmissao;

    @NotBlank (message = "É necessário escrever qual vai ser o tipo do relatório.")
    private String tipoRelatorio;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal valorTotalEntrada;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal valorTotalSaida;

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataInicioPeriodo;

    @NotNull (message = "Não pode ficar nulo.")
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
