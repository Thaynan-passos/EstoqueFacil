package model;

import jakarta.validation.constraints.NotBlank;

public class TelefoneModel {

    @NotBlank(message = "É necessário pôr o seu telefone.")
    private String telefone;

    @NotBlank(message="É necessário preencher o tipo do telefone.")
    private String tipoTelefone;


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(String tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }
}
