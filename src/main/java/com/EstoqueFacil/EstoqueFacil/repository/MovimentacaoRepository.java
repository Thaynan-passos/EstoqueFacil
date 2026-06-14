package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.MovimentacaoModel;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

import java.util.Optional;

@Repository
public interface MovimentacaoRepository extends JpaRepositoryImplementation<MovimentacaoModel,Integer> {

    List<MovimentacaoModel> findAll();
   Optional<MovimentacaoModel> findById(int id);
    MovimentacaoModel deleteById(int id);
    boolean existsById(int id);
   Optional<MovimentacaoModel> findByDataMovimentacao(LocalDate dataMovimentacao);
    boolean existsByDataMovimentacao(LocalDate dataMovimentacao);

}

