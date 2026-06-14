package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.FornecedorRepository;
import exceptions.CampoPreenchimento;
import com.EstoqueFacil.EstoqueFacil.model.FornecedorModel;
import exceptions.ErroDePreenchimentoInvalidoException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }


    public FornecedorModel cadastrarFornecedor(FornecedorModel fornecedor) {

        validarFornecedor(fornecedor);

        return fornecedorRepository.save(fornecedor);
    }

    public FornecedorModel buscarPorCnpj(String cnpj) {

        return fornecedorRepository.findByCnpj(cnpj).orElseThrow(() -> new NoSuchElementException("Nenhum funcionário encontrado"));
    }

    public FornecedorModel buscarPorEmail(String email) {

        return fornecedorRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Nenhum funcionário foi encontrado"));
    }

    public List<FornecedorModel> buscarTodosFornecedores() {

        return this.fornecedorRepository.findAll();
    }

    public FornecedorModel atualizarFornecedorPorCnpj(String cnpj,FornecedorModel dadosAtualizados) {

        FornecedorModel  fornecedorAtualizado = buscarPorCnpj(cnpj);

        if(!fornecedorAtualizado.getEmail().equals(dadosAtualizados.getEmail())
                && fornecedorRepository.existsByEmail(dadosAtualizados.getEmail())) {
            throw new ErroDePreenchimentoInvalidoException("Email já está em uso");
        }

        fornecedorAtualizado.setRazaoSocial(dadosAtualizados.getRazaoSocial());
        fornecedorAtualizado.setEmail(dadosAtualizados.getEmail());

        return fornecedorRepository.save(fornecedorAtualizado);
    }

    public FornecedorModel deletarPorCnpj(String cnpj) {

        if (!fornecedorRepository.existsByCnpj(cnpj)) {
            throw new NoSuchElementException("Nenhum funcionário encontrado");
        }
        return fornecedorRepository.deleteByCnpj(cnpj);

    }

    public void validarFornecedor(FornecedorModel fornecedor){


        if(fornecedorRepository.existsByCnpj(fornecedor.getCnpj())){
            throw new CampoPreenchimento("Este Cnpj já está cadastrado");
        }

        if(fornecedorRepository.existsByEmail(fornecedor.getEmail())){
            throw new CampoPreenchimento("Esté Email já está cadastrado");
        }


    }
}
