package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.FuncionarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.EstoqueFacil.EstoqueFacil.service.FuncionarioService;


import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;
    
    @PostMapping
    public ResponseEntity<FuncionarioModel> criarFuncionario(@Valid @RequestBody FuncionarioModel funcionario) {

        FuncionarioModel funcionarioNovo = funcionarioService.cadastrarFuncionario(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioNovo);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioModel>>  listarFuncionarios() {

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.buscarTodosFuncionarios());
    }

    @GetMapping("/pegar")
    public ResponseEntity<FuncionarioModel> BuscarFuncionariosPorCpf(@Valid @RequestParam String cpf) {

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.buscarPorCpf(cpf));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<FuncionarioModel> atualizarFuncionarioPorCpf(@Valid String cpf, @Valid @RequestBody FuncionarioModel funcionario) {

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.atualizarFuncionarioPorCpf(cpf, funcionario));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<FuncionarioModel> deletarFuncionarioPorCpf(@Valid @RequestParam String cpf) {

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.deletarPorCpf(cpf));
    }

}
