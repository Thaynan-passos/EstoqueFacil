package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "funcionario_setor")
public class FuncionarioSetor {

    @Id
    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @ManyToOne
    @JoinColumn(name = "fk_id_funcionario", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "fk_id_setor", nullable = false)
    private Setor setor;



    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
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