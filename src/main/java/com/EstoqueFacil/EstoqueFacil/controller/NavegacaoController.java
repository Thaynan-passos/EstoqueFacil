package com.EstoqueFacil.EstoqueFacil.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavegacaoController {

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/dashboard-gerente")
    public String dashboardGerente() {
        return "telas-gerente/analise-gerente";
    }

    @GetMapping("/cadastrar-produto")
    public String cadastrarProduto() {
        return "telas-gerente/cadastrar-produto";
    }

    @GetMapping("/controle-estoque")
    public String controleEstoque() {
        return "telas-almoxarife/controle-estoque";
    }

    @GetMapping("/entrada-materiais")
    public String entradaMateriais() {
        return "telas-almoxarife/entrada-materiais";
    }

    @GetMapping("/requisicoes")
    public String requisicoes() {
        return "telas-funcionario/requisicoes";
    }

    @GetMapping("/historico-requisicoes")
    public String historicoRequisicoes() {
        return "telas-funcionario/historico-requisicoes";
    }

    @GetMapping("/relatorio-financeiro")
    public String relatorioFinanceiro() {
        return "telas-gerente/relatorio-financeiro";
    }

    @GetMapping("/dashboard-almoxarife")
    public String dashboardAlmoxarife() {
        return "telas-almoxarife/dashboard-almoxarife";
    }

    @GetMapping("analise-gerente")
    public String analiseGerente() {
        return "telas-gerente/analise-gerente";
    }
    
    @GetMapping("/saidas-materiais")
    public String saidaMateriais() {
        return "telas-almoxarife/saida-materiais";
    }

    @GetMapping("/inventario")
    public String inventario() {
        return "telas-almoxarife/inventario";
    }

    @GetMapping("/dashboard-funcionario")
    public String dashboardFuncionario() {
        return "telas-funcionario/dashboard-funcionario";
    }

    @GetMapping("/minhas-solicitacoes")
    public String minhasSolicitacoes() {
        return "telas-funcionario/minhas-solicitacoes";
    }
}