package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;

import com.EstoqueFacil.EstoqueFacil.model.LoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.LoteService;



@RestController
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    private LoteService loteService;


    @PostMapping
    public ResponseEntity<LoteModel> criarLote(@Valid @RequestBody LoteModel lote) {

        LoteModel novoLote = loteService.cadastrarLote(lote);

        return ResponseEntity.status(HttpStatus.CREATED).body(novoLote);
    }

    @GetMapping
    public ResponseEntity<?> buscarListaLotes() {



        return ResponseEntity.status(HttpStatus.OK).body(loteService.buscarTodosLotes());
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> buscarListaLotesPorNumero(@Valid @RequestParam int numeroLote) {


        return ResponseEntity.status(HttpStatus.OK).body(loteService.buscarPorNumeroLote(numeroLote));
    }

    @PutMapping("/atualizar")
    public Object atualizarLotePorNumero(@Valid @RequestParam int numeroLote,@Valid @RequestBody LoteModel lote) {

        return ResponseEntity.status(HttpStatus.OK).body(loteService.atualizarLotePorNumero(numeroLote,lote));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarLotePorNumero(@RequestParam int numeroLote) {

        return ResponseEntity.status(HttpStatus.OK).body(loteService.deletarLotePorNumero(numeroLote));

    }

}
