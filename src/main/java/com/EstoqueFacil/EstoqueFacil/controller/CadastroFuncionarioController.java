package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import com.EstoqueFacil.EstoqueFacil.utils.MensagemEmailFuncionarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cadastro")
public class CadastroFuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private MensagemEmailFuncionarioUtil emailUtil;

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
            @RequestParam Cargo cargo,
            @RequestParam String telefone,
            @RequestParam String cep,
            @RequestParam String rua,
            @RequestParam (required = false) String numero,
            @RequestParam String bairro,
            @RequestParam String cidade,
            @RequestParam String estado
    ) {


        Funcionario f = new Funcionario();
        f.setNome(nome);
        f.setCpf(cpf.replaceAll("\\D", ""));
        f.setEmail(email);
        f.setSenhaHash(senha);
        f.setCargo(cargo);
        

        if (cargo == null) {
            throw new RuntimeException("Cargo obrigatório");
        }

        Endereco end = new Endereco();
        end.setCep(cep.replace("-", ""));
        end.setRua(rua);
        end.setNumeroCasa(numero);
        end.setBairro(bairro);
        end.setCidade(cidade);
        end.setEstado(estado);

        f.setEndereco(end);

        Telefone tel = new Telefone();
        tel.setTelefone(telefone);
        tel.setTipoTelefone("PRINCIPAL");

        f.setTelefone(List.of(tel));


        funcionarioService.cadastrarFuncionario(f, senha);
        try {
            emailUtil.enviarConfirmacao(f.getEmail(), f.getNome());
        } catch (Exception e) {
            System.err.println("Email não enviado: " + e.getMessage());
        }
        return "redirect:/cadastro";


    }
}