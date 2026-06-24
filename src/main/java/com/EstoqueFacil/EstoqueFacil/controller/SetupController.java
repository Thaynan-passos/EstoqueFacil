package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.Cargo;
import com.EstoqueFacil.EstoqueFacil.model.Endereco;
import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
import com.EstoqueFacil.EstoqueFacil.model.Telefone;
import com.EstoqueFacil.EstoqueFacil.service.EnderecoService;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import com.EstoqueFacil.EstoqueFacil.service.TelefoneService;
import com.EstoqueFacil.EstoqueFacil.utils.MensagemEmailFuncionarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/atualizar-senha")
public class SetupController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MensagemEmailFuncionarioUtil emailFuncionarioUtil;

    @GetMapping
    public String setup(Model model) {
        boolean gerenteExiste = funcionarioService.gerenteExiste();
        if (gerenteExiste) {
            return "redirect:/login";
        }
        return "setup";
    }

    @PostMapping
    public String criarPrimeiroGerente(
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String confirmarSenha,
            Model model) {

        boolean gerenteExiste = funcionarioService.gerenteExiste();
        if (gerenteExiste) {
            return "redirect:/login";
        }

        if (!senha.equals(confirmarSenha)) {
            model.addAttribute("erro", "Senhas não conferem");
            return "setup";
        }

        if (senha.length() < 6) {
            model.addAttribute("erro", "Senha deve ter no mínimo 6 caracteres");
            return "setup";
        }

        try {
            Endereco endereco = new Endereco();
            endereco.setRua("Não informado");
            endereco.setNumeroCasa("0");
            endereco.setBairro("Não informado");
            endereco.setCidade("Não informado");
            endereco.setEstado("SP");
            endereco.setCep("00000000");
            enderecoService.cadastrarEndereco(endereco);

            Telefone telefone = new Telefone();
            telefone.setTelefone("00000000000");
            telefone.setTipoTelefone("PRINCIPAL");
            telefoneService.cadastrarTelefone(telefone);

            Funcionario gerente = new Funcionario();
            gerente.setNome(nome);
            gerente.setCpf(cpf.replaceAll("\\D", ""));
            gerente.setEmail(email);
            gerente.setSenhaHash(passwordEncoder.encode(senha));
            gerente.setNivelAcesso(10);
            gerente.setCargo(Cargo.GERENTE);
            gerente.setEndereco(endereco);
            gerente.setTelefone(List.of(telefone));


            funcionarioService.cadastrarFuncionario(gerente, senha);
            emailFuncionarioUtil.enviarConfirmacao(email, nome);

            return "redirect:/login";

        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao criar gerente: " + e.getMessage());
            return "setup";
        }
    }
}
