package com.EstoqueFacil.EstoqueFacil.controller;


import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.RelatorioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.RelatorioService;

import java.time.LocalDate;


@RestController
@RequestMapping
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @PostMapping
    public ResponseEntity<?> criarRelatorio(@Valid @RequestBody RelatorioModel relatorioModel) {


        RelatorioModel relatorioNovo = relatorioService.cadastrarRelatorio(relatorioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(relatorioNovo);
    }

    @GetMapping
    public ResponseEntity<?>  listarRelatorio() {

        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.buscarTodosRelatorios());
    }

    @GetMapping("/pegar")
    public ResponseEntity<?>  pegarRelatorio(@Valid @RequestParam LocalDate dataEmissao) {


        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.buscarPorDataEmissao(dataEmissao));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarRelatorio(@Valid @RequestParam LocalDate dataEmissao,@Valid @RequestBody RelatorioModel relatorioModel) {

        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.atualizarPorDataEmitida(dataEmissao,relatorioModel));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarRelatorio(@RequestParam int idRelatorio) {

        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.deletarLotePorId(idRelatorio));
    }
}
