package com.EstoqueFacil.EstoqueFacil;


import jakarta.validation.Valid;
import model.MovimentacaoModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movimentar")
public class MovimentacaoController {

    private List<MovimentacaoModel> listaMovimentacoes = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> criarMovimentacoes(@Valid @RequestBody MovimentacaoModel movimentacoes) {

        for(MovimentacaoModel m : listaMovimentacoes) {
            if(m.getIdMovimentacao()==movimentacoes.getIdMovimentacao()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Não pode existir uma movimentação de mesmo ID");
            }
        }
        listaMovimentacoes.add(movimentacoes);
        return ResponseEntity.status(HttpStatus.CREATED).body(movimentacoes);
    }

    @GetMapping
    public ResponseEntity<?> listarMovimentacoes() {

        if(listaMovimentacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrada nenhuma movimentação");
        }
        return ResponseEntity.status(HttpStatus.OK).body(listaMovimentacoes);
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarPorMovimentacoesPorId(@Valid @RequestParam int id){

        for(MovimentacaoModel m : listaMovimentacoes) {
            if(m.getIdMovimentacao()==id) {
                return ResponseEntity.status(HttpStatus.FOUND).body(m);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível buscar a movimentação por ID");
    }

    @GetMapping("/data")
    public ResponseEntity<?> buscarPorData(@Valid @RequestParam LocalDate data){
        for(MovimentacaoModel m : listaMovimentacoes) {
            if(m.getDataMovimentacao().equals(data)) {
                return ResponseEntity.status(HttpStatus.FOUND).body(m);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível buscar a movimentação pela data");
    }

    @PutMapping("/atualizarId")
    public ResponseEntity<?> atualizarMovimentacoesPorId(@Valid @RequestParam int id,@Valid @RequestBody MovimentacaoModel movimentacoes) {
        for(MovimentacaoModel m : listaMovimentacoes) {
            if(m.getIdMovimentacao()==id) {
                return ResponseEntity.status(HttpStatus.OK).body(movimentacoes);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível atualizar a movimentação por Id");
    }

    @PutMapping("/atualizarData")
    public ResponseEntity<?> atualizarMovimentacoesPorData(@Valid @RequestParam LocalDate data, @Valid @RequestBody MovimentacaoModel movimentacoes) {
        for(MovimentacaoModel m : listaMovimentacoes) {
            if(m.getDataMovimentacao().equals(data)) {
                return ResponseEntity.status(HttpStatus.OK).body(movimentacoes);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível atualizar a movimentação por Data");
    }

    @DeleteMapping("/deletarId")
    public ResponseEntity<?> deletarMovimentacoesPorId(@Valid @RequestParam int id) {

        boolean remove =  listaMovimentacoes.removeIf(m -> m.getIdMovimentacao()==id);
        if(remove) {
            return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível remover por ID");
    }

    @DeleteMapping("/deletarData")
    public ResponseEntity<?> deletarMovimentacoesPorId(@Valid @RequestParam LocalDate data) {

        boolean remove =  listaMovimentacoes.removeIf(m -> m.getDataMovimentacao().equals(data));
        if(remove) {
            return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível remover pela data");
    }
}


