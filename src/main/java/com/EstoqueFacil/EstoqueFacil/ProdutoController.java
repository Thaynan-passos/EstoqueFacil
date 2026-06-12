package com.EstoqueFacil.EstoqueFacil;

import jakarta.validation.Valid;
import model.ProdutoModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProdutoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    private List<ProdutoModel> listaProduto = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> inserirProduto(@Valid @RequestBody ProdutoModel produto){

        produtoService.validarProduto(produto);

        for(ProdutoModel p : listaProduto){
            if(p.getNomeProduto().equals(produto.getNomeProduto())){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Não pode existir um produto de mesmo nome");
            }
        }
        listaProduto.add(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public ResponseEntity<?> listarProduto(){
        if(listaProduto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaProduto);
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> pegarProdutoPorNome(@Valid @RequestParam String nome){
        for(ProdutoModel p : listaProduto){
            if(p.getNomeProduto().equalsIgnoreCase(nome)){
                return ResponseEntity.status(HttpStatus.FOUND).body(p);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum produto encontrado.");
    }

    @PutMapping("/atualizar")
    public  ResponseEntity<?> atualizarProdutoPorNome(@Valid String nome, @Valid @RequestBody ProdutoModel produto){

        produtoService.validarProduto(produto);

        for(ProdutoModel p : listaProduto){
            if(p.getNomeProduto().equalsIgnoreCase(nome)){
                return ResponseEntity.status(HttpStatus.OK).body(produto);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto foi atualizado.");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarProdutoPorNome(@Valid @RequestParam String nome){

        boolean remove = listaProduto.removeIf(p -> p.getNomeProduto().equalsIgnoreCase(nome));

        if(remove){
            return ResponseEntity.status(HttpStatus.OK).body("Produto removido com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nenhum produto foi removido.");
    }
}
