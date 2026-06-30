package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
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

    @GetMapping("/recuperar-senha")
    public String telaRecuperarSenha() {
        return "recuperar-senha";
    }

    @PostMapping("/recuperar-senha")
    public String redefinirSenha(
            @RequestParam String cpf,
            @RequestParam String email,
            @RequestParam String novaSenha,
            @RequestParam String confirmarSenha,
            Model model) {

        try {
            String cpfLimpo = cpf.replaceAll("\\D", "");

            funcionarioService.redefinirSenha(cpfLimpo, email, novaSenha, confirmarSenha);

            model.addAttribute("sucesso", "Senha redefinida com sucesso! Você já pode fazer login.");
            return "recuperar-senha";

        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "recuperar-senha";
        }
    }
}