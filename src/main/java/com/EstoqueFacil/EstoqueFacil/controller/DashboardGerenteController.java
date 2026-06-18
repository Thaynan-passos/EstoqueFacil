package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.service.ProdutoService;
import com.EstoqueFacil.EstoqueFacil.service.RequisicaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/gerente")
public class DashboardGerenteController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private RequisicaoService requisicaoService;

    @GetMapping
    public String dashboard(Model model) {

        model.addAttribute("totalProdutos",
                produtoService.buscarTodosProdutos().size());

        model.addAttribute("totalRequisicoes",
                requisicaoService.buscarTodasRequisicoes().size());

        model.addAttribute("totalFuncionarios", 0);
        model.addAttribute("estoqueBaixo", 0);

        model.addAttribute("atividades",
                java.util.List.of(
                        "Sistema iniciado",
                        "Dashboard carregado"
                ));

        return "telas-gerente/dashboard-gerente";
    }
}