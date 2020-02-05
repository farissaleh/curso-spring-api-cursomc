package com.udemy.spring.api.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController//Reste Annotation
@RequestMapping(value="/categorias")//Mapeamento do endpoint
public class CategoriaResource {
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "Está Funcionando!";
	}

}
