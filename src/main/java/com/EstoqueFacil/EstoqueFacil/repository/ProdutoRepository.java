package com.EstoqueFacil.EstoqueFacil.repository;


import com.EstoqueFacil.EstoqueFacil.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Integer> {


    boolean existsByNome(String nome);
    boolean existsByCodigoProduto(String codigoProduto);
    Optional<Produto> findByNome(String nome);
    List<Produto> findAll();
    Produto deleteByNome(String nome);
    Optional<Produto> findByCodigoBarras(String codigoProduto);


}

