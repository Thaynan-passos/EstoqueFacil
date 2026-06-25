package com.EstoqueFacil.EstoqueFacil.service;


import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.repository.ProdutoRepository;
import com.EstoqueFacil.EstoqueFacil.repository.RequisicaoRepository;
import exceptions.ErroDePreenchimentoInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RequisicaoService {

    private final RequisicaoRepository requisicaoRepository;
    private final ProdutoRepository produtoRepository;

    public RequisicaoService(RequisicaoRepository requisicaoRepository,
                             ProdutoRepository produtoRepository) {
        this.requisicaoRepository = requisicaoRepository;
        this.produtoRepository = produtoRepository;
    }

    public void validarDataRequisicao(LocalDate date) {

        if(date.isAfter(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data não pode ser uma data futura");
        }
    }

    public Requisicao cadastrarRequisicao(Requisicao requisicao) {

        validarRequisicao(requisicao);

        requisicao.setStatus(Status.PENDENTE);

        if (requisicao.getProdutos() != null) {

            for (RequisicaoProduto rp : requisicao.getProdutos()) {

                if (rp.getProduto() == null || rp.getProduto().getIdProduto() == null) {
                    throw new NoSuchElementException("Produto inválido na requisição");
                }

                rp.setRequisicao(requisicao);
                Integer produtoId = rp.getProduto().getIdProduto();

                Produto produto = produtoRepository.findById(produtoId)
                        .orElseThrow(() -> new NoSuchElementException("Produto não encontrado"));

                rp.setProduto(produto);

            }
        }

        return requisicaoRepository.save(requisicao);
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

    public long totalRequisicoes() {
        return requisicaoRepository.count();
    }


    public long totalPorStatus(Status status) {
        return requisicaoRepository.countByStatus(status);
    }

    public List<Requisicao> buscarPendentes() {
        return requisicaoRepository.findByStatus(Status.PENDENTE);
    }

    public List<Requisicao> buscarAprovadas() {
        return requisicaoRepository.findByStatus(Status.APROVADO);
    }

    public List<Requisicao> buscarRejeitadas() {
        return requisicaoRepository.findByStatus(Status.NEGADO);
    }

    public List<Requisicao> buscarHistorico() {
        return requisicaoRepository.findByStatusNot(Status.PENDENTE);
    }

    public List<Requisicao> buscarUltimas() {
        return requisicaoRepository.findTopByOrderByDataRequisicaoDesc();
    }


    public Requisicao atualizarPorId(int id, Requisicao dadosAtualizados) {

        Requisicao requisicaoNovo = buscarPorId(id);

        requisicaoNovo.setDataRequisicao(dadosAtualizados.getDataRequisicao());
        requisicaoNovo.setMotivo(dadosAtualizados.getMotivo());
        requisicaoNovo.setStatus(dadosAtualizados.getStatus());

        return requisicaoRepository.save(requisicaoNovo);
    }

    public Requisicao atualizarStatus(int id, Status status) {
        Requisicao requisicao = buscarPorId(id);
        requisicao.setStatus(status);
        return requisicaoRepository.save(requisicao);
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
