package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import service.Status;

import java.time.LocalDate;

public class RequisicaoModel {

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataRequisicao;

    @NotNull (message = "Não pode ficar nulo.")
    private Status status;

    @NotBlank(message="É necessário ter a descrição")
    private String descricao;

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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
