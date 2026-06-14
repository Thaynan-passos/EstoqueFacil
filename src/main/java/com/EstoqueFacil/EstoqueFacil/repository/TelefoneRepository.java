package com.EstoqueFacil.EstoqueFacil.repository;

import com.EstoqueFacil.EstoqueFacil.model.TelefoneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface TelefoneRepository extends JpaRepository<TelefoneModel,Integer> {

    boolean existsById(Integer id);
    Optional<TelefoneModel> findById(int id);
    TelefoneModel deleteById(int id);
    List<TelefoneModel> findAll();
    boolean existsByTelefone(String telefone);

}

