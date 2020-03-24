package com.udemy.spring.api.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.spring.api.cursomc.domain.Cliente;
import com.udemy.spring.api.cursomc.domain.Pedido;
import com.udemy.spring.api.cursomc.exceptions.ObjectNotFoundException;
import com.udemy.spring.api.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: "+id //Supplier???
				+ ", Tipo: "+ Cliente.class));
	}

}
