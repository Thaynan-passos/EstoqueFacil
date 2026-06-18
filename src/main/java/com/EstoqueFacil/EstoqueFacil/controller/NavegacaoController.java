package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.service.ProdutoService;
import com.EstoqueFacil.EstoqueFacil.service.RelatorioService;
import com.EstoqueFacil.EstoqueFacil.service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavegacaoController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    RelatorioService relatorioService;

    @Autowired
    RequisicaoService requisicaoService;

    // =========================
    // AUTH UTIL
    // =========================
    private Authentication getAuth() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private boolean isLogado() {
        Authentication auth = getAuth();
        return auth != null &&
                auth.isAuthenticated() &&
                !auth.getPrincipal().equals("anonymousUser");
    }

    private boolean hasRole(String role) {
        Authentication auth = getAuth();

        if (auth == null) return false;

        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(role));
    }

    // =========================
    // DASHBOARDS
    // =========================
    @GetMapping("/dashboard-gerente")
    public String dashboardGerente() {
        if (!isLogado()) return "redirect:/login";
        return "telas-gerente/dashboard-gerente";
    }

    @GetMapping("/dashboard-almoxarife")
    public String dashboardAlmoxarife() {
        if (!isLogado()) return "redirect:/login";
        return "telas-almoxarife/dashboard-almoxarife";
    }

    @GetMapping("/dashboard-funcionario")
    public String dashboardFuncionario() {
        if (!isLogado()) return "redirect:/login";
        return "telas-funcionario/dashboard-funcionario";
    }

    // =========================
    // GERENTE
    // =========================
    @GetMapping("/cadastrar-produto")
    public String cadastrarProduto() {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        return "telas-gerente/cadastrar-produto";
    }

    @GetMapping("/cadastro-funcionario")
    public String cadastroFuncionario() {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        return "telas-gerente/cadastro-funcionario";
    }

    @GetMapping("/relatorio-financeiro")
    public String relatorioFinanceiro(Model model) {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        model.addAttribute("relatorios", relatorioService.buscarTodosRelatorios());
        return "telas-gerente/relatorio-financeiro";
    }

    @GetMapping("/analise-gerente")
    public String analiseGerente(Model model) {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        model.addAttribute("pendentes", requisicaoService.buscarPendentes());
        model.addAttribute("historico", requisicaoService.buscarTodasRequisicoes());

        return "telas-gerente/analise-gerente";
    }

    // =========================
    // ALMOXARIFE
    // =========================
    @GetMapping("/controle-estoque")
    public String controleEstoque() {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_ALMOXARIFE")) return "redirect:/dashboard-almoxarife";

        return "telas-almoxarife/controle-estoque";
    }

    @GetMapping("/entrada-materiais")
    public String entradaMateriais() {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_ALMOXARIFE")) return "redirect:/dashboard-almoxarife";

        return "telas-almoxarife/entrada-materiais";
    }

    @GetMapping("/saidas-materiais")
    public String saidaMateriais() {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_ALMOXARIFE")) return "redirect:/dashboard-almoxarife";

        return "telas-almoxarife/saida-materiais";
    }

    @GetMapping("/inventario")
    public String inventario() {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_ALMOXARIFE")) return "redirect:/dashboard-almoxarife";

        return "telas-almoxarife/inventario";
    }

    // =========================
    // FUNCIONÁRIO
    // =========================
    @GetMapping("/requisicoes")
    public String requisicoes(Model model) {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_FUNCIONARIO")) return "redirect:/dashboard-funcionario";

        model.addAttribute("produtos", produtoService.buscarTodosProdutos());
        return "telas-funcionario/requisicoes";
    }

    @GetMapping("/historico-requisicoes")
    public String historicoRequisicoes() {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_FUNCIONARIO")) return "redirect:/dashboard-funcionario";

        return "telas-funcionario/historico-requisicoes";
    }

    @GetMapping("/minhas-solicitacoes")
    public String minhasSolicitacoes() {

        if (!isLogado()) return "redirect:/login";
        if (!hasRole("ROLE_FUNCIONARIO")) return "redirect:/dashboard-funcionario";

        return "telas-funcionario/minhas-solicitacoes";
    }
}