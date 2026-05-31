package model;

import jakarta.validation.constraints.NotNull;


import java.time.LocalTime;


public class AlmoxarifeModel extends FuncionarioModel {

    @NotNull (message = "Não pode ficar nulo.")
    private LocalTime horarioEntrada;

    @NotNull (message = "Não pode ficar nulo.")
    private LocalTime horarioSaida;

    public AlmoxarifeModel(String cpf){
        super(cpf);
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

}
