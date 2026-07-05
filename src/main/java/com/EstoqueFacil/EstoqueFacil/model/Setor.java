package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.PositiveOrZero;


@Entity
@Table (name="setor")
public class Setor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSetor;

    @Column(name="nome", nullable = false, length = 45)
    @NotBlank(message = "É necessário pôr o nome do setor.")
    private String nomeSetor;

    @Column(name="capacidade", nullable = false)
    @NotNull (message = "Não pode ficar nulo.")
    @PositiveOrZero
    private int capacidade;

    @Enumerated(EnumType.STRING)
    @Column(name="tipo", nullable = false)
    @NotNull(message = "É necessário escrever o seu setor")
    private TipoSetor tipoSetor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_endereco_setor")
    @NotNull(message = "Não pode ficar nulo.")
    @Valid
    private Endereco endereco;


    public Integer getIdSetor() {
        return idSetor;
    }

    public void setIdSetor(Integer idSetor) {
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
