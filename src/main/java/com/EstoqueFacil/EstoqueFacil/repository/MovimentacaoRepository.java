package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.Movimentacao;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

import java.util.Optional;

@Repository
public interface MovimentacaoRepository extends JpaRepositoryImplementation<Movimentacao,Integer> {

    List<Movimentacao> findAll();
   Optional<Movimentacao> findById(int id);
    Movimentacao deleteById(int id);
    boolean existsById(int id);
   Optional<Movimentacao> findByDataMovimentacao(LocalDate dataMovimentacao);

}

