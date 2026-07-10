package com.EstoqueFacil.EstoqueFacil.service;

import com.EstoqueFacil.EstoqueFacil.model.Fornecedor;
import com.EstoqueFacil.EstoqueFacil.repository.FornecedorRepository;
import exceptions.CampoPreenchimento;
import exceptions.ErroDePreenchimentoInvalidoException;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;
    private final Validator validator;

    public FornecedorService(FornecedorRepository fornecedorRepository, Validator validator) {
        this.fornecedorRepository = fornecedorRepository;
        this.validator = validator;
    }

    public Fornecedor cadastrarFornecedor(Fornecedor fornecedor) {

        validarFornecedor(fornecedor);

        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor buscarPorCnpj(String cnpj) {

        return fornecedorRepository.findByCnpj(cnpj).orElseThrow(() -> new NoSuchElementException("Nenhum fornecedor foi encontrado"));
    }

    public List<Fornecedor> buscarTodosFornecedores() {

        return this.fornecedorRepository.findAll();
    }

    public Fornecedor atualizarFornecedorPorCnpj(String cnpj, Fornecedor dadosAtualizados) {

        Fornecedor fornecedorAtualizado = buscarPorCnpj(cnpj);

        if (!fornecedorAtualizado.getEmail().equals(dadosAtualizados.getEmail())
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
            throw new NoSuchElementException("Nenhum fornecedor foi encontrado");
        }
        return fornecedorRepository.deleteByCnpj(cnpj);

    }

    public void validarFornecedor(Fornecedor fornecedor) {

        // 1. Validação de formato (Bean Validation da entidade)
        Set<ConstraintViolation<Fornecedor>> violacoes = validator.validate(fornecedor);
        if (!violacoes.isEmpty()) {
            String mensagens = violacoes.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            throw new ErroDePreenchimentoInvalidoException(mensagens);
        }

        // 2. Validação de duplicidade
        if (fornecedorRepository.existsByCnpj(fornecedor.getCnpj())) {
            throw new CampoPreenchimento("Este CNPJ já está cadastrado");
        }

        if (fornecedorRepository.existsByEmail(fornecedor.getEmail())) {
            throw new CampoPreenchimento("Este Email já está cadastrado");
        }
    }
}