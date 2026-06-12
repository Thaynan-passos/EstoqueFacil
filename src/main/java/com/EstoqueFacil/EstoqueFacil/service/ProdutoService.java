package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.dao.ProdutoDAO;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import com.EstoqueFacil.EstoqueFacil.model.ProdutoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProdutoService {

    private final ProdutoDAO produtoDAO;
    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void validarDataCadastro(LocalDate dataCadastro){

        if(!dataCadastro.isEqual(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data do cadastrante deve ser referente ao dia atual (quando foi cadastrado)");
        }
    }

    public ProdutoModel cadastrarProduto(ProdutoModel produto){

        validarProduto(produto);
        return produtoDAO.save(produto);
    }

    public List<ProdutoModel> buscarTodosProdutos(){
        return  produtoDAO.findAll();
    }

    public ProdutoModel buscarProdutosPorNome(String nome){
        return produtoDAO.findByNome(nome).orElseThrow(()-> new NoSuchElementException("Nenhum produto foi encontrado"));
    }

    public ProdutoModel buscarProdutosPorCodigoProduto(String codigoProduto){
        return produtoDAO.findByCodigoBarras(codigoProduto).orElseThrow(()-> new NoSuchElementException("Produto foi encontrado"));
    }

    public ProdutoModel atualizarProdutosPorNome(String nome, ProdutoModel dadosAtualizados){

        ProdutoModel produtoAtualizado =  buscarProdutosPorNome(nome);

        if(!produtoAtualizado.getCodigoBarras().equals(dadosAtualizados.getCodigoBarras()) && produtoDAO.existsByCodigoProduto(dadosAtualizados.getCodigoBarras())){
            throw new CampoPreenchimento("Esse código de barras já existe");
        }

        produtoAtualizado.setGarantia(produtoAtualizado.getGarantia());
        produtoAtualizado.setValorUnitario(produtoAtualizado.getValorUnitario());
        produtoAtualizado.setClassificacao(produtoAtualizado.getClassificacao());
        produtoAtualizado.setCodigoBarras(produtoAtualizado.getCodigoBarras());

        return produtoDAO.save(produtoAtualizado);
    }

    public void deletarProdutoPorNome(String nome){
        if (!produtoDAO.existsByNome(nome)) {
            throw new NoSuchElementException("Nenhum produto encontrado");
        }
        produtoDAO.deleteByNome(nome);
    }

    public void deletarProdutoPorCodigo(String codigo){
        if (!produtoDAO.existsByCodigoProduto(codigo)) {
            throw new NoSuchElementException("Nenhum produto encontrado");
        }
        produtoDAO.deleteByNome(codigo);
    }

    public void validarProduto(ProdutoModel produto){
        validarDataCadastro(produto.getDataCadastro());

        if(produtoDAO.existsByCodigoProduto(produto.getCodigoBarras())){
            throw new ErroDePreenchimentoInvalidoException("Produto existente");
        }
    }
}
