package com.udemy.spring.api.cursomc.resources;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.spring.api.cursomc.domain.Categoria;
import com.udemy.spring.api.cursomc.services.CategoriaService;

@RestController//Reste Annotation
@RequestMapping(value="/categorias")//Mapeamento do endpoint
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable BigDecimal id) { //PathVariable or PathParam? //ResponseEntity ? qlqr tipo
		Categoria categoria = service.buscar(id);
		return ResponseEntity.ok(categoria);
	}

}
