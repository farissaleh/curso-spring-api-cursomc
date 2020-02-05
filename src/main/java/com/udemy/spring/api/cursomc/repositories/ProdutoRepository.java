package com.udemy.spring.api.cursomc.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.spring.api.cursomc.domain.Produto;

@Repository//Indica q é um Repos
public interface ProdutoRepository extends JpaRepository<Produto, Integer> { /*	JpaRepository Acesso os dados conforme o informado
																					*/	
}
