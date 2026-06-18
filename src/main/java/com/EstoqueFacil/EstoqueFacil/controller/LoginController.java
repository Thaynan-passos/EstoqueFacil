package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.service.EnderecoService;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import com.EstoqueFacil.EstoqueFacil.service.TelefoneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "telas-gerente/login";
    }

    @PostMapping("/login")
    public String autenticar(
            @RequestParam String email,
            @RequestParam String senha,
            Model model) {

        try {

            Funcionario funcionario =
                    funcionarioService.buscarPorEmail(email);

            if (!passwordEncoder.matches(
                    senha,
                    funcionario.getSenhaHash())) {

                model.addAttribute(
                        "erro",
                        "Email ou senha inválidos"
                );

                return "telas-gerente/login";
            }

            switch (funcionario.getCargo()) {

                case GERENTE:
                    return "redirect:/dashboard";

                case ALMOXARIFADO:
                    return "redirect:/dashboard-almoxarife";

                default:
                    return "redirect:/dashboard-funcionario";
            }

        } catch (Exception e) {

            model.addAttribute(
                    "erro",
                    "Email ou senha inválidos"
            );

            return "telas-gerente/login";
        }
    }

    @GetMapping("/cadastro-funcionario")
    public String cadastroFuncionario() {
        return "telas-gerente/cadastro-funcionario";
    }

    @PostMapping("/cadastro-funcionario")
    public String handleCadastro(
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam String senha,
            @RequestParam String email,
            @RequestParam(required = false) String telefone,
            @RequestParam(required = false) String setor,
            @RequestParam(required = false) String cargo) {

        Telefone tel = null;

        if (telefone != null && !telefone.trim().isEmpty()) {

            tel = new Telefone();
            tel.setTelefone(
                    telefone.replaceAll("\\D", "")
            );
            tel.setTipoTelefone("PRINCIPAL");

            telefoneService.cadastrarTelefone(tel);
        }

        Endereco end = new Endereco();

        end.setBairro("NA");
        end.setCep("00000000");
        end.setEstado("SP");
        end.setCidade("Cidade");
        end.setNumeroCasa("0");
        end.setRua("Rua");

        enderecoService.cadastrarEndereco(end);

        Funcionario f = new Funcionario();

        f.setNome(nome);
        f.setCpf(cpf.replaceAll("\\D", ""));
        f.setEmail(email);
        f.setSenhaHash(senha);
        f.setNivelAcesso(0);

        if (cargo != null) {

            String c = cargo.toLowerCase();

            if (c.contains("gerente")) {

                f.setCargo(Cargo.GERENTE);

            } else if (c.contains("almox")) {

                f.setCargo(Cargo.ALMOXARIFADO);

            } else {

                f.setCargo(Cargo.FINANCEIRO);
            }

        } else {

            f.setCargo(Cargo.FINANCEIRO);
        }

        if (tel != null) {
            f.setTelefone(java.util.List.of(tel));
        }

        f.setEndereco(end);

        funcionarioService.cadastrarFuncionario(f);

        return "redirect:/login";
    }
}