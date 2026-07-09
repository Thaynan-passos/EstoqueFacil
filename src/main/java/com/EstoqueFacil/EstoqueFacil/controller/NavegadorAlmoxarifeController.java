package com.EstoqueFacil.EstoqueFacil.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EstoqueFacil.EstoqueFacil.model.ClassificacaoProduto;
import com.EstoqueFacil.EstoqueFacil.model.Lote;
import com.EstoqueFacil.EstoqueFacil.model.Movimentacao;
import com.EstoqueFacil.EstoqueFacil.model.Produto;
import com.EstoqueFacil.EstoqueFacil.model.Status;
import com.EstoqueFacil.EstoqueFacil.repository.LoteRepository;
import com.EstoqueFacil.EstoqueFacil.repository.ProdutoRepository;
import com.EstoqueFacil.EstoqueFacil.service.LoteService;
import com.EstoqueFacil.EstoqueFacil.service.MovimentacaoService;
import com.EstoqueFacil.EstoqueFacil.service.ProdutoService;
import com.EstoqueFacil.EstoqueFacil.utils.AuthUtil;


@Controller
public class NavegadorAlmoxarifeController {

    @Autowired
    LoteService loteService;

    @Autowired
    LoteRepository loteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    MovimentacaoService movimentacaoService;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private com.EstoqueFacil.EstoqueFacil.service.FornecedorService fornecedorService;

    @GetMapping("/dashboard-almoxarife")
    public String dashboardAlmoxarife(Model model) {

        if (!authUtil.isLogado())
            return "redirect:/login";

        model.addAttribute("totalProdutos", produtoService.buscarTodosProdutos().size());

        model.addAttribute("movimentacoes",
                movimentacaoService.buscarTodasMovimentacoes());


        return "telas-almoxarife/dashboard-almoxarife";
    }

    @GetMapping("/cadastrar-produto")
    public String cadastrarProduto(Model model) {

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_ALMOXARIFADO"))
            return "redirect:/dashboard-almoxarife";

        model.addAttribute("fornecedores", fornecedorService.buscarTodosFornecedores());
        return "telas-almoxarife/cadastrar-produto";
    }

    @PostMapping("/produto/cadastrar-admin")
    public String cadastrarProduto(@ModelAttribute Produto produto) {

        produto.setDataCadastro(LocalDate.now());

        produtoService.cadastrarProduto(produto);

        return "redirect:/cadastrar-produto";
    }


    @GetMapping("/entrada-materiais")
    public String entradaMateriais(Model model) {

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_ALMOXARIFADO"))
            return "redirect:/dashboard-almoxarife";

        model.addAttribute("lotes", loteRepository.findAll());

        return "telas-almoxarife/entrada-materiais";
    }

    @PostMapping("/entrada")
    public String registrarEntrada(@RequestParam Integer loteId,
                                   @RequestParam Integer quantidade,
                                   @RequestParam String descricao,
                                   @RequestParam String dataMovimentacao) {


        Movimentacao mov = new Movimentacao();
        mov.setStatus(Status.APROVADO);
        mov.setDescricao(descricao);
        mov.setDataMovimentacao(LocalDate.parse(dataMovimentacao));

        movimentacaoService.registrarEntrada(loteId, quantidade, mov);

        return "redirect:/entrada-materiais";
    }

    @GetMapping("/saidas-materiais")
    public String saidaMateriais(Model model) {

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_ALMOXARIFADO"))
            return "redirect:/dashboard-almoxarife";
        model.addAttribute("lotes", loteService.buscarTodosLotes());



        return "telas-almoxarife/saidas-materiais";
    }

    @PostMapping("/saidas-materiais")
    public String registrarSaida(@RequestParam Integer loteId,
                                 @RequestParam Integer quantidade,
                                 @RequestParam String solicitante,
                                 @RequestParam String setor,
                                 @RequestParam String observacoes) {

        movimentacaoService.registrarSaida(
                loteId,
                quantidade,
                solicitante,
                setor,
                observacoes
        );

        return "redirect:/saidas-materiais";
    }
    @GetMapping("/inventario")
    public String inventario(Model model) {

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_ALMOXARIFADO"))
            return "redirect:/dashboard-almoxarife";

        // Agrupa os lotes por produto e soma quantidades
        List<Lote> lotes = loteRepository.findAll();

        Map<Integer, InventarioItem> mapaInventario = new LinkedHashMap<>();
        for (Lote lote : lotes) {
            Produto p = lote.getProduto();
            mapaInventario.merge(
                    p.getIdProduto(),
                    new InventarioItem(p, lote.getQuantidade()),
                    (existing, novo) -> {
                        existing.quantidadeTotal += novo.quantidadeTotal;
                        return existing;
                    }
            );
        }

        // Produtos sem nenhum lote também aparecem
        List<Produto> todosProdutos = produtoRepository.findAll();
        for (Produto p : todosProdutos) {
            mapaInventario.putIfAbsent(p.getIdProduto(), new InventarioItem(p, 0));
        }

        List<String> categorias = Arrays.stream(ClassificacaoProduto.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        model.addAttribute("inventario", new ArrayList<>(mapaInventario.values()));
        model.addAttribute("categorias", categorias);

        return "telas-almoxarife/inventario";
    }


    public static class InventarioItem {
        public Produto produto;
        public int quantidadeTotal;

        public InventarioItem(Produto produto, int quantidadeTotal) {
            this.produto = produto;
            this.quantidadeTotal = quantidadeTotal;
        }

        public Produto getProduto() { return produto; }
        public int getQuantidadeTotal() { return quantidadeTotal; }
    }
}