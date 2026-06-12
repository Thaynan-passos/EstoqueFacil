package com.EstoqueFacil.EstoqueFacil.service;


import com.EstoqueFacil.EstoqueFacil.dao.RelatorioDAO;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.RelatorioModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class RelatorioService {

    private final RelatorioDAO relatorioDAO;
    public RelatorioService(RelatorioDAO relatorioDAO) {
        this.relatorioDAO = relatorioDAO;
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
        return relatorioDAO.save(relatorioModel);
    }

    public RelatorioModel buscarPorId(Integer id){
        return relatorioDAO.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhum relatório foi encontrado"));
    }

    public boolean buscarPorDataEmissao(LocalDate dataEmissao){
         if(!relatorioDAO.existsByDataEmissao(dataEmissao)){
             throw new CampoPreenchimento("Não foi possível encontrar o relatório dessa data de emissão");
         }
         return relatorioDAO.existsByDataEmissao(dataEmissao);
    }

    public List<RelatorioModel> buscarTodosRelatorios() {
        return relatorioDAO.findAll();
    }


    public RelatorioModel atualizarPorId(int id,RelatorioModel dadosAtualizados) {

        RelatorioModel relatorioNovo = buscarPorId(id);

        relatorioNovo.setDataFim(dadosAtualizados.getDataFim());
        relatorioNovo.setDescricao(dadosAtualizados.getDescricao());

        return relatorioDAO.save(relatorioNovo);
    }

    public void deletarLotePorId(int id){
        if(!relatorioDAO.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhum relatório com o id " + id);
        }
        relatorioDAO.deleteById(id);
    }

    public void validarRelatorio(RelatorioModel relatorio){
        validarDataEmissao(relatorio.getDataEmissao());
        validarDataCriacao(relatorio.getDataInicio());
        validarDataFim(relatorio.getDataFim(), relatorio.getDataInicio());
    }

}
