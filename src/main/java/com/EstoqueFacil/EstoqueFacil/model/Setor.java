package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.PositiveOrZero;


import java.math.BigDecimal;

@Entity
@Table (name="Setor")
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSetor;

    @Column(name="Nome", nullable = false, length = 45)
    @NotBlank(message = "É necessário pôr o nome do setor.")
    private String nomeSetor;

    @Column(name="Capacidade", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private int capacidade;

    @Column(name="Orcamento_Mensal", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private BigDecimal orcamentoMensal;

    @Enumerated(EnumType.STRING)
    @Column(name="Tipo", nullable = false)
    @NotNull(message = "É necessário escrever o seu setor")
    private TipoSetor tipoSetor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_Endereco_Setor")
    @NotNull(message = "Não pode ficar nulo.")
    @Valid
    private Endereco endereco;


    public int getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(int idSetor) {
        this.idSetor = idSetor;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
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
        return tipoSetor;
    }

    public void setTipoSetor(TipoSetor tipoSetor) {
        this.tipoSetor = tipoSetor;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

}
