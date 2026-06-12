package com.EstoqueFacil.EstoqueFacil.dao;

import com.EstoqueFacil.EstoqueFacil.model.LoteModel;
import com.EstoqueFacil.EstoqueFacil.model.MovimentacaoModel;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

import java.util.List;

import java.util.Optional;

@Repository
public interface MovimentacaoDAO extends JpaRepositoryImplementation<MovimentacaoModel,Integer> {

    List<MovimentacaoModel> findAll();
   Optional<MovimentacaoModel> findById(int id);
    void deleteById(int id);
    boolean existsById(int id);
    List<MovimentacaoModel> findByDataMovimentacao(LocalDate dataMovimentacao);
    boolean existsByDataMovimentacao(LocalDate dataMovimentacao);

}

