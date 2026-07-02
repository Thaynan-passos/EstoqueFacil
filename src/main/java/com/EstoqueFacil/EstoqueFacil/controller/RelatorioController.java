package com.EstoqueFacil.EstoqueFacil.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EstoqueFacil.EstoqueFacil.model.Relatorio;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;
import com.EstoqueFacil.EstoqueFacil.service.RelatorioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<?> criarRelatorio(@Valid @RequestBody Relatorio relatorioModel) {

        Relatorio relatorioNovo = relatorioService.cadastrarRelatorio(relatorioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(relatorioNovo);
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> gerarPdf() {

        byte[] pdf = relatorioService.gerarPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(
                ContentDisposition.attachment()
                        .filename("relatorio-financeiro.pdf")
                        .build());

        return ResponseEntity.ok()
                .headers(headers)
                .body(pdf);
    }

    @GetMapping
    public ResponseEntity<?> listarRelatorio() {

        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.buscarTodosRelatorios());
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> pegarRelatorio(@Valid @RequestParam LocalDate dataEmissao) {

        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.buscarPorDataEmissao(dataEmissao));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarRelatorio(@Valid @RequestParam LocalDate dataEmissao,
            @Valid @RequestBody Relatorio relatorioModel) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(relatorioService.atualizarPorDataEmitida(dataEmissao, relatorioModel));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarRelatorio(@RequestParam int idRelatorio) {

        return ResponseEntity.status(HttpStatus.OK).body(relatorioService.deletarRelatorioPorId(idRelatorio));
    }
}
