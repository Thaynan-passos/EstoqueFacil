package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private FuncionarioService funcionarioService;

    // abre a tela
    @GetMapping
    public String telaCadastro() {
        return "telas-gerente/cadastro-funcionario";
    }

    // salva funcionário
    @PostMapping("/funcionario")
    public String cadastrarFuncionario(
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam (required = true) Cargo cargo,
            @RequestParam String telefone
    ) {

        // =========================
        // FUNCIONÁRIO
        // =========================
        Funcionario f = new Funcionario();
        f.setNome(nome);
        f.setCpf(cpf);
        f.setEmail(email);
        f.setSenhaHash(senha);
        f.setCargo(cargo);
        f.setNivelAcesso(0);

        if (cargo == null) {
            throw new RuntimeException("Cargo obrigatório");
        }

        // =========================
        // ENDEREÇO (OBRIGATÓRIO)
        // =========================
        Endereco end = new Endereco();
        end.setRua("Não informado");
        end.setNumeroCasa("0");
        end.setBairro("Não informado");
        end.setCidade("Não informado");
        end.setEstado("PE");
        end.setCep("00000000");

        f.setEndereco(end);

        // =========================
        // TELEFONE (OBRIGATÓRIO)
        // =========================
        Telefone tel = new Telefone();
        tel.setTelefone(telefone);
        tel.setTipoTelefone("PRINCIPAL");

        f.setTelefone(List.of(tel));

        // =========================
        // SALVAR
        // =========================
        funcionarioService.cadastrarFuncionario(f, senha);

        return "redirect:/cadastro-funcionario";
    }
}