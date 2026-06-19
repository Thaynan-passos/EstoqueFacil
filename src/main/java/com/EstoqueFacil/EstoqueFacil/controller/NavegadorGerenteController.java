package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.service.FornecedorService;
import com.EstoqueFacil.EstoqueFacil.service.ProdutoService;
import com.EstoqueFacil.EstoqueFacil.service.RelatorioService;
import com.EstoqueFacil.EstoqueFacil.service.RequisicaoService;
import com.EstoqueFacil.EstoqueFacil.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavegadorGerenteController {

    @Autowired
    RelatorioService relatorioService;

    @Autowired
    RequisicaoService requisicaoService;

    @Autowired
    FornecedorService fornecedorService;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/dashboard-gerente")
    public String dashboardGerente(Model model) {
        if (!authUtil.isLogado()) return "redirect:/login";

        model.addAttribute("totalProdutos",
                    produtoService.buscarTodosProdutos().size());

        model.addAttribute("totalRequisicoes",
                    requisicaoService.buscarTodasRequisicoes().size());

        model.addAttribute("totalFuncionarios", 0);
        model.addAttribute("estoqueBaixo", 0);

        model.addAttribute("atividades", java.util.List.of(
                            "Sistema iniciado",
                            "Dashboard carregado"));

        return "telas-gerente/dashboard-gerente";
    }

    @GetMapping("/cadastrar-fornecedor")
    public String cadastrarFornecedor(Model model) {

        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        model.addAttribute("fornecedores",
                fornecedorService.buscarTodosFornecedores());

        return "telas-gerente/cadastrar-fornecedor";
    }

    @GetMapping("/cadastro-funcionario")
    public String cadastroFuncionario() {

        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        return "telas-gerente/cadastro-funcionario";
    }
    @GetMapping("/relatorio-financeiro")
    public String relatorioFinanceiro(Model model) {

        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        model.addAttribute("relatorios", relatorioService.buscarTodosRelatorios());
        return "telas-gerente/relatorio-financeiro";
    }

    @GetMapping("/analise-gerente")
    public String analiseGerente(Model model) {

        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        model.addAttribute("pendentes", requisicaoService.buscarPendentes());
        model.addAttribute("historico", requisicaoService.buscarHistorico());

        return "telas-gerente/analise-gerente";
    }
}
