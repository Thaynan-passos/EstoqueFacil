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

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataEmissao;

    @NotBlank (message = "É necessário escrever qual vai ser o tipo do relatório.")
    private String descricao;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal valorTotalEntrada;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal valorTotalSaida;

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataInicio;

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
