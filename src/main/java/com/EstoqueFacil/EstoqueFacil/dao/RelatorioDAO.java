package com.EstoqueFacil.EstoqueFacil.dao;

import com.EstoqueFacil.EstoqueFacil.model.RelatorioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

@Repository
public interface RelatorioDAO extends JpaRepository<RelatorioModel,Integer> {

    List<RelatorioModel> findByDataEmissao(LocalDate dataEmissao);
    boolean existsByDataEmissao(LocalDate dataEmissao);
    boolean existsById(Integer id);
    List<RelatorioModel> findById(int id);
    void deleteById(Integer id);
    List<RelatorioModel> findAll();


}

