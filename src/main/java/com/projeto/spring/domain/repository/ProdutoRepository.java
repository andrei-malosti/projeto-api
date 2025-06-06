package com.projeto.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.spring.domain.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
}
