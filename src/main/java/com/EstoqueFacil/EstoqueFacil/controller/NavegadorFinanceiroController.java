package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.Requisicao;
import com.EstoqueFacil.EstoqueFacil.model.Status;
import com.EstoqueFacil.EstoqueFacil.service.*;
import com.EstoqueFacil.EstoqueFacil.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavegadorFinanceiroController {

    @Autowired
    private RequisicaoService requisicaoService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    private AuthUtil authUtil;

    @GetMapping("/dashboard-funcionario")
    public String dashboardFuncionario(Model model) {

        if (!authUtil.isLogado())
            return "redirect:/login";

        model.addAttribute("total",
                requisicaoService.totalRequisicoes());

        model.addAttribute("pendentes",
                requisicaoService.totalPorStatus(Status.PENDENTE));

        model.addAttribute("aprovadas",
                requisicaoService.totalPorStatus(Status.APROVADO));

        model.addAttribute("recusadas",
                requisicaoService.totalPorStatus(Status.NEGADO));

        model.addAttribute("ultimas",
                requisicaoService.buscarUltimas());

        return "telas-funcionario/dashboard-funcionario";
    }

    @GetMapping("/requisicoes")
    public String requisicoes(Model model) {

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_FINANCEIRO"))
            return "redirect:/dashboard-funcionario";

        model.addAttribute("produtos", produtoService.buscarTodosProdutos());
        model.addAttribute("requisicao", new Requisicao());
        return "telas-funcionario/requisicoes";
    }

    @GetMapping("/historico-requisicoes")
    public String historicoRequisicoes() {

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_FINANCEIRO"))
            return "redirect:/dashboard-funcionario";

        return "telas-funcionario/historico-requisicoes";
    }

    @GetMapping("/minhas-solicitacoes")
    public String minhasSolicitacoes() {

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_FINANCEIRO"))
            return "redirect:/dashboard-funcionario";

        return "telas-funcionario/minhas-solicitacoes";
    }
}