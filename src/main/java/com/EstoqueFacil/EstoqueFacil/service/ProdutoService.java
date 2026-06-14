package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.ProdutoRepository;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.ProdutoModel;
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

    public ProdutoModel cadastrarProduto(ProdutoModel produto){

        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    public List<ProdutoModel> buscarTodosProdutos(){
        return  produtoRepository.findAll();
    }

    public ProdutoModel buscarProdutosPorNome(String nome){
        return produtoRepository.findByNome(nome).orElseThrow(()-> new NoSuchElementException("Nenhum produto foi encontrado"));
    }

    public ProdutoModel buscarProdutosPorCodigoProduto(String codigoProduto){
        return produtoRepository.findByCodigoBarras(codigoProduto).orElseThrow(()-> new NoSuchElementException("Produto foi encontrado"));
    }

    public ProdutoModel atualizarProdutosPorNome(String nome, ProdutoModel dadosAtualizados){

        ProdutoModel produtoAtualizado =  buscarProdutosPorNome(nome);

        if(!produtoAtualizado.getCodigoBarras().equals(dadosAtualizados.getCodigoBarras()) && produtoRepository.existsByCodigoProduto(dadosAtualizados.getCodigoBarras())){
            throw new CampoPreenchimento("Esse código de barras já existe");
        }

        produtoAtualizado.setGarantia(produtoAtualizado.getGarantia());
        produtoAtualizado.setValorUnitario(produtoAtualizado.getValorUnitario());
        produtoAtualizado.setClassificacao(produtoAtualizado.getClassificacao());
        produtoAtualizado.setCodigoBarras(produtoAtualizado.getCodigoBarras());

        return produtoRepository.save(produtoAtualizado);
    }

    public ProdutoModel deletarProdutoPorNome(String nome){
        if (!produtoRepository.existsByNome(nome)) {
            throw new NoSuchElementException("Nenhum produto encontrado");
        }
        return produtoRepository.deleteByNome(nome);
    }

    public ProdutoModel deletarProdutoPorCodigo(String codigo){
        if (!produtoRepository.existsByCodigoProduto(codigo)) {
            throw new NoSuchElementException("Nenhum produto encontrado");
        }
        return produtoRepository.deleteByNome(codigo);
    }

    public void validarProduto(ProdutoModel produto){
        validarDataCadastro(produto.getDataCadastro());

        if(produtoRepository.existsByCodigoProduto(produto.getCodigoBarras())){
            throw new ErroDePreenchimentoInvalidoException("Produto existente");
        }
    }
}
