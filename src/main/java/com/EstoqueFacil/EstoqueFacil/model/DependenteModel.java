package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.validation.constraints.NotBlank;

public class DependenteModel {

    @NotBlank (message="Deve possuir um nome")
    private String nomeDependente;

    @NotBlank(message= "Não pode ficar vazio")
    private Sexo sexoDependente;

    public String getNomeDependente() {
        return nomeDependente;
    }

    public void setNomeDependente(String nomeDependente) {
        this.nomeDependente = nomeDependente;
    }

    public Sexo getSexoDependente() {
        return sexoDependente;
    }

    public void setSexoDependente(Sexo sexoDependente) {
        this.sexoDependente = sexoDependente;
    }
}
