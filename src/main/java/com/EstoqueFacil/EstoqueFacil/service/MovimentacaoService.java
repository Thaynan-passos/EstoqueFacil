package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.MovimentacaoRepository;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.MovimentacaoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public static void validarID(int id){
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido");
        }
    }

    public void validarDataMovimentacao(LocalDate dataMovimentacao) {
        if (dataMovimentacao.isAfter(LocalDate.now())) {
            throw new ErroDePreenchimentoInvalidoException("A data de movimentação não pode ser uma data futura.");
        }
    }

    public MovimentacaoModel cadastrarMovimentacao(MovimentacaoModel movimentacaoModel) {
        validarMovimentacao(movimentacaoModel);
        return movimentacaoRepository.save(movimentacaoModel);
    }

    public MovimentacaoModel buscarPorId(Integer id){
        return movimentacaoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum movimento foi encontrado"));
    }

    public MovimentacaoModel buscarPorDataMovimentacao(LocalDate dataMovimentacao){
        validarDataMovimentacao(dataMovimentacao);
        return  movimentacaoRepository.findByDataMovimentacao(dataMovimentacao).orElseThrow(() -> new NoSuchElementException("Nenhuma movimentação foi encontrada."));
    }

    public List<MovimentacaoModel> buscarTodasMovimentacoes() {
        return movimentacaoRepository.findAll();
    }

    
    public MovimentacaoModel atualizarPorId(int id,MovimentacaoModel dadosAtualizados) {

      MovimentacaoModel movimentacaoNova = buscarPorId(id);

      movimentacaoNova.setDataMovimentacao(dadosAtualizados.getDataMovimentacao());
      movimentacaoNova.setDescricao(dadosAtualizados.getDescricao());
      movimentacaoNova.setStatus(dadosAtualizados.getStatus());

      return movimentacaoRepository.save(movimentacaoNova);
    }

    public MovimentacaoModel atualizarPorData(LocalDate dataMovimentacao,MovimentacaoModel dadosAtualizados) {

        MovimentacaoModel movimentacaoNova = buscarPorDataMovimentacao(dataMovimentacao);

        movimentacaoNova.setDataMovimentacao(dataMovimentacao);
        movimentacaoNova.setDescricao(dadosAtualizados.getDescricao());
        movimentacaoNova.setStatus(dadosAtualizados.getStatus());

        return movimentacaoRepository.save(movimentacaoNova);


    }

    public MovimentacaoModel deletarMovimentacaoPorId(int id){
        if(!movimentacaoRepository.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhuma movimentação com o id " + id);
        }
       return movimentacaoRepository.deleteById(id);
    }

    public void validarMovimentacao(MovimentacaoModel movimentacao) {

        validarDataMovimentacao(movimentacao.getDataMovimentacao());
    }

}
