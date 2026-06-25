package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.service.FornecedorService;
import com.EstoqueFacil.EstoqueFacil.utils.MensagemEmailFornecedorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DataIntegrityViolationException;

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

    @GetMapping
    public String telaFornecedor(Model model) {
        model.addAttribute("fornecedores", fornecedorService.buscarTodosFornecedores());
        return "telas-gerente/cadastrar-fornecedor";
    }

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
            @RequestParam String estado,
            Model model
    ) {
        try {
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
            emailUtil.enviarConfirmacaoFornecedor(
                    f.getEmail(),
                    f.getRazaoSocial()
            );

            return "redirect:/cadastrar";

        } catch (DataIntegrityViolationException e) {
            model.addAttribute("erro", "Dados duplicados: CNPJ, email ou CEP já cadastrado.");
            model.addAttribute("fornecedores", fornecedorService.buscarTodosFornecedores());
            return "telas-gerente/cadastrar-fornecedor";

        } catch (Exception e) {                      // ← sem } antes, só encadeia
            e.printStackTrace();
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("fornecedores", fornecedorService.buscarTodosFornecedores());
            return "telas-gerente/cadastrar-fornecedor";
        }
    }
}