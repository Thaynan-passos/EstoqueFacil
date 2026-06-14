package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.LoteRepository;
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

    private final LoteRepository loteRepository;
    public LoteService(LoteRepository loteRepository) {
       this.loteRepository = loteRepository;
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
        return loteRepository.save(loteModel);
    }

    public Optional<LoteModel> buscarPorId(Integer id){
        if (!loteRepository.existsById(id)) {
            throw new NoSuchElementException("Não foi possível encontrar nenhum lote");
        }
        return loteRepository.findById(id);
    }
    public List<LoteModel> buscarTodosLotes() {
        return loteRepository.findAll();
    }

    public LoteModel buscarPorNumeroLote(int numeroLote) {
        if (numeroLote <= 0) {
            throw new NoSuchElementException("Não foi possível encontrar nenhum lote");
        }
        return loteRepository.findByNumeroLote(numeroLote);
    }

    public LoteModel atualizarLotePorNumero(int numero,LoteModel dadosAtualizados) {

        LoteModel loteNovo = buscarPorNumeroLote(numero);

        loteNovo.setDataFornecimento(dadosAtualizados.getDataFornecimento());
        loteNovo.setQuantidade(dadosAtualizados.getQuantidade());

        return  loteRepository.save(loteNovo);
    }

    public LoteModel deletarLotePorNumero(int numeroLote){
        if(!loteRepository.existsByNumeroLote(numeroLote)) {
            throw new NoSuchElementException("Não existe nenhum Lote com esse numero");
        }
        return loteRepository.deleteByNumeroLote(numeroLote);
    }

    public void validarLote(LoteModel lote) {

        validarDataValida(lote.getDataValidade());
        validarDataFabricacao(lote.getDataFabricacao());
        validarDataFornecimento(lote.getDataFornecimento(), lote.getDataFabricacao());

        if (loteRepository.existsByNumeroLote(lote.getNumeroLote())){
            throw new CampoPreenchimento("Esse número já existe");
        }
    }

}
