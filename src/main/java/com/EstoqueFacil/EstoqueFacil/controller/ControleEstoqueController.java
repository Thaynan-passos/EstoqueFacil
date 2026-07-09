package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.ClassificacaoProduto;
import com.EstoqueFacil.EstoqueFacil.model.Lote;
import com.EstoqueFacil.EstoqueFacil.model.Produto;
import com.EstoqueFacil.EstoqueFacil.repository.LoteRepository;
import com.EstoqueFacil.EstoqueFacil.repository.ProdutoRepository;
import com.EstoqueFacil.EstoqueFacil.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Controller
public class ControleEstoqueController {

    @Autowired
    LoteRepository loteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private AuthUtil authUtil;

    @GetMapping("/controle-estoque")
    public String controleEstoque(Model model){

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_ALMOXARIFADO"))
            return "redirect:/dashboard-almoxarife";

        List<Lote> lotes = loteRepository.findAll();

        Map<Integer, NavegadorAlmoxarifeController.InventarioItem> mapa = new LinkedHashMap<>();

        for (Lote lote : lotes){

            Produto p = lote.getProduto();

            mapa.merge(

                    p.getIdProduto(),

                    new NavegadorAlmoxarifeController.InventarioItem(p,lote.getQuantidade()),

                    (existente,novo)->{

                        existente.quantidadeTotal += novo.quantidadeTotal;

                        return existente;

                    });

        }

        List<Produto> produtos = produtoRepository.findAll();

        for(Produto p : produtos){

            mapa.putIfAbsent(

                    p.getIdProduto(),

                    new NavegadorAlmoxarifeController.InventarioItem(p,0));

        }

        List<String> categorias = Arrays.stream(ClassificacaoProduto.values())
                .map(Enum::name)
                .toList();

        model.addAttribute("inventario",new ArrayList<>(mapa.values()));
        model.addAttribute("categorias",categorias);

        return "telas-almoxarife/controle-estoque";
    }

    //método para editar o produto

    @GetMapping("/controle-estoque/editar/{id}")
    public String editarProduto(@PathVariable Integer id, Model model){

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_ALMOXARIFADO"))
            return "redirect:/dashboard-almoxarife";

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        model.addAttribute("produto", produto);

        return "telas-almoxarife/editar-produto";
    }

    @PostMapping("/controle-estoque/editar")
    public String salvarEdicao(@ModelAttribute Produto produto){

        Produto existente = produtoRepository.findById(produto.getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        existente.setNome(produto.getNome());
        existente.setCodigoBarras(produto.getCodigoBarras());
        existente.setGarantia(produto.getGarantia());
        existente.setValorUnitario(produto.getValorUnitario());

        produtoRepository.save(existente);

        return "redirect:/controle-estoque";
    }
}
