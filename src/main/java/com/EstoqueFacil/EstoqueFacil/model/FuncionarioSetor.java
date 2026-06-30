package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "funcionario_setor")
public class FuncionarioSetor {

    @EmbeddedId
    private FuncionarioSetorId id;

    @ManyToOne
    @MapsId("idFuncionario")
    @JoinColumn(name = "fk_id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @MapsId("idSetor")
    @JoinColumn(name = "fk_id_setor")
    private Setor setor;





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