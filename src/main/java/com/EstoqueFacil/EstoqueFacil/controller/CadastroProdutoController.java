package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.ClassificacaoProduto;
import com.EstoqueFacil.EstoqueFacil.model.Produto;
import com.EstoqueFacil.EstoqueFacil.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/produto")
public class CadastroProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // =========================
    // CADASTRAR PRODUTO 
    // =========================
    @PostMapping("/cadastrar")
    public String cadastrarProduto(
            @RequestParam String nome,
            @RequestParam String codigoBarras,
            @RequestParam int garantia,
            @RequestParam BigDecimal valorUnitario,
            @RequestParam String categoria
    ) {

        Produto produto = new Produto();

        // nome
        produto.setNomeProduto(nome);

        // código de barras
        produto.setCodigoBarras(codigoBarras);

        // garantia (se não vier do form, pode ser padrão 0 ou campo depois)
        produto.setGarantia(garantia);

        // data do cadastro (OBRIGATÓRIO no seu model)
        produto.setDataCadastro(LocalDate.now());

        // valor
        produto.setValorUnitario(valorUnitario);

        // classificação (ENUM)
        produto.setClassificacao(mapClassificacao(categoria));

        produtoService.cadastrarProduto(produto);

        return "redirect:/cadastrar-produto";
    }

    // =========================
    // MAPEAMENTO STRING -> ENUM
    // =========================
    private ClassificacaoProduto mapClassificacao(String categoria) {

        if (categoria == null) {
            throw new IllegalArgumentException("Categoria inválida");
        }

        return switch (categoria.toLowerCase()) {

            case "carro" -> ClassificacaoProduto.REPARO;
            case "comida" -> ClassificacaoProduto.CONSUMO;
            case "limpeza" -> ClassificacaoProduto.LIMPEZA;
            default -> throw new IllegalArgumentException("Categoria inválida: " + categoria);
        };
    }
}