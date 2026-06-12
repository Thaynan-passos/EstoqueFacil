package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.dao.LoteDAO;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.LoteModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LoteService {

    private final LoteDAO loteDAO;
    public LoteService(LoteDAO loteDAO) {
       this.loteDAO = loteDAO;
    }

    public void validarDataValida(LocalDate data){
          if (data.isBefore(LocalDate.now())) {

            throw new ErroDePreenchimentoInvalidoException("Error: Não tem como o produto já estar vencido!");
        }
    }

    public void validarDataFabricacao(LocalDate dataFabricacao) {

        if (dataFabricacao.isAfter(LocalDate.now())) {
            throw new ErroDePreenchimentoInvalidoException("A data de fabricação não pode ser uma data futura.");
        }
    }

    public void validarDataFornecimento(LocalDate dataFornecimento, LocalDate dataFabricacao) {

        if (dataFornecimento.isBefore(dataFabricacao)) {
            throw new ErroDePreenchimentoInvalidoException("A data de fornecimento não pode ser anterior à data de fabricação.");
        }
    }

    public LoteModel cadastrarLote(LoteModel loteModel) {
        validarLote(loteModel);
        return loteDAO.save(loteModel);
    }

    public Optional<LoteModel> buscarPorId(Integer id){
        if (!loteDAO.existsById(id)) {
            throw new NoSuchElementException("Não foi possível encontrar nenhum lote");
        }
        return loteDAO.findById(id);
    }
    public List<LoteModel> buscarTodosLotes() {
        return loteDAO.findAll();
    }

    public LoteModel buscarPorNumeroLote(int numeroLote) {
        if (numeroLote <= 0) {
            throw new NoSuchElementException("Não foi possível encontrar nenhum lote");
        }
        return loteDAO.findByNumeroLote(numeroLote);
    }

    public LoteModel atualizarLotePorNumero(int numero,LoteModel dadosAtualizados) {

        LoteModel loteNovo = buscarPorNumeroLote(numero);

        loteNovo.setDataFornecimento(dadosAtualizados.getDataFornecimento());
        loteNovo.setQuantidade(dadosAtualizados.getQuantidade());

        return  loteDAO.save(loteNovo);
    }

    public void deletarLotePorId(int id){
        if(!loteDAO.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhum Lote com o id " + id);
        }
        loteDAO.deleteById(id);
    }

    public void validarLote(LoteModel lote) {

        validarDataValida(lote.getDataValidade());
        validarDataFabricacao(lote.getDataFabricacao());
        validarDataFornecimento(lote.getDataFornecimento(), lote.getDataFabricacao());

        if (loteDAO.existsByNumeroLote(lote.getNumeroLote())){
            throw new CampoPreenchimento("Esse número já existe");
        }
    }

}
