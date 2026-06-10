package com.EstoqueFacil.EstoqueFacil;

import jakarta.validation.Valid;
import model.MovimentacaoModel;
import model.RequisicaoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.RequisicaoService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RequisicaoController {


    @Autowired
    RequisicaoService requisicaoService;
    private List<RequisicaoModel> listaRequisicao = new ArrayList<>();

    @PostMapping
    public ResponseEntity<?> criarListaRequisicao(@Valid @RequestBody RequisicaoModel requisicaoModel) {

    requisicaoService.validarRequisicao(requisicaoModel);

    for(RequisicaoModel requisicao: listaRequisicao){
        if(requisicao.getIdRequisicao() == requisicaoModel.getIdRequisicao()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Não pode existir uma requisição de mesmo ID");
        }
    }
    listaRequisicao.add(requisicaoModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(listaRequisicao);
    }


    @GetMapping
    public ResponseEntity<?> listarRequisicao() {
        if(listaRequisicao.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listaRequisicao);
    }

    @GetMapping("/data")
    public ResponseEntity<?> buscarRequisicaoPorData(@Valid @RequestParam LocalDate data){

        requisicaoService.validarDataRequisicao(data);

        for(RequisicaoModel requisicao: listaRequisicao){
            if(requisicao.getDataRequisicao().equals(data)){
                return ResponseEntity.ok(requisicao);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id")
    public ResponseEntity<?> buscarRequisicaoPorId(@Valid @RequestParam int id){

        for(RequisicaoModel requisicao: listaRequisicao){
            if(requisicao.getIdRequisicao() == id){
                return ResponseEntity.ok(requisicao);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/atualizarData")
    public ResponseEntity<?> atualizarRequisicaoPorData(@Valid @RequestParam LocalDate data, @Valid @RequestBody RequisicaoModel requisicao) {


        requisicaoService.validarRequisicao(requisicao);

        for(RequisicaoModel requisicaoModel : listaRequisicao) {
            if(requisicaoModel.getDataRequisicao().equals(data)) {
                return ResponseEntity.status(HttpStatus.OK).body(requisicao);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível atualizar a movimentação por Data");
    }

    @PutMapping("/atualizarId")
    public ResponseEntity<?> atualizarRequisicaoPorId(@Valid @RequestParam int id, @Valid @RequestBody RequisicaoModel requisicaoModel) {


        requisicaoService.validarRequisicao(requisicaoModel);

        for(RequisicaoModel r : listaRequisicao) {
            if(r.getIdRequisicao() == id) {
                return ResponseEntity.status(HttpStatus.OK).body(requisicaoModel);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível atualizar a movimentação por ID");
    }

    @DeleteMapping("/deletarId")
    public ResponseEntity<?> deletarMovimentacoesPorId(@Valid @RequestParam int id) {

        boolean remove =  listaRequisicao.removeIf(r -> r.getIdRequisicao()==id);
        if(remove) {
            return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível remover por ID");
    }

    @DeleteMapping("/deletarData")
    public ResponseEntity<?> deletarMovimentacoesPorId(@Valid @RequestParam LocalDate data) {


        requisicaoService.validarDataRequisicao(data);

        boolean remove =  listaRequisicao.removeIf(r-> r.getDataRequisicao().equals(data));
        if(remove) {
            return ResponseEntity.status(HttpStatus.OK).body("Removido com sucesso");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível remover pela data");
    }

}
