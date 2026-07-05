package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
import com.EstoqueFacil.EstoqueFacil.model.FuncionarioSetor;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.Requisicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.RequisicaoService;

import java.time.LocalDate;

@RestController
@RequestMapping("/requisicao")
public class RequisicaoController {

    @Autowired
    RequisicaoService requisicaoService;
    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Requisicao requisicao) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String cpf = authentication.getName();

        Funcionario funcionario = funcionarioService.buscarPorCpf(cpf);

        requisicaoService.cadastrarRequisicao(requisicao, funcionario);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/minhas")
    public ResponseEntity<?> listarRequisicao() {

        return ResponseEntity.ok(requisicaoService.buscarTodasRequisicoes());
    }

    @GetMapping("/historico")
    public ResponseEntity<?> listarHistorico() {
        return ResponseEntity.ok(requisicaoService.buscarHistorico());
    }

    @GetMapping("/data")
    public ResponseEntity<?> buscarRequisicaoPorData(@Valid @RequestParam LocalDate data){


        return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.buscarPorDataRequisicao(data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarRequisicaoPorId(@Valid @PathVariable int id){

       return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarRequisicaoPorId(@Valid @PathVariable  int id, @Valid @RequestBody Requisicao requisicaoModel) {


        return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.atualizarPorId(id, requisicaoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarMovimentacoesPorId(@Valid @PathVariable int id) {


        return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.deletarRequisicaoPorId(id));
    }

    @DeleteMapping("/data")
    public ResponseEntity<?> deletarMovimentacoesPorData(@Valid @RequestParam LocalDate data) {

        return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.deletarRequisicaoPorData(data));
    }

}
