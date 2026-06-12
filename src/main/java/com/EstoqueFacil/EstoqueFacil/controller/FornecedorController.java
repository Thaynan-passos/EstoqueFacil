package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.FornecedorModel;
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
    public ResponseEntity<FornecedorModel> CriarFornecedor(@Valid @RequestBody FornecedorModel fornecedor) {

        FornecedorModel fornecedorSalvo = fornecedorService.cadastrarFornecedor(fornecedor);

        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorSalvo);
    }

    @GetMapping
    public ResponseEntity<List<FornecedorModel>> ListarFornecedores() {

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarTodosFornecedores());
    }

    @GetMapping("/pegar")
    public ResponseEntity<FornecedorModel> pegarFornecedorPorCnpj(@Valid @RequestParam String cnpj) {

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarPorCnpj(cnpj));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<FornecedorModel> atualizarFornecedorPorCnpj(@Valid @RequestParam String cnpj, @Valid @RequestBody FornecedorModel fornecedor) {

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.atualizarFornecedorPorCnpj(cnpj, fornecedor));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<FornecedorModel> deletarFornecedor(@Valid @RequestParam String cnpj) {

        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.deletarPorCnpj(cnpj));
    }
}
