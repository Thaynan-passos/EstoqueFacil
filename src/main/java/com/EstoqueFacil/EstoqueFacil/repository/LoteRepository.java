package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.Optional;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {


    List<Lote> findAll();
    List<Optional> findById(int id);
    void deleteById(int id);
    boolean existsById(int id);
    Lote findByNumeroLote(int numeroLote);
    boolean existsByNumeroLote(int numeroLote);
    Lote deleteByNumeroLote(int numeroLote);
}

