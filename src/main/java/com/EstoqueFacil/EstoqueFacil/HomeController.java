package com.EstoqueFacil.EstoqueFacil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // Isso preenche o ${message} que está no seu HTML
        model.addAttribute("message", "Olá mundo do Estoque Fácil!");

        // Isso diz ao Spring para procurar o arquivo "index.html" na pasta templates
        return "index";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }

    @GetMapping("/controle-estoque")
    public String controleEstoque() {
        return "telas-almoxarife/controle-estoque";
    }

    @GetMapping("/relatorio-financeiro")
    public String relatorioFinanceiro() {
        return "telas-gerente/relatorio-financeiro";
    }
}