package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;

import com.EstoqueFacil.EstoqueFacil.model.LoteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.LoteService;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lote")
public class LoteController {

    @Autowired
    private LoteService loteService;

    private List<LoteModel> listaLotes = new ArrayList<>();

    @PostMapping
    public ResponseEntity<LoteModel> criarLote(@Valid @RequestBody LoteModel lote) {

        loteService.validarLote(lote);

        for( LoteModel l : listaLotes ) {
            if(l.getNumeroLote() == lote.getNumeroLote()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(l);
            }
        }

        listaLotes.add(lote);
        return ResponseEntity.status(HttpStatus.CREATED).body(lote);
    }

    @GetMapping
    public ResponseEntity<?> buscarListaLotes() {

        if(listaLotes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A lista está vazia");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaLotes);
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> buscarListaLotesPorNumero(@Valid @RequestParam int numeroLote) {


        for(LoteModel l : listaLotes) {
            if(l.getNumeroLote() == numeroLote) {
                return ResponseEntity.status(HttpStatus.FOUND).body(l);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("O lote que você procura não foi achado");
    }

    @PutMapping("/atualizar")
    public Object atualizarLotePorNumero(@Valid @RequestParam int numeroLote,@Valid @RequestBody LoteModel lote) {

        loteService.validarLote(lote);

        for(LoteModel l : listaLotes) {
            if(l.getNumeroLote() == numeroLote) {
                l.setNumeroLote(numeroLote);
                l.setQuantidade(lote.getQuantidade());
                l.setDataValidade(lote.getDataValidade());
                return ResponseEntity.status(HttpStatus.OK).body(lote);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível atualizar o lote");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarLotePorNumero(@RequestParam int numeroLote) {



        boolean remover = listaLotes.removeIf(l -> l.getNumeroLote() == numeroLote);

        if(remover) {
            return ResponseEntity.status(HttpStatus.OK).body("O Lote foi removido com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível remover o lote");

    }

}
