package com.udemy.spring.api.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.spring.api.cursomc.domain.Categoria;
import com.udemy.spring.api.cursomc.domain.Produto;

@Repository//Indica q é um Repo
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {/*	JpaRepository Acesso os dados conforme o informado */	
	
	//Método sem utilizar padrão de nomes do spring data
	
	//@Query Indica o JPQL q deverá ser usado nesse método, evita a criação de outra classe q implemente essa interface.
	//a query sobrepoem qlqr tipo de padrão usando no nome do método
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat in :categorias")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest); 
	
	//@Param() = indica q essa var representa o param nome dentro da query
	
	
	//Método utilizando o padrão de nomes do spring data
	
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest); 
}
