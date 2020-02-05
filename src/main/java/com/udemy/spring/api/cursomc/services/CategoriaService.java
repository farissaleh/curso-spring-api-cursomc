package com.udemy.spring.api.cursomc.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.spring.api.cursomc.domain.Categoria;
import com.udemy.spring.api.cursomc.repositories.CategoriaRepository;

@Service // Oq faz ?
public class CategoriaService {
	
	@Autowired //Injeção de dep / Inversão de Controler - Controlado (instanciado) pelo container
	private CategoriaRepository repo;
	
	public Categoria buscar(BigDecimal id) {
		Optional<Categoria> categoria = repo.findById(id);
		return categoria.orElse(null);
	}

}
