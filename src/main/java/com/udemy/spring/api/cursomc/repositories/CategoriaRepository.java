package com.udemy.spring.api.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.spring.api.cursomc.domain.Categoria;

@Repository//Indica q é um Repos
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> { /*	JpaRepository Acesso os dados conforme o informado
																					*/	
}
