package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.repository.FornecedorRepository;
import exceptions.CampoPreenchimento;
import com.EstoqueFacil.EstoqueFacil.model.Fornecedor;
import exceptions.ErroDePreenchimentoInvalidoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository) {
        this.fornecedorRepository = fornecedorRepository;
    }


    public Fornecedor cadastrarFornecedor(Fornecedor fornecedor) {

        validarFornecedor(fornecedor);

        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor buscarPorCnpj(String cnpj) {

        return fornecedorRepository.findByCnpj(cnpj).orElseThrow(() -> new NoSuchElementException("Nenhum funcionário encontrado"));
    }

    public Fornecedor buscarPorEmail(String email) {

        return fornecedorRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("Nenhum funcionário foi encontrado"));
    }

    public List<Fornecedor> buscarTodosFornecedores() {

        return this.fornecedorRepository.findAll();
    }

    public Fornecedor atualizarFornecedorPorCnpj(String cnpj, Fornecedor dadosAtualizados) {

        Fornecedor fornecedorAtualizado = buscarPorCnpj(cnpj);

        if(!fornecedorAtualizado.getEmail().equals(dadosAtualizados.getEmail())
                && fornecedorRepository.existsByEmail(dadosAtualizados.getEmail())) {
            throw new ErroDePreenchimentoInvalidoException("Email já está em uso");
        }

        fornecedorAtualizado.setRazaoSocial(dadosAtualizados.getRazaoSocial());
        fornecedorAtualizado.setEmail(dadosAtualizados.getEmail());

        return fornecedorRepository.save(fornecedorAtualizado);
    }

    @Transactional
    public Fornecedor deletarPorCnpj(String cnpj) {

        if (!fornecedorRepository.existsByCnpj(cnpj)) {
            throw new NoSuchElementException("Nenhum funcionário encontrado");
        }
        return fornecedorRepository.deleteByCnpj(cnpj);

    }

    public void validarFornecedor(Fornecedor fornecedor){


        if(fornecedorRepository.existsByCnpj(fornecedor.getCnpj())){
            throw new CampoPreenchimento("Este Cnpj já está cadastrado");
        }

        if(fornecedorRepository.existsByEmail(fornecedor.getEmail())){
            throw new CampoPreenchimento("Esté Email já está cadastrado");
        }


    }
}
