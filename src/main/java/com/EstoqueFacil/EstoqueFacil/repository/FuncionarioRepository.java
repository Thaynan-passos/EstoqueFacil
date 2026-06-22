package com.EstoqueFacil.EstoqueFacil.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EstoqueFacil.EstoqueFacil.model.Cargo;
import com.EstoqueFacil.EstoqueFacil.model.Funcionario;


@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByCpf(String cpf);
    Optional<Funcionario> findByEmail(String email);
    Funcionario deleteByCpf(String cpf);
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByCargo(Cargo cargo);
    List<Funcionario> findAll();


}


