package com.EstoqueFacil.EstoqueFacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EstoqueFacil.EstoqueFacil.model.Lote;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {

    Optional<Lote> findByNumeroLote(int numeroLote);
    boolean existsByNumeroLote(int numeroLote);
    void deleteByNumeroLote(int numeroLote);

}

