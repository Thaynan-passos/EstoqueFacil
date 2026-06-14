package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.RequisicaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.RequisicaoService;

import java.time.LocalDate;


public class RequisicaoController {

    @Autowired
    RequisicaoService requisicaoService;


    @PostMapping
    public ResponseEntity<?> criarListaRequisicao(@Valid @RequestBody RequisicaoModel requisicaoModel) {

    RequisicaoModel novaRequisicao = requisicaoService.cadastrarRequisicao(requisicaoModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(novaRequisicao);
    }


    @GetMapping
    public ResponseEntity<?> listarRequisicao() {

        return ResponseEntity.ok(requisicaoService.buscarTodasRequisicoes());
    }

    @GetMapping("/data")
    public ResponseEntity<?> buscarRequisicaoPorData(@Valid @RequestParam LocalDate data){


        return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.buscarPorDataRequisicao(data));
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarRequisicaoPorId(@Valid @RequestParam int id){

       return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.buscarPorId(id));
    }

    @PutMapping("/atualizarData")
    public ResponseEntity<?> atualizarRequisicaoPorData(@Valid @RequestParam LocalDate data, @Valid @RequestBody RequisicaoModel requisicao) {



        return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.atualizarPorData(data, requisicao));
    }

    @PutMapping("/atualizarId")
    public ResponseEntity<?> atualizarRequisicaoPorId(@Valid @RequestParam int id, @Valid @RequestBody RequisicaoModel requisicaoModel) {


        return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.atualizarPorId(id, requisicaoModel));
    }

    @DeleteMapping("/deletarId")
    public ResponseEntity<?> deletarMovimentacoesPorId(@Valid @RequestParam int id) {


        return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.deletarRequisicaoPorId(id));
    }

    @DeleteMapping("/deletarData")
    public ResponseEntity<?> deletarMovimentacoesPorData(@Valid @RequestParam LocalDate data) {

        return ResponseEntity.status(HttpStatus.OK).body(requisicaoService.deletarRequisicaoPorData(data));
    }

}
