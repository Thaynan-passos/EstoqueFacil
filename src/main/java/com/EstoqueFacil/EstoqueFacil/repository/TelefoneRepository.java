package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,Integer> {

    boolean existsById(Integer id);
    Optional<Telefone> findById(int id);
    Telefone deleteById(int id);
    List<Telefone> findAll();
    boolean existsByTelefone(String telefone);

}

