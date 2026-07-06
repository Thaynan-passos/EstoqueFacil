package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.repository.FuncionarioSetorRepository;
import com.EstoqueFacil.EstoqueFacil.repository.SetorRepository;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import com.EstoqueFacil.EstoqueFacil.utils.MensagemEmailFuncionarioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/cadastro")
public class CadastroFuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private MensagemEmailFuncionarioUtil emailUtil;

    @Autowired
    private SetorRepository setorRepository;

    @Autowired
    private FuncionarioSetorRepository funcionarioSetorRepository;

    // abre a tela
    @GetMapping
    public String telaCadastro(Model model) {
        model.addAttribute("setores", setorRepository.findAll());
        return "telas-gerente/cadastro-funcionario";
    }

    // salva funcionário
    @PostMapping("/funcionario")
    public String cadastrarFuncionario(
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam Cargo cargo,
            @RequestParam String telefone,
            @RequestParam String cep,
            @RequestParam String rua,
            @RequestParam (required = false) String numero,
            @RequestParam String bairro,
            @RequestParam String cidade,
            @RequestParam String estado,
            @RequestParam Integer idSetor
    ) {

        Funcionario f = new Funcionario();
        f.setNome(nome);
        f.setCpf(cpf.replaceAll("\\D", ""));
        f.setEmail(email);
        f.setSenhaHash(senha);
        f.setCargo(cargo);

        if (cargo == null) {
            throw new RuntimeException("Cargo obrigatório");
        }

        Endereco end = new Endereco();
        end.setCep(cep.replace("-", ""));
        end.setRua(rua);
        end.setNumeroCasa(numero);
        end.setBairro(bairro);
        end.setCidade(cidade);
        end.setEstado(estado);

        f.setEndereco(end);

        Telefone tel = new Telefone();
        tel.setTelefone(telefone);
        tel.setTipoTelefone("PRINCIPAL");

        f.setTelefone(List.of(tel));

        Funcionario funcionarioSalvo = funcionarioService.cadastrarFuncionario(f, senha);

        Setor setor = setorRepository.findById(idSetor)
                .orElseThrow(() -> new NoSuchElementException("Setor não encontrado."));

        FuncionarioSetor funcionarioSetor = new FuncionarioSetor();
        funcionarioSetor.setFuncionario(funcionarioSalvo);
        funcionarioSetor.setSetor(setor);
        funcionarioSetor.setId(new FuncionarioSetorId(
                funcionarioSalvo.getIdFuncionario(),
                setor.getIdSetor(),
                LocalDate.now()
        ));
        funcionarioSetorRepository.save(funcionarioSetor);

        try {
            emailUtil.enviarConfirmacao(f.getEmail(), f.getNome());
        } catch (Exception e) {
            System.err.println("Email não enviado: " + e.getMessage());
        }
        return "redirect:/cadastro";
    }
}