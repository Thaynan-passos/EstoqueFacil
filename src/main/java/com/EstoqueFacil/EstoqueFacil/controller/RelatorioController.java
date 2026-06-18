package com.EstoqueFacil.EstoqueFacil.controller;


import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.Relatorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.RelatorioService;

import java.time.LocalDate;


@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @PostMapping
    public ResponseEntity<?> criarRelatorio(@Valid @RequestBody Relatorio relatorioModel) {


        Relatorio relatorioNovo = relatorioService.cadastrarRelatorio(relatorioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(relatorioNovo);
    }

    @GetMapping("/relatorio-financeiro/pdf")
    public ResponseEntity<byte[]> baixarRelatorioPdf() {

        byte[] pdf = relatorioService.gerarPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "relatorio-financeiro.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdf);
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
    public ResponseEntity<?> atualizarRelatorio(@Valid @RequestParam LocalDate dataEmissao,@Valid @RequestBody Relatorio relatorioModel) {

        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.atualizarPorDataEmitida(dataEmissao,relatorioModel));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarRelatorio(@RequestParam int idRelatorio) {

        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.deletarLotePorId(idRelatorio));
    }
}
