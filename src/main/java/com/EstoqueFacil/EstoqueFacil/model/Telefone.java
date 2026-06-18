package com.EstoqueFacil.EstoqueFacil.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import com.EstoqueFacil.EstoqueFacil.service.TelefoneService;

@Entity
@Table (name="telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTelefone;

    @Column(name="telefone", unique = true,nullable = false, length = 11)
    @NotBlank(message = "É necessário pôr o seu telefone.")
    private String telefone;


    @Column(name="tipo", nullable = false, length = 45)
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
