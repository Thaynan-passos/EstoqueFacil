package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.Funcionario;
import com.EstoqueFacil.EstoqueFacil.model.FuncionarioSetor;
import com.EstoqueFacil.EstoqueFacil.model.FuncionarioSetorId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioSetorRepository extends JpaRepository<FuncionarioSetor, FuncionarioSetorId> {
    Optional<FuncionarioSetor> findByFuncionario(Funcionario funcionario);

}