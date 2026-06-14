package com.EstoqueFacil.EstoqueFacil.service;


import com.EstoqueFacil.EstoqueFacil.repository.RelatorioRepository;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.RelatorioModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    public RelatorioService(RelatorioRepository relatorioRepository) {
        this.relatorioRepository = relatorioRepository;
    }

    public void validarDataEmissao(LocalDate dataEmissao) {

        if (!dataEmissao.isEqual(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data da emissão deve se referir apenas a hoje");
        }
    }

    public  void validarDataCriacao(LocalDate dataInicio) {

        if(dataInicio.isBefore(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data não pode ser antes do dia atual");
        }
    }

    public void validarDataFim(LocalDate dataFim, LocalDate dataInicio) {

        if(dataFim.isBefore(dataInicio)) throw new ErroDePreenchimentoInvalidoException("O fim não pode ser antes do início");
    }

    public RelatorioModel cadastrarRelatorio(RelatorioModel relatorioModel) {
        validarRelatorio(relatorioModel);
        return relatorioRepository.save(relatorioModel);
    }

    public RelatorioModel buscarPorId(Integer id){
        return relatorioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum relatório foi encontrado"));
    }

    public RelatorioModel buscarPorDataEmissao(LocalDate dataEmissao){

         return relatorioRepository.findByDataEmissao(dataEmissao).orElseThrow(() -> new NoSuchElementException("Nenhum relatório foi encontrado."));
    }

    public List<RelatorioModel> buscarTodosRelatorios() {
        return relatorioRepository.findAll();
    }


    public RelatorioModel atualizarPorDataEmitida(LocalDate dataEmissao,RelatorioModel dadosAtualizados) {

        RelatorioModel relatorioNovo = buscarPorDataEmissao(dataEmissao);

        relatorioNovo.setDataFim(dadosAtualizados.getDataFim());
        relatorioNovo.setDescricao(dadosAtualizados.getDescricao());

        return relatorioRepository.save(relatorioNovo);
    }

    public RelatorioModel deletarLotePorId(int id){
        if(!relatorioRepository.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhum relatório com o id " + id);
        }
        return relatorioRepository.deleteById(id);
    }

    public void validarRelatorio(RelatorioModel relatorio){
        validarDataEmissao(relatorio.getDataEmissao());
        validarDataCriacao(relatorio.getDataInicio());
        validarDataFim(relatorio.getDataFim(), relatorio.getDataInicio());
    }

}
