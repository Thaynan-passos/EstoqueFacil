package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.SetorModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

public class SetorController {

    private List<SetorModel> listaSetor = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> listarSetor(@Valid @RequestBody  SetorModel setor){

        for( SetorModel set : listaSetor){
            if(set.getIdSetor()==setor.getIdSetor()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não pode existir um setor de mesmo Id");
            }
        }
        listaSetor.add(setor);
        return  ResponseEntity.status(HttpStatus.CREATED).body(listaSetor);
    }

    @GetMapping
    public ResponseEntity<?> listarProduto(){

        if(listaSetor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum relatorio foi encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaSetor);
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> buscarSetorPorId(@Valid @RequestParam Integer idSetor){

        for( SetorModel set : listaSetor){
            if(set.getIdSetor()==idSetor){
                return ResponseEntity.status(HttpStatus.FOUND).body(set);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum setor foi encontrado.");
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarSetorPorId(@Valid @RequestParam int idSetor,@Valid @RequestBody SetorModel setor){

        for( SetorModel set : listaSetor){
            if(set.getIdSetor()==idSetor){
                return  ResponseEntity.status(HttpStatus.OK).body(set);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível atualizar o setor por ID");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarSetorPorId(@RequestParam Integer idSetor){

        boolean remover = listaSetor.removeIf(set -> set.getIdSetor()==idSetor);

        if(remover){
            return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível remover o setor por ID");
    }
}
