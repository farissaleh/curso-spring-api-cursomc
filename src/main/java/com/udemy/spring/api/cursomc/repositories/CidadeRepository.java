package com.udemy.spring.api.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.spring.api.cursomc.domain.Cidade;

@Repository//Indica q é um Repos
public interface CidadeRepository extends JpaRepository<Cidade, Integer> { /*	JpaRepository Acesso os dados conforme o informado
																					*/	
}
