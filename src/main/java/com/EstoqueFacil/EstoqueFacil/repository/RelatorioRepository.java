package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.RelatorioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelatorioRepository extends JpaRepository<RelatorioModel,Integer> {

   Optional<RelatorioModel> findByDataEmissao(LocalDate dataEmissao);
    boolean existsById(Integer id);
    List<RelatorioModel> findById(int id);
    RelatorioModel deleteById(int id);
    List<RelatorioModel> findAll();


}

