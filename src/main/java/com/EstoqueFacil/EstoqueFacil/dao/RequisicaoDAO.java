package com.EstoqueFacil.EstoqueFacil.dao;


import com.EstoqueFacil.EstoqueFacil.model.RequisicaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface RequisicaoDAO extends JpaRepository<RequisicaoModel,Integer> {

    List<RequisicaoModel> findByDataRequisicao(LocalDate dataRequisicao);
    boolean existsByDataRequisicao(LocalDate dataRequisicao);
    boolean existsById(Integer id);
    List<RequisicaoModel> findById(int id);
    void deleteById(Integer id);
    List<RequisicaoModel> findAll();

}

