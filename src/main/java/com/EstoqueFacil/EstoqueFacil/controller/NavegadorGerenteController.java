package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
import com.EstoqueFacil.EstoqueFacil.model.Relatorio;
import com.EstoqueFacil.EstoqueFacil.model.Status;
import com.EstoqueFacil.EstoqueFacil.repository.FuncionarioRepository;
import com.EstoqueFacil.EstoqueFacil.service.ProdutoService;
import com.EstoqueFacil.EstoqueFacil.service.RelatorioService;
import com.EstoqueFacil.EstoqueFacil.service.RequisicaoService;
import com.EstoqueFacil.EstoqueFacil.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import com.EstoqueFacil.EstoqueFacil.repository.SetorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import exceptions.ErroDePreenchimentoInvalidoException;

@Controller
public class NavegadorGerenteController {

    @Autowired
    RelatorioService relatorioService;

    @Autowired
    RequisicaoService requisicaoService;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping("/dashboard-gerente")
    public String dashboardGerente(Model model) {
        if (!authUtil.isLogado()) return "redirect:/login";

        model.addAttribute("totalProdutos",
                produtoService.buscarTodosProdutos().size());

        model.addAttribute("totalRequisicoes",
                requisicaoService.buscarTodasRequisicoes().size());

        model.addAttribute("totalFuncionarios",
                funcionarioService.buscarTodosFuncionarios().size());
        model.addAttribute("estoqueBaixo", 0);

        model.addAttribute("atividades", java.util.List.of(
                "Sistema iniciado",
                "Dashboard carregado"));

        return "telas-gerente/dashboard-gerente";
    }

    @GetMapping("/cadastro-funcionario")
    public String cadastroFuncionario(Model model) {

        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        model.addAttribute("setores", setorRepository.findAll());

        return "telas-gerente/cadastro-funcionario";
    }


    @GetMapping("/relatorio-financeiro")
    public String relatorioFinanceiro(Model model) {

        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        model.addAttribute("relatorios", relatorioService.buscarTodosRelatorios());
        return "telas-gerente/relatorio-financeiro";
    }

    @PostMapping("/relatorio-financeiro")
    public String criarRelatorio(@ModelAttribute Relatorio relatorio,
                                 RedirectAttributes redirectAttributes) {

        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        try {
            String cpfLogado = authUtil.getCpfLogado();
            Funcionario gerente = funcionarioRepository.findByCpf(cpfLogado)
                    .orElseThrow(() -> new NoSuchElementException("Funcionário logado não foi encontrado."));

            // A emissão é sempre "hoje" (é o que o RelatorioService exige) e o
            // emissor é sempre o gerente logado — nada disso vem do formulário.
            relatorio.setDataEmissao(LocalDate.now());
            relatorio.setFuncionario(gerente);

            relatorioService.cadastrarRelatorio(relatorio);

            redirectAttributes.addFlashAttribute("sucesso", "Relatório salvo com sucesso.");
        } catch (ErroDePreenchimentoInvalidoException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro",
                    "Não foi possível salvar o relatório. Confira as datas e os valores informados.");
        }

        return "redirect:/relatorio-financeiro";
    }

    @GetMapping("/analise-gerente")
    public String analiseGerente(Model model) {

        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";

        model.addAttribute("pendentes", requisicaoService.buscarPendentes());
        model.addAttribute("historico", requisicaoService.buscarHistorico());

        return "telas-gerente/analise-gerente";
    }

    @GetMapping("/requisicao/aprovar/{id}")
    public String aprovarRequisicao(@PathVariable int id) {
        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";
        requisicaoService.atualizarStatus(id, Status.APROVADO);
        return "redirect:/analise-gerente";
    }

    @GetMapping("/requisicao/rejeitar/{id}")
    public String rejeitarRequisicao(@PathVariable int id) {
        if (!authUtil.isLogado()) return "redirect:/login";
        if (!authUtil.hasRole("ROLE_GERENTE")) return "redirect:/dashboard-gerente";
        requisicaoService.atualizarStatus(id, Status.NEGADO);
        return "redirect:/analise-gerente";
    }

    @GetMapping("/desativar-funcionario")
    public String telaDesativarFuncionario(Model model) {

        if (!authUtil.isLogado())
            return "redirect:/login";

        if (!authUtil.hasRole("ROLE_GERENTE"))
            return "redirect:/dashboard-gerente";

        model.addAttribute("funcionarios", funcionarioRepository.findAll());

        return "telas-gerente/desativar-funcionario";
    }

    @PostMapping("/desativar-funcionario/{id}")
    public String alterarStatus(@PathVariable Integer id){

        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Funcionário não encontrado."));

        funcionario.setAtivo(!funcionario.isAtivo());

        funcionarioRepository.save(funcionario);

        return "redirect:/desativar-funcionario";
    }
}