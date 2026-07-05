package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "funcionario_setor")
public class FuncionarioSetor {

    @EmbeddedId
    private FuncionarioSetorId id = new FuncionarioSetorId();

    @ManyToOne
    @MapsId("idFuncionario")
    @JoinColumn(name = "fk_id_funcionario")
    @NotNull
    private Funcionario funcionario;

    @ManyToOne
    @MapsId("idSetor")
    @JoinColumn(name = "fk_id_setor")
    @NotNull
    private Setor setor;


    public FuncionarioSetorId getId() {
        return id;
    }

    public void setId(FuncionarioSetorId id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}