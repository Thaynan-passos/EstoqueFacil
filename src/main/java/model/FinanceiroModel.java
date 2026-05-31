package model;


import jakarta.validation.constraints.NotBlank;



public class FinanceiroModel extends FuncionarioModel {

    @NotBlank(message="É necessário digitar a área responsável.")
    private String areaResponsavel;

    public FinanceiroModel(String cpf) {
        super(cpf);
    }

    public String getAreaResponsavel() {
        return areaResponsavel;
    }

    public void setAreaResponsavel(String areaResponsavel) {
        this.areaResponsavel = areaResponsavel;
    }
}
