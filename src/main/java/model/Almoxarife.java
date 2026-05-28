package model;

import jakarta.validation.constraints.NotNull;


import java.time.LocalTime;


public class Almoxarife extends Funcionario{

    @NotNull
    private LocalTime horarioEntrada;

    @NotNull
    private LocalTime horarioSaida;

    public Almoxarife(String cpf){
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
