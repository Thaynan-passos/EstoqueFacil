package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.service.FornecedorService;
import com.EstoqueFacil.EstoqueFacil.utils.MensagemEmailFornecedorUtil;
import com.EstoqueFacil.EstoqueFacil.utils.MensagemEmailFuncionarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cadastrar")
public class CadastroFornecedorController {

    private final FornecedorService fornecedorService;


    @Autowired
    private MensagemEmailFornecedorUtil emailUtil;

    public CadastroFornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    // abre tela
    @GetMapping
    public String telaFornecedor(org.springframework.ui.Model model) {
        model.addAttribute("fornecedores", fornecedorService.buscarTodosFornecedores());
        return "telas-gerente/cadastrar-fornecedor";
    }

    // cadastra fornecedor
    @PostMapping("/fornecimento")
    public String cadastrarFornecedor(
            @RequestParam String nome,
            @RequestParam String cnpj,
            @RequestParam String email,
            @RequestParam String telefone,
            @RequestParam String cep,
            @RequestParam String rua,
            @RequestParam String numero,
            @RequestParam String bairro,
            @RequestParam String cidade,
            @RequestParam String estado
    ) {

        // =========================
        // FORNECEDOR
        // =========================
        Fornecedor f = new Fornecedor();
        f.setRazaoSocial(nome);
        f.setCnpj(cnpj.replaceAll("\\D", ""));
        f.setEmail(email);

        // =========================
        // ENDEREÇO
        // =========================
        Endereco end = new Endereco();
        end.setCep(cep.replace("-", ""));
        end.setRua(rua);
        end.setNumeroCasa(numero);
        end.setBairro(bairro);
        end.setCidade(cidade);
        end.setEstado(estado);

        f.setEndereco(end);

        // =========================
        // TELEFONE
        // =========================
        Telefone tel = new Telefone();
        tel.setTelefone(telefone);
        tel.setTipoTelefone("PRINCIPAL");

        f.setTelefone(List.of(tel));

        // =========================
        // SALVAR
        // =========================


        fornecedorService.cadastrarFornecedor(f);
        emailUtil.enviarConfirmacaoFornecedor(f.getEmail(), f.getRazaoSocial());

        return "redirect:/cadastrar-fornecedor";
    }
}