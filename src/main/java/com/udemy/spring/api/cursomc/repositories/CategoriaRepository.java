package com.udemy.spring.api.cursomc.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.spring.api.cursomc.domain.Categoria;

@Repository//Indica q é um Repos
public interface CategoriaRepository extends JpaRepository<Categoria, BigDecimal> { /*	JpaRepository Acesso os dados conforme o informado
																						já contem várias operações de acesso ao dado
																					*/	
}
