package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco,Integer> {

    Optional<Endereco> findById(Integer id);
    Endereco deleteById(int id);
    List<Endereco> findAll();
    boolean existsById(Integer id);
    boolean existsByCep(String cep);
    Optional<Endereco> findByCep(String cep);
}

