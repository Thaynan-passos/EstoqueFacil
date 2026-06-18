package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.service.EnderecoService;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import com.EstoqueFacil.EstoqueFacil.service.TelefoneService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    // =========================
    // SERVICES
    // =========================
    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // =========================
    // LOGIN PAGE
    // =========================
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // =========================
    // AUTH LOGIN
    // =========================
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

    // =========================
    // LOGOUT
    // =========================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    // =========================
    // CADASTRO PAGE
    // =========================
    @GetMapping("/cadastro-trabalhador")
    public String cadastroFuncionario() {
        return "telas-gerente/cadastro-funcionario";
    }

    // =========================
    // CADASTRO FUNCIONARIO
    // =========================
    @PostMapping("/cadastro")
    public String handleCadastro(
           @Valid @RequestParam String nome,
           @Valid  @RequestParam String cpf,
           @Valid  @RequestParam String senha,
           @Valid  @RequestParam String email,
           @Valid  @RequestParam(required = false) Telefone telefone,
           @Valid  @RequestParam(required = false) Setor setor,
           @Valid  @RequestParam(required = false) Cargo cargo) {

        telefoneService.cadastrarTelefone(telefone);

        // =========================
        // ENDEREÇO PADRÃO
        // =========================
        Endereco end = new Endereco();
        end.setRua("Rua");
        end.setNumeroCasa("0");
        end.setBairro("NA");
        end.setCidade("Cidade");
        end.setEstado("SP");
        end.setCep("00000000");

        enderecoService.cadastrarEndereco(end);

        // =========================
        // FUNCIONÁRIO
        // =========================
        Funcionario f = new Funcionario();
        f.setNome(nome);
        f.setCpf(cpf.replaceAll("\\D", ""));
        f.setEmail(email);
        f.setSenhaHash(passwordEncoder.encode(senha));
        f.setNivelAcesso(0);
        f.setEndereco(end);

        // cargo
        f.setCargo(definirCargo(String.valueOf(cargo)));

        // telefone
        if (telefone != null) {
            f.setTelefone(List.of(telefone));
        }

        funcionarioService.cadastrarFuncionario(f, f.getSenhaHash());

        return "redirect:/login";
    }

    // =========================
    // HELPER (CARGO)
    // =========================
    private Cargo definirCargo(String cargo) {

        if (cargo == null) return Cargo.FINANCEIRO;

        String c = cargo.toLowerCase();

        if (c.contains("gerente")) return Cargo.GERENTE;
        if (c.contains("almox")) return Cargo.ALMOXARIFADO;

        return Cargo.FINANCEIRO;
    }
}