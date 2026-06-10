package com.EstoqueFacil.EstoqueFacil;


import jakarta.validation.Valid;
import model.RelatorioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RelatorioService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    private List<RelatorioModel> listaRelatorio = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> criarRelatorio(@Valid @RequestBody RelatorioModel relatorioModel) {

        relatorioService.validarRelatorio(relatorioModel);

        for (RelatorioModel relatorio : listaRelatorio) {
            if(relatorio.getIdRelatorio() == relatorioModel.getIdRelatorio()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Não pode existir um relatório de mesmo ID");
            }
        }
        listaRelatorio.add(relatorioModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(relatorioModel);
    }

    @GetMapping
    public ResponseEntity<?>  listarRelatorio() {
        if(listaRelatorio.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum relatorio foi encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaRelatorio);
    }

    @GetMapping("/pegar")
    public ResponseEntity<?>  pegarRelatorio(@Valid @RequestParam LocalDate dataEmissao) {

        relatorioService.validarDataEmissao(dataEmissao);

        for(RelatorioModel relatorio : listaRelatorio) {
            if(relatorio.getDataEmissao().equals(dataEmissao)) {
                return ResponseEntity.status(HttpStatus.FOUND).body(relatorio);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível buscar o relatório pela data");
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarRelatorio(@Valid @RequestParam LocalDate dataEmissao,@Valid @RequestBody RelatorioModel relatorioModel) {

        relatorioService.validarRelatorio(relatorioModel);

        for(RelatorioModel r : listaRelatorio) {
            if(r.getDataEmissao().equals(dataEmissao)) {
                return ResponseEntity.status(HttpStatus.OK).body(relatorioModel);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível atualizar a movimentação por Data");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarRelatorio(@RequestParam LocalDate dataEmissao) {

        relatorioService.validarDataEmissao(dataEmissao);

        boolean remover = listaRelatorio.removeIf(r -> r.getDataEmissao().equals(dataEmissao));
        if(remover) {
            return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível remover o relatório");
    }
}
