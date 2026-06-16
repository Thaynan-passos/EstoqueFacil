package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.Fornecedor;
import com.EstoqueFacil.EstoqueFacil.service.FornecedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @PostMapping
    public ResponseEntity<Fornecedor> CriarFornecedor(@Valid @RequestBody Fornecedor fornecedor) {

        Fornecedor fornecedorSalvo = fornecedorService.cadastrarFornecedor(fornecedor);

        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> ListarFornecedores() {

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarTodosFornecedores());
    }

    @GetMapping("/pegar")
    public ResponseEntity<Fornecedor> pegarFornecedorPorCnpj(@Valid @RequestParam String cnpj) {

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarPorCnpj(cnpj));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Fornecedor> atualizarFornecedorPorCnpj(@Valid @RequestParam String cnpj, @Valid @RequestBody Fornecedor fornecedor) {

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.atualizarFornecedorPorCnpj(cnpj, fornecedor));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Fornecedor> deletarFornecedor(@Valid @RequestParam String cnpj) {

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.deletarPorCnpj(cnpj));
    }
}
