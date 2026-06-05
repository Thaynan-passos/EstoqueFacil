package com.EstoqueFacil.EstoqueFacil;

import Utils.ValidadorUtils;
import jakarta.validation.Valid;
import model.FuncionarioModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private List<FuncionarioModel> funcionarios = new ArrayList<>();

    @PostMapping
    public ResponseEntity<FuncionarioModel> criarFuncionario(@Valid @RequestBody FuncionarioModel funcionario) {

        ValidadorUtils.validarCPF(funcionario.getCpf());

        for( FuncionarioModel f : funcionarios) {
            if(f.getCpf().equals(funcionario.getCpf())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(f);
            }
        }

        funcionarios.add(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }

    @GetMapping
    public ResponseEntity<?>  listarFuncionarios() {

        if(funcionarios.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A lista de funcionários está vazia");
        }
        return ResponseEntity.status(HttpStatus.OK).body(funcionarios);
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> BuscarFuncionarios(@Valid @RequestParam String cpf) {

        ValidadorUtils.validarCPF(cpf);

        for( FuncionarioModel f : funcionarios) {
            if(f.getCpf().equals(cpf)) {
                return ResponseEntity.status(HttpStatus.FOUND).body(f);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o funcionário");
    }

    @PutMapping("/atualizar")
    public Object atualizarFuncionarioPorCpf(@Valid String cpf, @Valid @RequestBody FuncionarioModel funcionario) {
        ValidadorUtils.validarCPF(cpf);

        for( FuncionarioModel f : funcionarios) {
            if(f.getCpf().equals(cpf)) {
                return ResponseEntity.status(HttpStatus.OK).body(funcionario);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível atualizar funcionário");
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarFuncionarioPorCpf(@RequestParam String cpf) {
        ValidadorUtils.validarCPF(cpf);

        boolean remover = funcionarios.removeIf(f -> f.getCpf().equals(cpf));

        if(remover) {
            return ResponseEntity.status(HttpStatus.OK).body("Funcionário removido com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível remover funcionário");
    }

}
