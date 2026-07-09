package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.ProdutoRepository;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.Produto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void validarDataCadastro(LocalDate dataCadastro){

        if(!dataCadastro.isEqual(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data do cadastrante deve ser referente ao dia atual (quando foi cadastrado)");
        }
    }

    public Produto cadastrarProduto(Produto produto){

        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    public List<Produto> buscarTodosProdutos(){
        return  produtoRepository.findAll();
    }

    public Produto buscarProdutosPorNome(String nome){
        return produtoRepository.findByNome(nome).orElseThrow(()-> new NoSuchElementException("Nenhum produto não foi encontrado"));
    }

    public Produto buscarProdutosPorCodigoProduto(String codigoProduto){
        return produtoRepository.findByCodigoBarras(codigoProduto).orElseThrow(()-> new NoSuchElementException("Produto não foi encontrado"));
    }

    public Produto atualizarProdutosPorNome(String nome, Produto dadosAtualizados){

        Produto produtoAtualizado =  buscarProdutosPorNome(nome);

        if(!produtoAtualizado.getCodigoBarras().equals(dadosAtualizados.getCodigoBarras()) && produtoRepository.existsByCodigoProduto(dadosAtualizados.getCodigoBarras())){
            throw new CampoPreenchimento("Esse código de barras já existe");
        }

        produtoAtualizado.setGarantia(dadosAtualizados.getGarantia());
        produtoAtualizado.setValorUnitario(dadosAtualizados.getValorUnitario());
        produtoAtualizado.setClassificacao(dadosAtualizados.getClassificacao());
        produtoAtualizado.setCodigoBarras(dadosAtualizados.getCodigoBarras());

        return produtoRepository.save(produtoAtualizado);
    }

    @Transactional
    public Produto deletarProdutoPorNome(String nome){
        if (!produtoRepository.existsByNome(nome)) {
            throw new NoSuchElementException("Nenhum produto encontrado");
        }
        return produtoRepository.deleteByNome(nome);
    }

    public void validarProduto(Produto produto){
        validarDataCadastro(produto.getDataCadastro());

        if(produtoRepository.existsByCodigoProduto(produto.getCodigoBarras())){
            throw new ErroDePreenchimentoInvalidoException("Produto existente");
        }
    }
}
