package com.EstoqueFacil.EstoqueFacil.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EstoqueFacil.EstoqueFacil.model.ClassificacaoProduto;
import com.EstoqueFacil.EstoqueFacil.model.Produto;
import com.EstoqueFacil.EstoqueFacil.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class CadastroProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/cadastrar")
    public String cadastrarProduto(
            @RequestParam String nome,
            @RequestParam String codigoBarras,
            @RequestParam int garantia,
            @RequestParam BigDecimal valorUnitario,
            @RequestParam String categoria
    ) {

        Produto produto = new Produto();
        produto.setNomeProduto(nome);
        produto.setCodigoBarras(codigoBarras);
        produto.setGarantia(garantia);
        produto.setDataCadastro(LocalDate.now());
        produto.setValorUnitario(valorUnitario);
        produto.setClassificacao(mapClassificacao(categoria));

        produtoService.cadastrarProduto(produto);

        return "redirect:/cadastrar-produto";
    }

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