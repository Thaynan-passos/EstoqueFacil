package service;

import exceptions.ErroDePreenchimentoInvalidoException;
import model.ProdutoModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProdutoService {


    public void validarDataCadastro(LocalDate dataCadastro){

        if(!dataCadastro.isEqual(LocalDate.now())){
            throw new ErroDePreenchimentoInvalidoException("A data do cadastrante deve ser referente ao dia atual (quando foi cadastrado)");
        }
    }

    public void validarProduto(ProdutoModel produto){
        validarDataCadastro(produto.getDataCadastro());
    }
}
