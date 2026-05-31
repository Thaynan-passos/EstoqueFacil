package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import service.TipoSetor;


import java.math.BigDecimal;



public class SetorModel {

    @NotNull
    @Positive(message = "O ID deve ser positivo")
    private int idSetor;

    @NotBlank(message = "É necessário pôr o nome do setor.")
    private String nomeSetor;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private int capacidade;

    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal orcamentoMensal;

    @NotBlank(message = "É necessário escrever o seu setor")
    private TipoSetor TipoSetor;


    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nome) {
        this.nomeSetor = nomeSetor;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public BigDecimal getOrcamentoMensal() {
        return orcamentoMensal;
    }

    public void setOrcamentoMensal(BigDecimal orcamentoMensal) {
        this.orcamentoMensal = orcamentoMensal;
    }

    public TipoSetor getTipoSetor() {
        return TipoSetor;
    }

    public void setTipoSetor(TipoSetor TipoSetor) {
        this.TipoSetor = TipoSetor;
    }

}
