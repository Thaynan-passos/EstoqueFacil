package com.EstoqueFacil.EstoqueFacil.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EstoqueFacil.EstoqueFacil.model.Relatorio;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio,Integer> {

    Optional<Relatorio> findByDataEmissao(LocalDate dataEmissao);



}

