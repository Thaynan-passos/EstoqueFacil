package com.EstoqueFacil.EstoqueFacil.model;

public enum Cargo {

    GERENTE, FINANCEIRO, ALMOXARIFADO;

    public String toUpperCase() {
        return this.name();
    }
}
