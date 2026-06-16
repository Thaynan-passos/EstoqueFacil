package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio,Integer> {

   Optional<Relatorio> findByDataEmissao(LocalDate dataEmissao);
    boolean existsById(Integer id);
    List<Relatorio> findById(int id);
    Relatorio deleteById(int id);
    List<Relatorio> findAll();


}

