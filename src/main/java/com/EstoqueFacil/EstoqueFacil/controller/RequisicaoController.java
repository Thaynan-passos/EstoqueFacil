package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.Requisicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.RequisicaoService;

import java.time.LocalDate;

@RestController
@RequestMapping("/requisicao")
public class RequisicaoController {

    @Autowired
    RequisicaoService requisicaoService;


    @PostMapping
    public ResponseEntity<?> criarListaRequisicao(@Valid @RequestBody Requisicao requisicaoModel) {

    Requisicao novaRequisicao = requisicaoService.cadastrarRequisicao(requisicaoModel);
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
