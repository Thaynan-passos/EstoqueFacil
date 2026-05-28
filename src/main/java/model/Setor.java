package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.math.BigDecimal;



public class Setor {

    @NotNull
    private int idSetor;

    @NotBlank(message = "É necessário pôr o nome do setor.")
    private String nomeSetor;

    @NotNull
    private int capacidade;

    @NotNull
    private BigDecimal orcamentoMensal;

    @NotBlank(message = "É necessário escrever o seu setor")
    private String TipoSetor;


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

    public String getTipoSetor() {
        return TipoSetor;
    }

    public void setTipoSetor(String TipoSetor) {
        this.TipoSetor = TipoSetor;
    }

}
