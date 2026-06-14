package com.EstoqueFacil.EstoqueFacil.controller;

import com.EstoqueFacil.EstoqueFacil.model.EnderecoModel;

import com.EstoqueFacil.EstoqueFacil.service.EnderecoService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/endereco")
public class EnderecoController {


    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<?> criarEndereco(@Valid @RequestBody EnderecoModel endereco){

        EnderecoModel enderecoNovo = enderecoService.cadastrarEndereco(endereco);
        return ResponseEntity.status(HttpStatus.OK).body(enderecoNovo);
    }

    @GetMapping
    public ResponseEntity<?> listarEnderecos(){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.buscarTodosEnderecos());
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> buscarEnderecoPorId(@Valid @RequestParam Integer idEndereco){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.buscarPorId(idEndereco));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarEnderecoPorId(@Valid @RequestParam int id, @Valid @RequestBody EnderecoModel endereco){
        return ResponseEntity.ok(enderecoService.atualizarEnderecoPorId(id, endereco));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarEnderecoPorId(@Valid @RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(enderecoService.deletarPorId(id));
    }


}
