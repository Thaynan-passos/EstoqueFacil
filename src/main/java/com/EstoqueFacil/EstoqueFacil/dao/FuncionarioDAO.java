package com.EstoqueFacil.EstoqueFacil.dao;

import com.EstoqueFacil.EstoqueFacil.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.Optional;


@Repository
public interface FuncionarioDAO extends JpaRepository<FuncionarioModel, Integer> {

    Optional<FuncionarioModel> findByCpf(String cpf);
    Optional<FuncionarioModel> findByEmail(String email);
    FuncionarioModel deleteByCpf(String cpf);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    List<FuncionarioModel> findAll();


}


