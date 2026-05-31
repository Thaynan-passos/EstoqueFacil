package model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


import java.time.LocalDate;



public class LoteModel {

    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message = "O ID deve ser positivo")
    private int idLote;

    @NotNull (message = "Não pode ficar nulo.")
    @Positive(message = "O numero do lote deve ser maior que 0 ")
    private final int numeroLote;

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataFabricacao;

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataValidade;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero(message = "A quantidade deve ser 0 ou positivo")
    private int quantidade;

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataFornecimento;

    public LoteModel(int numeroLote) {
        this.numeroLote = numeroLote;
    }


    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataFornecimento() {
        return dataFornecimento;
    }

    public void setDataFornecimento(LocalDate dataFornecimento) {
        this.dataFornecimento = dataFornecimento;
    }

}
