package com.udemy.spring.api.cursomc.resources;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.spring.api.cursomc.domain.Categoria;

@RestController//Reste Annotation
@RequestMapping(value="/categorias")//Mapeamento do endpoint
public class CategoriaResource {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		
		categorias.add(new Categoria(new BigDecimal(1), "Informática"));
		categorias.add(new Categoria(new BigDecimal(2), "Escritório "));
		
		
		
		return categorias;
	}

}
