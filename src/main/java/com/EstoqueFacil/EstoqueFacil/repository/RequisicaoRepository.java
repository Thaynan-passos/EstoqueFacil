package com.EstoqueFacil.EstoqueFacil.repository;


import com.EstoqueFacil.EstoqueFacil.model.RequisicaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequisicaoRepository extends JpaRepository<RequisicaoModel,Integer> {

  Optional<RequisicaoModel> findByDataRequisicao(LocalDate dataRequisicao);
    boolean existsById(Integer id);
    List<RequisicaoModel> findById(int id);
    RequisicaoModel deleteById(int id);
    List<RequisicaoModel> findAll();
    boolean existsByDataRequisicao(LocalDate dataRequisicao);
    RequisicaoModel deleteByDataRequisicao(LocalDate dataRequisicao);

}

