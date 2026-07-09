package com.EstoqueFacil.EstoqueFacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
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


//
//    @PostMapping("/autenticar")
//    public String autenticar(
//            @Valid @RequestParam String cpf,
//            @Valid @RequestParam String senha,
//            Model model,
//            HttpSession session) {
//
//
//        System.out.println("ENTROU NO LOGIN");
//
//
//        try {
//
//            String cpfLimpo = cpf.replaceAll("\\D", "");
//
//            Funcionario funcionario =
//                    funcionarioService.buscarPorCpf(cpfLimpo);
//
//
//            System.out.println("FUNCIONARIO: " + funcionario);
//
//
//            if (funcionario == null ||
//                    !passwordEncoder.matches(senha, funcionario.getSenhaHash())) {
//
//                model.addAttribute("erro", "CPF ou senha inválidos.");
//                return "login";
//            }
//
//
//            if (!funcionario.isAtivo()) {
//
//                model.addAttribute("erro", "Funcionário desativado.");
//                return "login";
//            }
//
//
//            session.setAttribute("usuarioLogado", funcionario);
//
//
//            System.out.println("LOGIN REALIZADO");
//            System.out.println("Sessão: " + session.getId());
//
//
//            return switch (funcionario.getCargo()) {
//                case GERENTE -> "redirect:/dashboard-gerente";
//                case ALMOXARIFADO -> "redirect:/dashboard-almoxarife";
//                default -> "redirect:/dashboard-funcionario";
//            };
//
//
//        } catch (Exception e) {
//
//            e.printStackTrace();
//
//            model.addAttribute("erro", "CPF ou senha inválidos");
//            return "login";
//        }
//    }
//


    @GetMapping("/logout")
    public String logout(HttpSession session) {


        session.invalidate();


        return "redirect:/login";
    }

}