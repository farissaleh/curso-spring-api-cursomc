package com.udemy.spring.api.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.spring.api.cursomc.domain.Cliente;

@Repository//Indica q é um Repos
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { /*	JpaRepository Acesso os dados conforme o informado
																					*/
	@Transactional(readOnly = true)//Não precisar ser envolvida em transação de banco de dados
	Cliente findByEmail(String email); //usar esse padrão (findBy+Campo) o spring impl um find pelo nome do campo
}
