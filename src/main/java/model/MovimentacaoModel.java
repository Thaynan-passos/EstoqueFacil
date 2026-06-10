package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public class MovimentacaoModel {

    @NotNull (message = "Não pode ficar nulo.")
    @Positive (message="O ID deve ser maior que 0")
    private int idMovimentacao;

    @NotNull (message = "Não pode ficar nulo.")
    private Status status;

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataMovimentacao;

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
