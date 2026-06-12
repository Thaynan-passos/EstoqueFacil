package com.EstoqueFacil.EstoqueFacil.dao;


import com.EstoqueFacil.EstoqueFacil.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.Optional;

@Repository
public interface ProdutoDAO extends JpaRepository<ProdutoModel,Integer> {


    boolean existsByNome(String nome);
    boolean existsByCodigoProduto(String codigoProduto);
    Optional<ProdutoModel> findByNome(String nome);
    List<ProdutoModel> findAll();
    void deleteByNome(String nome);
    Optional<ProdutoModel> findByCodigoBarras(String codigoProduto);


}

