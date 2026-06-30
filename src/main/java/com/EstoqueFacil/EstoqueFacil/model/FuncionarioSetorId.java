package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class FuncionarioSetorId implements Serializable {

    @Column(name = "fk_id_funcionario")
    private Integer idFuncionario;

    @Column(name = "fk_id_setor")
    private Integer idSetor;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    public FuncionarioSetorId(Integer idFuncionario,
                              Integer idSetor,
                              LocalDate dataInicio) {
        this.idFuncionario = idFuncionario;
        this.idSetor = idSetor;
        this.dataInicio = dataInicio;
    }

    public Integer getIdFuncionario() {
            return  idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Integer getIdSetor() {
        return  idSetor;
    }

    public void setIdSetor(Integer idSetor) {
        this.idSetor = idSetor;
    }

    public LocalDate getDataInicio() {
        return  dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataInicio, idFuncionario, idSetor);
    }
}