package com.EstoqueFacil.EstoqueFacil.repository;


import com.EstoqueFacil.EstoqueFacil.model.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao,Integer> {

  Optional<Requisicao> findByDataRequisicao(LocalDate dataRequisicao);
    boolean existsById(Integer id);
    List<Requisicao> findById(int id);
    Requisicao deleteById(int id);
    List<Requisicao> findAll();
    boolean existsByDataRequisicao(LocalDate dataRequisicao);
    Requisicao deleteByDataRequisicao(LocalDate dataRequisicao);

}

