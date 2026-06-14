package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.LoteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<LoteModel, Integer> {


    List<LoteModel> findAll();
    List<Optional> findById(int id);
    void deleteById(int id);
    boolean existsById(int id);
    LoteModel findByNumeroLote(int numeroLote);
    boolean existsByNumeroLote(int numeroLote);
    LoteModel deleteByNumeroLote(int numeroLote);
}

