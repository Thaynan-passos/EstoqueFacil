package model;

import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;



public class Gerente extends Funcionario {

    @NotNull
    private LocalDate dataPromocao;

    public Gerente(String cpf) {
        super(cpf);
    }

    public LocalDate getDataPromocao() {
        return dataPromocao;
    }

    public void setDataPromocao(LocalDate dataPromocao) {
        this.dataPromocao = dataPromocao;
    }
}
