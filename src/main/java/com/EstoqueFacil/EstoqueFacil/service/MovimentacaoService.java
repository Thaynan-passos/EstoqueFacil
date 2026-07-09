package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.model.Lote;
import com.EstoqueFacil.EstoqueFacil.model.Produto;
import com.EstoqueFacil.EstoqueFacil.repository.LoteRepository;
import com.EstoqueFacil.EstoqueFacil.repository.MovimentacaoRepository;
import com.EstoqueFacil.EstoqueFacil.repository.ProdutoRepository;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.Movimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private LoteRepository loteRepository;

    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, LoteRepository loteRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.loteRepository = loteRepository;
    }


    public void registrarSaida(Integer loteId,
                               Integer quantidade,
                               String solicitante,
                               String setor,
                               String observacoes) {

        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new NoSuchElementException("Lote não encontrado"));

        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        if (lote.getQuantidade() < quantidade) {
            throw new IllegalStateException("Estoque insuficiente no lote");
        }

        lote.setQuantidade(
                lote.getQuantidade() - quantidade
        );

        loteRepository.save(lote);
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

    public void registrarEntrada(Integer loteId, Integer quantidade, Movimentacao movimentacao) {

        if (movimentacao == null) {
            throw new IllegalArgumentException("Movimentação inválida");
        }

        Lote lote = loteRepository.findById(loteId)
                .orElseThrow(() -> new NoSuchElementException("Lote não encontrado"));

        if (quantidade == null || quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        }

        validarMovimentacao(movimentacao);

        lote.setQuantidade(lote.getQuantidade() + quantidade);
        loteRepository.save(lote);

        movimentacao.setStatus(com.EstoqueFacil.EstoqueFacil.model.Status.APROVADO);
        movimentacao.setDescricao(movimentacao.getDescricao() == null ? "Entrada de material" : movimentacao.getDescricao());
        movimentacaoRepository.save(movimentacao);
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
