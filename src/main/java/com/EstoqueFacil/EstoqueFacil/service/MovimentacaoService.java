package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.dao.MovimentacaoDAO;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.MovimentacaoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MovimentacaoService {

    private final MovimentacaoDAO movimentacaoDAO;

    public MovimentacaoService(MovimentacaoDAO movimentacaoDAO) {
        this.movimentacaoDAO = movimentacaoDAO;
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
        return movimentacaoDAO.save(movimentacaoModel);
    }

    public MovimentacaoModel buscarPorId(Integer id){
        return movimentacaoDAO.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum movimento foi encontrado"));
    }
    public List<MovimentacaoModel> buscarTodasMovimentacoes() {
        return movimentacaoDAO.findAll();
    }

    
    public MovimentacaoModel atualizarPorId(int id,MovimentacaoModel dadosAtualizados) {

      MovimentacaoModel movimentacaoNova = buscarPorId(id);

      movimentacaoNova.setDataMovimentacao(dadosAtualizados.getDataMovimentacao());
      movimentacaoNova.setDescricao(dadosAtualizados.getDescricao());
      movimentacaoNova.setStatus(dadosAtualizados.getStatus());

      return movimentacaoDAO.save(movimentacaoNova);
    }

    public void deletarMovimentacaoPorId(int id){
        if(!movimentacaoDAO.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhuma movimentação com o id " + id);
        }
        movimentacaoDAO.deleteById(id);
    }

    public void validarMovimentacao(MovimentacaoModel movimentacao) {

        validarDataMovimentacao(movimentacao.getDataMovimentacao());
    }

}
