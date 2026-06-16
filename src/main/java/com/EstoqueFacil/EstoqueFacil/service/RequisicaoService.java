package com.EstoqueFacil.EstoqueFacil.service;


import com.EstoqueFacil.EstoqueFacil.repository.RequisicaoRepository;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.Requisicao;
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

    public Requisicao cadastrarRequisicao(Requisicao requisicaoModel) {
        validarRequisicao(requisicaoModel);
        return requisicaoRepository.save(requisicaoModel);
    }

    public Requisicao buscarPorId(Integer id){
        return requisicaoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Nenhuma requisição foi encontrada"));
    }

    public Requisicao buscarPorDataRequisicao(LocalDate dataRequisicao){

        return requisicaoRepository.findByDataRequisicao(dataRequisicao).orElseThrow(() -> new NoSuchElementException("Nenhuma requisiçao foi encontrada"));
    }

    public List<Requisicao> buscarTodasRequisicoes() {
        return requisicaoRepository.findAll();
    }


    public Requisicao atualizarPorId(int id, Requisicao dadosAtualizados) {

        Requisicao requisicaoNovo = buscarPorId(id);

        requisicaoNovo.setDataRequisicao(dadosAtualizados.getDataRequisicao());
        requisicaoNovo.setMotivo(dadosAtualizados.getMotivo());
        requisicaoNovo.setStatus(dadosAtualizados.getStatus());

        return requisicaoRepository.save(requisicaoNovo);
    }

    public Requisicao atualizarPorData(LocalDate dataRequisicao, Requisicao dadosAtualizados) {

        Requisicao requisicaoNovo = buscarPorDataRequisicao(dataRequisicao);

        requisicaoNovo.setDataRequisicao(dataRequisicao);
        requisicaoNovo.setStatus(dadosAtualizados.getStatus());
        requisicaoNovo.setMotivo(dadosAtualizados.getMotivo());

        return requisicaoRepository.save(requisicaoNovo);
    }

    public Requisicao deletarRequisicaoPorId(int id){
        if(!requisicaoRepository.existsById(id)) {
            throw new NoSuchElementException("Não existe nenhuma requisição com o id " + id);
        }
        return requisicaoRepository.deleteById(id);
    }

    public Requisicao deletarRequisicaoPorData(LocalDate dataRequisicao){
        if(!requisicaoRepository.existsByDataRequisicao(dataRequisicao)) {
            throw new NoSuchElementException("Não existe nenhuma requisição com essa data");
        }
        return requisicaoRepository.deleteByDataRequisicao(dataRequisicao);

    }


    public void validarRequisicao(Requisicao requisicao) {

        validarDataRequisicao(requisicao.getDataRequisicao());
    }

}
