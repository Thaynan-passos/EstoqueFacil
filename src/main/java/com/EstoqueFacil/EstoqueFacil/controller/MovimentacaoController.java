package com.EstoqueFacil.EstoqueFacil.controller;


import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.Movimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.MovimentacaoService;

import java.time.LocalDate;


@RestController
@RequestMapping("/movimentar")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<?> criarMovimentacoes(@Valid @RequestBody Movimentacao movimentacoes) {

        Movimentacao novaMovimentacao = movimentacaoService.cadastrarMovimentacao(movimentacoes);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaMovimentacao);
    }

    @GetMapping
    public ResponseEntity<?> listarMovimentacoes() {

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.buscarTodasMovimentacoes());
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarPorMovimentacoesPorId(@Valid @RequestParam int id) {

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.buscarPorId(id));
    }

    @GetMapping("/data")
    public ResponseEntity<?> buscarPorData(@Valid @RequestParam LocalDate data) {

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.buscarPorDataMovimentacao(data));
    }

    @PutMapping("/atualizarId")
    public ResponseEntity<?> atualizarMovimentacoesPorId(@Valid @RequestParam int id, @Valid @RequestBody Movimentacao movimentacoes) {

        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.atualizarPorId(id, movimentacoes));
    }

    @PutMapping("/atualizarData")
    public ResponseEntity<?> atualizarMovimentacoesPorData(@Valid @RequestParam LocalDate data, @Valid @RequestBody Movimentacao movimentacoes) {


        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.atualizarPorData(data, movimentacoes));
    }

    @DeleteMapping("/deletarId")
    public ResponseEntity<?> deletarMovimentacoesPorId(@Valid @RequestParam int id) {


        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.deletarMovimentacaoPorId(id));
    }
}
