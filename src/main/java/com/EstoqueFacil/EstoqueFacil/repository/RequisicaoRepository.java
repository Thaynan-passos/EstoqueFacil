package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.Requisicao;
import com.EstoqueFacil.EstoqueFacil.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Integer> {

  Optional<Requisicao> findByDataRequisicao(LocalDate dataRequisicao);
  long countByStatus(Status status);
  List<Requisicao> findTop5ByOrderByDataRequisicaoDesc();
  List<Requisicao> findByStatus(Status status);
  List<Requisicao> findByStatusNot(Status status);
}