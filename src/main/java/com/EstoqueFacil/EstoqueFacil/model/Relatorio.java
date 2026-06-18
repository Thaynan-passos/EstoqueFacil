package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name="relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRelatorio;

    @Column(name="data_emissao", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataEmissao;

    @Column(name="descricao", nullable = false, length = 45)
    @NotBlank (message = "É necessário escrever qual vai ser o tipo do relatório.")
    private String descricao;

    @Column(name="valor_total_entradas", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal valorTotalEntrada;

    @Column(name="valor_total_saidas", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal valorTotalSaida;

    @Column(name = "data_inicio", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataInicio;

    @Column(name = "data_fim", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataFim;


    @ManyToOne
    @JoinColumn(name = "fk_id_funcionario", nullable = false)
    @NotNull(message = "O funcionário emissor é obrigatório.")
    private Funcionario funcionario;

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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
