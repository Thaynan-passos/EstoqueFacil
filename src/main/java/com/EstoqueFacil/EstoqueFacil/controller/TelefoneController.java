package com.EstoqueFacil.EstoqueFacil.controller;


import com.EstoqueFacil.EstoqueFacil.model.Telefone;
import com.EstoqueFacil.EstoqueFacil.service.TelefoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class TelefoneController {

    @Autowired
    private TelefoneService telefoneService;

    @PostMapping
    public ResponseEntity<?> criarTelefone(@Valid @RequestBody Telefone telefone){

        Telefone telefoneNovo = telefoneService.cadastrarTelefone(telefone);
        return ResponseEntity.status(HttpStatus.OK).body(telefoneNovo);
    }

    @GetMapping
    public ResponseEntity<?> listarTelefone(){
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.buscarTodosTelefones());
    }

    @GetMapping("/pegar")
    public ResponseEntity<?> buscarTelefonePorId(@Valid @RequestParam Integer idTelefone){
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.buscarTelefonePorId(idTelefone));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarTelefone(@Valid @RequestParam int id, @Valid @RequestBody Telefone telefone){
        return ResponseEntity.ok(telefoneService.atualizarTelefonePorId(id, telefone));
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<?> deletarTelefone(@Valid @RequestParam Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(telefoneService.deletarTelefonePorId(id));
    }

}
