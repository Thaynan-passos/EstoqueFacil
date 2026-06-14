package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.FornecedorModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<FornecedorModel,Integer> {

    Optional<FornecedorModel> findByCnpj(String cnpj);
    Optional<FornecedorModel> findByEmail(String email);
    FornecedorModel deleteByCnpj(String cnpj);
    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);
    List<FornecedorModel> findAll();



}

