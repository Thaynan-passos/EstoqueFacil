package model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import service.TelefoneService;

public class TelefoneModel {

    @NotNull (message="O ID não pode ser nulo")
    @Positive(message = "O ID deve ser positivo")
    private int idTelefone;

    @NotBlank(message = "É necessário pôr o seu telefone.")
    private String telefone;

    @NotBlank(message="É necessário preencher o tipo do telefone.")
    private String tipoTelefone;


    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {

        TelefoneService.numeroTelefoneValidar(telefone);
        this.telefone = telefone;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
}
