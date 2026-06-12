package com.EstoqueFacil.EstoqueFacil.service;


import com.EstoqueFacil.EstoqueFacil.dao.RequisicaoDAO;
import com.EstoqueFacil.EstoqueFacil.model.RelatorioModel;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.RequisicaoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RequisicaoService {

    private final RequisicaoDAO requisicaoDAO;

    public RequisicaoService(RequisicaoDAO requisicaoDAO) {
        this.requisicaoDAO = requisicaoDAO;
    }

    public void validarDataRequisicao(LocalDate date) {

        if(date.isAfter(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data deve ser posterior a hoje");
        }
    }

    public  RequisicaoModel  cadastrarRelatorio( RequisicaoModel requisicaoModel) {
        validarRequisicao(requisicaoModel);
        return requisicaoDAO.save(requisicaoModel);
    }

    public RequisicaoModel buscarPorId(Integer id){
        return requisicaoDAO.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhuma requisição foi encontrada"));
    }

    public boolean buscarPorDataRequisicao(LocalDate dataRequisicao){
        if(!requisicaoDAO.existsByDataRequisicao(dataRequisicao)){
            throw new CampoPreenchimento("Não foi possível encontrar a requisição dessa data requisição");
        }
        return requisicaoDAO.existsByDataRequisicao(dataRequisicao);
    }

    public List<RequisicaoModel> buscarTodasRequisicoes() {
        return requisicaoDAO.findAll();
    }


    public RequisicaoModel atualizarPorId(int id,RequisicaoModel dadosAtualizados) {

        RequisicaoModel requisicaoNovo = buscarPorId(id);

        requisicaoNovo.setDataRequisicao(dadosAtualizados.getDataRequisicao());
        requisicaoNovo.setMotivo(dadosAtualizados.getMotivo());
        requisicaoNovo.setStatus(dadosAtualizados.getStatus());

        return requisicaoDAO.save(requisicaoNovo);
    }

    public void deletarLotePorId(int id){
        if(!requisicaoDAO.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhuma requisição com o id " + id);
        }
        requisicaoDAO.deleteById(id);
    }


    public void validarRequisicao(RequisicaoModel requisicao) {

        validarDataRequisicao(requisicao.getDataRequisicao());
    }

}
