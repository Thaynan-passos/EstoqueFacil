package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.MovimentacaoRepository;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.Movimentacao;
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

    public Movimentacao cadastrarMovimentacao(Movimentacao movimentacaoModel) {
        validarMovimentacao(movimentacaoModel);
        return movimentacaoRepository.save(movimentacaoModel);
    }

    public Movimentacao buscarPorId(Integer id){
        return movimentacaoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum movimento foi encontrado"));
    }

    public Movimentacao buscarPorDataMovimentacao(LocalDate dataMovimentacao){
        validarDataMovimentacao(dataMovimentacao);
        return  movimentacaoRepository.findByDataMovimentacao(dataMovimentacao).orElseThrow(() -> new NoSuchElementException("Nenhuma movimentação foi encontrada."));
    }

    public List<Movimentacao> buscarTodasMovimentacoes() {
        return movimentacaoRepository.findAll();
    }

    
    public Movimentacao atualizarPorId(int id, Movimentacao dadosAtualizados) {

      Movimentacao movimentacaoNova = buscarPorId(id);

      movimentacaoNova.setDataMovimentacao(dadosAtualizados.getDataMovimentacao());
      movimentacaoNova.setDescricao(dadosAtualizados.getDescricao());
      movimentacaoNova.setStatus(dadosAtualizados.getStatus());

      return movimentacaoRepository.save(movimentacaoNova);
    }

    public Movimentacao atualizarPorData(LocalDate dataMovimentacao, Movimentacao dadosAtualizados) {

        Movimentacao movimentacaoNova = buscarPorDataMovimentacao(dataMovimentacao);

        movimentacaoNova.setDataMovimentacao(dataMovimentacao);
        movimentacaoNova.setDescricao(dadosAtualizados.getDescricao());
        movimentacaoNova.setStatus(dadosAtualizados.getStatus());

        return movimentacaoRepository.save(movimentacaoNova);


    }

    public Movimentacao deletarMovimentacaoPorId(int id){
        if(!movimentacaoRepository.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhuma movimentação com o id " + id);
        }
       return movimentacaoRepository.deleteById(id);
    }

    public void validarMovimentacao(Movimentacao movimentacao) {

        validarDataMovimentacao(movimentacao.getDataMovimentacao());
    }

}
