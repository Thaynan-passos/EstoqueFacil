package com.EstoqueFacil.EstoqueFacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class LoginController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String autenticar(
            @Valid @RequestParam String cpf,
            @Valid @RequestParam String senha,
            Model model,
            HttpSession session) {

        try {
            String cpfLimpo = cpf.replaceAll("\\D", "");

            Funcionario funcionario = funcionarioService.buscarPorCpf(cpfLimpo);

            if (funcionario == null ||
                    !passwordEncoder.matches(senha, funcionario.getSenhaHash())) {

                model.addAttribute("erro", "CPF ou senha inválidos");
                return "login";
            }

            session.setAttribute("usuarioLogado", funcionario);

            return switch (funcionario.getCargo()) {
                case GERENTE -> "redirect:/dashboard-gerente";
                case ALMOXARIFADO -> "redirect:/dashboard-almoxarife";
                default -> "redirect:/dashboard-funcionario";
            };

        } catch (Exception e) {
            model.addAttribute("erro", "CPF ou senha inválidos");
            return "login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}

