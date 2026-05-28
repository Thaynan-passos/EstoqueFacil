package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;



public class Lote {

    @NotNull
    private int idLote;

    @NotNull
    private int numeroLote;

    @NotNull
    private LocalDate dataFabricacao;

    @NotNull
    private LocalDate dataValidade;

    @NotNull
    private int quantidade;

    @NotBlank(message="É necessário digitar o nome do fabricador.")
    private String fabricador;


    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(int numeroLote) {
        this.numeroLote = numeroLote;
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

    public String getFabricador() {
        return fabricador;
    }

    public void setFabricador(String fabricador) {
        this.fabricador = fabricador;
    }
}
