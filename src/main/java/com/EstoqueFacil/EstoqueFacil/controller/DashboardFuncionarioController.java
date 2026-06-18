package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.Status;
import com.EstoqueFacil.EstoqueFacil.service.RequisicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/dashboard/funcionario")
public class DashboardFuncionarioController {

    @Autowired
    private RequisicaoService requisicaoService;

    @GetMapping("/funcionario")
    public String dashboardFuncionario(Model model) {

        model.addAttribute("total",
                requisicaoService.totalRequisicoes());

        model.addAttribute("pendentes",
                requisicaoService.totalPorStatus(Status.PENDENTE));

        model.addAttribute("aprovadas",
                requisicaoService.totalPorStatus(Status.APROVADO));

        model.addAttribute("recusadas",
                requisicaoService.totalPorStatus(Status.NEGADO));

        model.addAttribute("ultimas",
                requisicaoService.buscarUltimas(5));
        return "telas-funcionario/dashboard-funcionario";
    }
}