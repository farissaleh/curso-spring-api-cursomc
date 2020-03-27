package com.udemy.spring.api.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.udemy.spring.api.cursomc.domain.Categoria;
import com.udemy.spring.api.cursomc.exceptions.DataIntegrityException;
import com.udemy.spring.api.cursomc.exceptions.ObjectNotFoundException;
import com.udemy.spring.api.cursomc.repositories.CategoriaRepository;

@Service // Oq faz ?
public class CategoriaService {
	
	@Autowired //Injeção de dep / Inversão de Controler - Controlado (instanciado) pelo container
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repo.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: "+id //Supplier???
				+ ", Tipo: "+ Categoria.class));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);//P garantir q o obj será inserido e n atualizado
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());//verificar se o obj c esse id existe
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
		}
	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	//Page armazena informações sobre a paginação
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);// properties
																											// campos a
																											// serem
																											// ordenados
		return repo.findAll(pageRequest);
	}

}
