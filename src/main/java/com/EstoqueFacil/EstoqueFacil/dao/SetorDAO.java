package com.EstoqueFacil.EstoqueFacil.dao;


import com.EstoqueFacil.EstoqueFacil.model.SetorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface SetorDAO extends JpaRepository<SetorModel,Integer> {

    boolean existsById(Integer id);
   Optional<SetorModel> findById(int id);
    void deleteById(Integer id);
    List<SetorModel> findAll();
}

