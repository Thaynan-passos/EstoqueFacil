package com.EstoqueFacil.EstoqueFacil.service;


import com.EstoqueFacil.EstoqueFacil.repository.RequisicaoRepository;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.RequisicaoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RequisicaoService {

    private final RequisicaoRepository requisicaoRepository;

    public RequisicaoService(RequisicaoRepository requisicaoRepository) {
        this.requisicaoRepository = requisicaoRepository;
    }

    public void validarDataRequisicao(LocalDate date) {

        if(date.isAfter(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data deve ser posterior a hoje");
        }
    }

    public  RequisicaoModel  cadastrarRequisicao( RequisicaoModel requisicaoModel) {
        validarRequisicao(requisicaoModel);
        return requisicaoRepository.save(requisicaoModel);
    }

    public RequisicaoModel buscarPorId(Integer id){
        return requisicaoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhuma requisição foi encontrada"));
    }

    public RequisicaoModel buscarPorDataRequisicao(LocalDate dataRequisicao){

        return requisicaoRepository.findByDataRequisicao(dataRequisicao).orElseThrow(() -> new NoSuchElementException("Nenhuma requisiçao foi encontrada"));
    }

    public List<RequisicaoModel> buscarTodasRequisicoes() {
        return requisicaoRepository.findAll();
    }


    public RequisicaoModel atualizarPorId(int id,RequisicaoModel dadosAtualizados) {

        RequisicaoModel requisicaoNovo = buscarPorId(id);

        requisicaoNovo.setDataRequisicao(dadosAtualizados.getDataRequisicao());
        requisicaoNovo.setMotivo(dadosAtualizados.getMotivo());
        requisicaoNovo.setStatus(dadosAtualizados.getStatus());

        return requisicaoRepository.save(requisicaoNovo);
    }

    public RequisicaoModel atualizarPorData(LocalDate dataRequisicao,RequisicaoModel dadosAtualizados) {

        RequisicaoModel requisicaoNovo = buscarPorDataRequisicao(dataRequisicao);

        requisicaoNovo.setDataRequisicao(dataRequisicao);
        requisicaoNovo.setStatus(dadosAtualizados.getStatus());
        requisicaoNovo.setMotivo(dadosAtualizados.getMotivo());

        return requisicaoRepository.save(requisicaoNovo);
    }

    public RequisicaoModel deletarRequisicaoPorId(int id){
        if(!requisicaoRepository.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhuma requisição com o id " + id);
        }
        return requisicaoRepository.deleteById(id);
    }

    public RequisicaoModel deletarRequisicaoPorData(LocalDate dataRequisicao){
        if(!requisicaoRepository.existsByDataRequisicao(dataRequisicao)) {
            throw new NoSuchElementException("Não existe nenhuma requisição com essa data");
        }
        return requisicaoRepository.deleteByDataRequisicao(dataRequisicao);

    }


    public void validarRequisicao(RequisicaoModel requisicao) {

        validarDataRequisicao(requisicao.getDataRequisicao());
    }

}
