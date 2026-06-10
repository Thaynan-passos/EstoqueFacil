package com.EstoqueFacil.EstoqueFacil;

import jakarta.validation.Valid;
import model.FornecedorModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FornecedorService;

import java.util.ArrayList;
import java.util.List;


@RestController

@RequestMapping("/fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    private List<FornecedorModel> fornecedores = new ArrayList<>();

    @PostMapping
    public ResponseEntity<FornecedorModel> CriarFornecedor(@Valid @RequestBody FornecedorModel fornecedor) {

        fornecedorService.validarFornecedor(fornecedor);

        for (FornecedorModel f : fornecedores) {

            if (f.getCnpj().equals(fornecedor.getCnpj())) {
                throw new RuntimeException("Não pode existir dois fornecedores com o mesmo cnpj");
            }
        }
        fornecedores.add(fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedor);
    }

    @GetMapping
    public ResponseEntity<?> ListarFornecedores() {

        if (fornecedores == null || fornecedores.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A lista de fornecedores está vazia");
        }
        return ResponseEntity.status(HttpStatus.OK).body(fornecedores);
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> pegarFornecedor(@Valid @RequestParam String cnpj) {


        for (FornecedorModel f : fornecedores) {
            if (f.getCnpj().equals(cnpj)) {
                return ResponseEntity.status(HttpStatus.FOUND).body(f);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o fornecedor");
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarFornecedorPorCnpj(@Valid @RequestParam String cnpj, @Valid @RequestBody FornecedorModel fornecedor) {

        fornecedorService.validarFornecedor(fornecedor);

        for (FornecedorModel  f : fornecedores) {
            if (f.getCnpj().equals(cnpj)) {
                return  ResponseEntity.status(HttpStatus.OK).body(fornecedor);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível atualizar fornecedor");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> deletarFornecedor(@Valid @RequestParam String cnpj) {

        boolean remover = fornecedores.removeIf(fornecedor -> fornecedor.getCnpj().equals(cnpj));

        if (remover) {
            return ResponseEntity.status(HttpStatus.OK).body("fornecedor removido com sucesso");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível remover o fornecedor");
    }
}
