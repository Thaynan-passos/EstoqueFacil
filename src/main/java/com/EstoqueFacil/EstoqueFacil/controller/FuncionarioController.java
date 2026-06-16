package com.EstoqueFacil.EstoqueFacil.controller;

import jakarta.validation.Valid;
import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
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
    public ResponseEntity<Funcionario> criarFuncionario(@Valid @RequestBody Funcionario funcionario) {

        Funcionario funcionarioNovo = funcionarioService.cadastrarFuncionario(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioNovo);
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>>  listarFuncionarios() {

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.buscarTodosFuncionarios());
    }

    @GetMapping("/pegar")
    public ResponseEntity<Funcionario> BuscarFuncionariosPorCpf(@Valid @RequestParam String cpf) {

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.buscarPorCpf(cpf));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Funcionario> atualizarFuncionarioPorCpf(@Valid String cpf, @Valid @RequestBody Funcionario funcionario) {

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.atualizarFuncionarioPorCpf(cpf, funcionario));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<Funcionario> deletarFuncionarioPorCpf(@Valid @RequestParam String cpf) {

        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.deletarPorCpf(cpf));
    }

}
