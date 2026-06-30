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
  boolean existsByDataRequisicao(LocalDate dataRequisicao);
  long countByStatus(Status status);

  @Query("SELECT DISTINCT r FROM Requisicao r " +
          "LEFT JOIN FETCH r.produtos rp " +
          "LEFT JOIN FETCH rp.produto " +
          "ORDER BY r.dataRequisicao DESC " +
          "LIMIT 5")
  List<Requisicao> findTop5ByOrderByDataRequisicaoDesc();

  List<Requisicao> findByStatus(Status status);
  List<Requisicao> findByStatusNot(Status status);

  List<Requisicao> findByFuncionarioIdFuncionario(int idFuncionario);
  long countByFuncionarioIdFuncionarioAndStatus(int idFuncionario, Status status);
  long countByFuncionarioIdFuncionario(int idFuncionario);
  List<Requisicao> findTop5ByFuncionarioIdFuncionarioOrderByDataRequisicaoDesc(int idFuncionario);
}