package com.udemy.spring.api.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.spring.api.cursomc.domain.Categoria;
import com.udemy.spring.api.cursomc.exceptions.ObjectNotFoundException;
import com.udemy.spring.api.cursomc.repositories.CategoriaRepository;

@Service // Oq faz ?
public class CategoriaService {
	
	@Autowired //Injeção de dep / Inversão de Controler - Controlado (instanciado) pelo container
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = repo.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: "+id //Supplier???
				+ ", Tipo: "+ Categoria.class));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);//P garantir q o obj será inserido e n atualizado
		return repo.save(obj);
	}

}
