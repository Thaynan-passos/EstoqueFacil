package com.EstoqueFacil.EstoqueFacil.repository;


import com.EstoqueFacil.EstoqueFacil.model.Setor;
import com.EstoqueFacil.EstoqueFacil.model.TipoSetor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface SetorRepository extends JpaRepository<Setor,Integer> {

    boolean existsById(Integer id);
    Optional<Setor> findById(int id);
    Setor deleteById(int id);
    List<Setor> findAll();
    boolean existsByTipoSetor(TipoSetor tipoSetor);
}

