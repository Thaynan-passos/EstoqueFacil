package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.model.*;
import com.EstoqueFacil.EstoqueFacil.repository.FuncionarioSetorRepository;
import com.EstoqueFacil.EstoqueFacil.repository.ProdutoRepository;
import com.EstoqueFacil.EstoqueFacil.repository.RequisicaoRepository;
import exceptions.ErroDePreenchimentoInvalidoException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class RequisicaoService {

    private final RequisicaoRepository requisicaoRepository;
    private final ProdutoRepository produtoRepository;
    private final FuncionarioSetorRepository funcionarioSetorRepository;

    public RequisicaoService(RequisicaoRepository requisicaoRepository,
                             ProdutoRepository produtoRepository, FuncionarioSetorRepository funcionarioSetorRepository) {
        this.requisicaoRepository = requisicaoRepository;
        this.produtoRepository = produtoRepository;
        this.funcionarioSetorRepository = funcionarioSetorRepository;
    }

    public void validarDataRequisicao(LocalDate data) {

        if (data == null) {
            throw new ErroDePreenchimentoInvalidoException(
                    "A data da requisição é obrigatória.");
        }

        if (data.isAfter(LocalDate.now())) {
            throw new ErroDePreenchimentoInvalidoException(
                    "A data não pode ser futura.");
        }
    }

    public void validarRequisicao(Requisicao requisicao) {

        if (requisicao == null) {
            throw new ErroDePreenchimentoInvalidoException(
                    "Requisição inválida.");
        }

        validarDataRequisicao(requisicao.getDataRequisicao());

        if (requisicao.getSetor() == null) {
            throw new ErroDePreenchimentoInvalidoException(
                    "O setor é obrigatório.");
        }
    }

    public Requisicao cadastrarRequisicao(Requisicao requisicao,
                                          Funcionario funcionario) {

        validarRequisicao(requisicao);

        if (funcionario == null) {
            throw new ErroDePreenchimentoInvalidoException(
                    "Funcionário inválido.");
        }

        requisicao.setStatus(Status.PENDENTE);

        requisicao.setFuncionario(funcionario);

        FuncionarioSetor funcionarioSetor = funcionarioSetorRepository
                .findByFuncionario(funcionario)
                .orElseThrow(() ->
                        new RuntimeException("Funcionário não possui um setor cadastrado."));


        // Associa o setor automaticamente
        requisicao.setSetor(funcionarioSetor.getSetor());

        if (requisicao.getProdutos() == null ||
                requisicao.getProdutos().isEmpty()) {

            throw new ErroDePreenchimentoInvalidoException(
                    "A requisição deve possuir pelo menos um produto.");
        }

        Set<Integer> produtosAdicionados = new HashSet<>();

        for (RequisicaoProduto rp : requisicao.getProdutos()) {

            if (rp == null) {
                throw new ErroDePreenchimentoInvalidoException("Item da requisição inválido.");
            }

            if (rp.getQuantidadeSolicitada() <= 0) {
                throw new ErroDePreenchimentoInvalidoException(
                        "A quantidade deve ser maior que zero.");
            }

            if (rp.getProduto() == null ||
                    rp.getProduto().getIdProduto() == null) {

                throw new NoSuchElementException(
                        "Produto inválido na requisição.");
            }

            Integer produtoId = rp.getProduto().getIdProduto();

            if (!produtosAdicionados.add(produtoId)) {
                throw new ErroDePreenchimentoInvalidoException(
                        "Existem produtos repetidos na requisição.");
            }

            Produto produto = produtoRepository.findById(produtoId)
                    .orElseThrow(() ->
                            new NoSuchElementException(
                                    "Produto não encontrado."));

            rp.setProduto(produto);
            rp.setRequisicao(requisicao);
        }

        return requisicaoRepository.save(requisicao);
    }

    public Requisicao buscarPorId(Integer id) {
        return requisicaoRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Nenhuma requisição foi encontrada."));
    }

    public Requisicao buscarPorDataRequisicao(LocalDate dataRequisicao) {

        return requisicaoRepository.findByDataRequisicao(dataRequisicao)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Nenhuma requisição foi encontrada."));
    }

    public List<Requisicao> buscarTodasRequisicoes() {
        return requisicaoRepository.findAll();
    }

    public List<Requisicao> buscarPorFuncionario(Funcionario funcionario) {
        return requisicaoRepository.findByFuncionarioWithProdutos(funcionario);
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

    public List<Requisicao> buscarHistoricoPorFuncionario(Funcionario funcionario) {
        return requisicaoRepository.findHistoricoByFuncionarioWithProdutos(funcionario);
    }

    public List<Requisicao> buscarUltimas() {
        return requisicaoRepository.findTop5ByOrderByDataRequisicaoDesc();
    }

    public Requisicao atualizarPorId(int id,
                                     Requisicao dadosAtualizados) {

        Requisicao requisicao = buscarPorId(id);

        validarDataRequisicao(dadosAtualizados.getDataRequisicao());

        requisicao.setDataRequisicao(dadosAtualizados.getDataRequisicao());
        requisicao.setMotivo(dadosAtualizados.getMotivo());
        requisicao.setStatus(dadosAtualizados.getStatus());

        return requisicaoRepository.save(requisicao);
    }
    public void atualizarStatus(int id, Status status) {

        Requisicao requisicao = buscarPorId(id);

        requisicao.setStatus(status);

        requisicaoRepository.save(requisicao);
    }

    public Requisicao deletarRequisicaoPorId(int id) {
        Requisicao requisicao = buscarPorId(id); // já lança exceção se não existir
        requisicaoRepository.deleteById(id);
        return requisicao;
    }

    public Requisicao deletarRequisicaoPorData(LocalDate dataRequisicao) {
        Requisicao requisicao = buscarPorDataRequisicao(dataRequisicao); // já lança exceção se não existir
        requisicaoRepository.delete(requisicao);
        return requisicao;
    }

}