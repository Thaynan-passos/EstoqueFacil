package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDate;

public class GerenteModel extends FuncionarioModel {

    @NotNull (message = "Não pode ficar nulo.")
    private LocalDate dataPromocao;

    @NotBlank (message= "É necessário informar o setor responsável.")
    private String setorResponsavel;

    public GerenteModel(String cpf) {
        super(cpf);
    }

    public LocalDate getDataPromocao() {
        return dataPromocao;
    }

    public void setDataPromocao(LocalDate dataPromocao) {
        this.dataPromocao = dataPromocao;
    }

    public String getSetorResponsavel() {
        return setorResponsavel;
    }

    public void setSetorResponsavel(String setorResponsavel) {
        this.setorResponsavel = setorResponsavel;
    }
}
