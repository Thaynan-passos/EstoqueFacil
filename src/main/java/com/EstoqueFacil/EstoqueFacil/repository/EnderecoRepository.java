package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel,Integer> {

    Optional<EnderecoModel> findById(Integer id);
    EnderecoModel deleteById(int id);
    List<EnderecoModel> findAll();
    boolean existsById(Integer id);
    boolean existsByCep(String cep);
    Optional<EnderecoModel> findByCep(String cep);
}

