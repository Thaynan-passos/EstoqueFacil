package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import com.EstoqueFacil.EstoqueFacil.utils.MensagemEmailRedefinirSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecuperarSenhaController {

    @Autowired
    private FuncionarioService funcionarioService;


    @Autowired
    private MensagemEmailRedefinirSenha mensagemEmailRedefinirSenha;

    @GetMapping("/recuperar-senha")
    public String telaRecuperarSenha() {
        return "recuperar-senha";
    }

    @PostMapping("/recuperar-senha")
    public String redefinirSenha(
            @RequestParam String email,
            @RequestParam String novaSenha,
            @RequestParam String confirmarSenha,
            Model model) {

        try {

            Funcionario funcionario = funcionarioService.redefinirSenha(
                    email,
                    novaSenha,
                    confirmarSenha
            );

            mensagemEmailRedefinirSenha.enviarConfirmacaoRedefinirSenha(
                    funcionario.getEmail(),
                    funcionario.getNome()
            );

            model.addAttribute(
                    "sucesso",
                    "Senha redefinida com sucesso! Você já pode fazer login."
            );

            return "recuperar-senha";

        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "recuperar-senha";
        }
    }
}