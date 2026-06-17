package com.EstoqueFacil.EstoqueFacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
import com.EstoqueFacil.EstoqueFacil.model.Telefone;
import com.EstoqueFacil.EstoqueFacil.model.Endereco;
import com.EstoqueFacil.EstoqueFacil.model.Cargo;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import com.EstoqueFacil.EstoqueFacil.service.TelefoneService;
import com.EstoqueFacil.EstoqueFacil.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model) {
        return "telas-gerente/login";
    }

    @GetMapping("/cadastro-funcionario")
    public String cadastroFuncionario(Model model) {
        return "telas-gerente/cadastro-funcionario";
    }

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/cadastro-funcionario")
    public String handleCadastro(@RequestParam String nome,
                                 @RequestParam String cpf,
                                 @RequestParam String senha,
                                 @RequestParam String email,
                                 @RequestParam(required = false) String telefone,
                                 @RequestParam(required = false) String setor,
                                 @RequestParam(required = false) String cargo) {

        // criar telefone (se fornecido)
        Telefone tel = null;
        if (telefone != null && !telefone.trim().isEmpty()) {
            tel = new Telefone();
            tel.setTelefone(telefone.replaceAll("\\D", ""));
            tel.setTipoTelefone("PRINCIPAL");
            telefoneService.cadastrarTelefone(tel);
        }

        // criar endereco placeholder (campos obrigatórios no model)
        Endereco end = new Endereco();
        end.setBairro("NA" );
        end.setCep("00000000");
        end.setEstado("SP");
        end.setCidade("Cidade" );
        end.setNumeroCasa("0");
        end.setRua("Rua");
        enderecoService.cadastrarEndereco(end);

        // montar Funcionario
        Funcionario f = new Funcionario();
        f.setNome(nome);
        f.setCpf(cpf.replaceAll("\\D", ""));
        f.setEmail(email);
        f.setSenhaHash(senha);
        f.setNivelAcesso(0);

        // mapear cargo do formulário para enum
        if (cargo != null) {
            String c = cargo.toLowerCase();
            if (c.contains("gerente")) f.setCargo(Cargo.GERENTE);
            else if (c.contains("almox")) f.setCargo(Cargo.ALMOXARIFADO);
            else f.setCargo(Cargo.FINANCEIRO);
        } else {
            f.setCargo(Cargo.FINANCEIRO);
        }

        if (tel != null) {
            f.setTelefone(java.util.List.of(tel));
        }
        f.setEndereco(end);

        funcionarioService.cadastrarFuncionario(f);

        // após cadastro, redireciona para a tela de login
        return "redirect:/login";
    }

}
