package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;

import com.EstoqueFacil.EstoqueFacil.model.Lote;
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
    public ResponseEntity<Lote> criarLote(@Valid @RequestBody Lote lote) {

        Lote novoLote = loteService.cadastrarLote(lote);

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
    public Object atualizarLotePorNumero(@Valid @RequestParam int numeroLote,@Valid @RequestBody Lote lote) {

        return ResponseEntity.status(HttpStatus.OK).body(loteService.atualizarLotePorNumero(numeroLote,lote));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarLotePorNumero(@RequestParam int numeroLote) {

        return ResponseEntity.status(HttpStatus.OK).body(loteService.deletarLotePorNumero(numeroLote));

    }

}
