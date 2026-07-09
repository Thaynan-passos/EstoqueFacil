package com.EstoqueFacil.EstoqueFacil.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EstoqueFacil.EstoqueFacil.model.ClassificacaoProduto;
import com.EstoqueFacil.EstoqueFacil.model.Fornecedor;
import com.EstoqueFacil.EstoqueFacil.model.Lote;
import com.EstoqueFacil.EstoqueFacil.model.Produto;
import com.EstoqueFacil.EstoqueFacil.repository.FornecedorRepository;
import com.EstoqueFacil.EstoqueFacil.service.LoteService;
import com.EstoqueFacil.EstoqueFacil.service.ProdutoService;

@Controller
@RequestMapping("/produto")
public class CadastroProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private LoteService loteService;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @PostMapping("/cadastrar")
    public String cadastrarProduto(
            @RequestParam String nome,
            @RequestParam String codigoBarras,
            @RequestParam int garantia,
            @RequestParam BigDecimal valorUnitario,
            @RequestParam String categoria
            ,
            @RequestParam(required = false) Integer numeroLote,
            @RequestParam(required = false) String dataFabricacao,
            @RequestParam(required = false) String dataValidade,
            @RequestParam(required = false) Integer quantidade,
            @RequestParam(required = false) String dataFornecimento,
            @RequestParam(required = false) Integer fornecedorId
        ) {

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setCodigoBarras(codigoBarras);
        produto.setGarantia(garantia);
        produto.setDataCadastro(LocalDate.now());
        produto.setValorUnitario(valorUnitario);
        produto.setClassificacao(mapClassificacao(categoria));

        produtoService.cadastrarProduto(produto);

        boolean informouLote = temDadosMinimosDeLote(numeroLote, dataFabricacao, dataValidade, quantidade, dataFornecimento, fornecedorId);

        // Se foram informados dados de lote, criar lote associado ao produto
        if (numeroLote != null && numeroLote > 0) {
            Lote lote = new Lote();
            lote.setNumeroLote(numeroLote);
            if (dataFabricacao != null && !dataFabricacao.isBlank())
                lote.setDataFabricacao(LocalDate.parse(dataFabricacao));
            if (dataValidade != null && !dataValidade.isBlank())
                lote.setDataValidade(LocalDate.parse(dataValidade));
            if (dataFornecimento != null && !dataFornecimento.isBlank())
                lote.setDataFornecimento(LocalDate.parse(dataFornecimento));
            if (quantidade != null) lote.setQuantidade(quantidade);

            // associar produto salvo
            Produto produtoSalvo = produtoService.buscarProdutosPorCodigoProduto(codigoBarras);
            lote.setProduto(produtoSalvo);

            if (fornecedorId != null) {
                Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId).orElse(null);
                if (fornecedor != null) lote.setFornecedor(fornecedor);
            }

            loteService.cadastrarLote(lote);
        }

        return "redirect:/cadastrar-produto";
    }

    private boolean temDadosMinimosDeLote(Integer numeroLote,
                                          String dataFabricacao,
                                          String dataValidade,
                                          Integer quantidade,
                                          String dataFornecimento,
                                          Integer fornecedorId) {
        boolean informouNumero = numeroLote != null && numeroLote > 0;
        boolean informouFabricacao = dataFabricacao != null && !dataFabricacao.isBlank();
        boolean informouValidade = dataValidade != null && !dataValidade.isBlank();
        boolean informouQuantidade = quantidade != null && quantidade >= 0;
        boolean informouFornecimento = dataFornecimento != null && !dataFornecimento.isBlank();
        boolean informouFornecedor = fornecedorId != null;

        return informouNumero || informouFabricacao || informouValidade || informouQuantidade || informouFornecimento || informouFornecedor;
    }

    private ClassificacaoProduto mapClassificacao(String categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Categoria inválida");
        }

        return switch (categoria.toLowerCase()) {
            case "manutencao" -> ClassificacaoProduto.REPARO;
            case "consumo" -> ClassificacaoProduto.CONSUMO;
            case "limpeza" -> ClassificacaoProduto.LIMPEZA;
            default -> throw new IllegalArgumentException("Categoria inválida: " + categoria);
        };
    }
}