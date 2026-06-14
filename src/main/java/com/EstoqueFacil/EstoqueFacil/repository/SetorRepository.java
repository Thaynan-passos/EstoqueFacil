package com.EstoqueFacil.EstoqueFacil.repository;


import com.EstoqueFacil.EstoqueFacil.model.SetorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface SetorRepository extends JpaRepository<SetorModel,Integer> {

    boolean existsById(Integer id);
   Optional<SetorModel> findById(int id);
    SetorModel deleteById(int id);
    List<SetorModel> findAll();
}

