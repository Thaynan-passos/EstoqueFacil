package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.ProdutoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.ProdutoService;



@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping
    public ResponseEntity<?> inserirProduto(@Valid @RequestBody ProdutoModel produto){

        ProdutoModel produtoNovo =  produtoService.cadastrarProduto(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoNovo);
    }

    @GetMapping
    public ResponseEntity<?> listarProduto(){

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarTodosProdutos());
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> pegarProdutoPorNome(@Valid @RequestParam String nome){

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarProdutosPorNome(nome));
    }

    @PutMapping("/atualizar")
    public  ResponseEntity<?> atualizarProdutoPorNome(@Valid String nome, @Valid @RequestBody ProdutoModel produto){


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(produtoService.atualizarProdutosPorNome(nome, produto));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarProdutoPorNome(@Valid @RequestParam String nome){

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.deletarProdutoPorNome(nome));
    }
}
