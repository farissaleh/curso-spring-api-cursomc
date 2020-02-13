package com.udemy.spring.api.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.spring.api.cursomc.domain.Endereco;

@Repository//Indica q é um Repos
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> { /*	JpaRepository Acesso os dados conforme o informado
																					*/	
}
