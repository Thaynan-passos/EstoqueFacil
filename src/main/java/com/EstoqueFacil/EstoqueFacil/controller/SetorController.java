package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.service.SetorService;
import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.Setor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/setor")
public class SetorController {

    @Autowired
    SetorService setorService;

    @PostMapping
    public ResponseEntity<?> listarSetor(@Valid @RequestBody Setor setor){

        Setor setorNovo = setorService.cadastrarSetor(setor) ;
        return  ResponseEntity.status(HttpStatus.CREATED).body(setorNovo);
    }

    @GetMapping
    public ResponseEntity<?> listarProduto(){

        return ResponseEntity.status(HttpStatus.OK).body(setorService.buscarTodosSetores());
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> buscarSetorPorId(@Valid @RequestParam int idSetor){

        return ResponseEntity.status(HttpStatus.OK).body(setorService.buscarPorId(idSetor));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarSetorPorId(@Valid @RequestParam int idSetor,@Valid @RequestBody Setor setor){

        return ResponseEntity.status(HttpStatus.OK).body(setorService.atualizarSetorPorId(idSetor,setor));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarSetorPorId(@RequestParam Integer idSetor){

        return ResponseEntity.status(HttpStatus.OK).body(setorService.deletarSetorPorId(idSetor));
    }
}
