package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.dao.FornecedorDAO;
import exceptions.CampoPreenchimento;
import com.EstoqueFacil.EstoqueFacil.model.FornecedorModel;
import exceptions.ErroDePreenchimentoInvalidoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FornecedorService {

    private final FornecedorDAO fornecedorDAO;

    public FornecedorService(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }


    public FornecedorModel cadastrarFornecedor(FornecedorModel fornecedor) {

        validarFornecedor(fornecedor);

        return fornecedorDAO.save(fornecedor);
    }

    public FornecedorModel buscarPorCnpj(String cnpj) {

        return fornecedorDAO.findByCnpj(cnpj).orElseThrow(() -> new NoSuchElementException("Nenhum funcionário encontrado"));
    }

    public FornecedorModel buscarPorEmail(String email) {

        return fornecedorDAO.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Nenhum funcionário foi encontrado"));
    }

    public List<FornecedorModel> buscarTodosFornecedores() {

        return this.fornecedorDAO.findAll();
    }

    public FornecedorModel atualizarFornecedorPorCnpj(String cnpj,FornecedorModel dadosAtualizados) {

        FornecedorModel  fornecedorAtualizado = buscarPorCnpj(cnpj);

        if(!fornecedorAtualizado.getEmail().equals(dadosAtualizados.getEmail())
                && fornecedorDAO.existsByEmail(dadosAtualizados.getEmail())) {
            throw new ErroDePreenchimentoInvalidoException("Email já está em uso");
        }

        fornecedorAtualizado.setRazaoSocial(dadosAtualizados.getRazaoSocial());
        fornecedorAtualizado.setEmail(dadosAtualizados.getEmail());

        return fornecedorDAO.save(fornecedorAtualizado);
    }

    public FornecedorModel deletarPorCnpj(String cnpj) {

        if (!fornecedorDAO.existsByCnpj(cnpj)) {
            throw new NoSuchElementException("Nenhum funcionário encontrado");
        }
        return fornecedorDAO.deleteByCnpj(cnpj);

    }

    public void validarFornecedor(FornecedorModel fornecedor){


        if(fornecedorDAO.existsByCnpj(fornecedor.getCnpj())){
            throw new CampoPreenchimento("Este Cnpj já está cadastrado");
        }

        if(fornecedorDAO.existsByEmail(fornecedor.getEmail())){
            throw new CampoPreenchimento("Esté Email já está cadastrado");
        }


    }
}
