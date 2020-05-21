package com.udemy.spring.api.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.spring.api.cursomc.domain.Cliente;
import com.udemy.spring.api.cursomc.domain.ItemPedido;
import com.udemy.spring.api.cursomc.domain.PagamentoComBoleto;
import com.udemy.spring.api.cursomc.domain.Pedido;
import com.udemy.spring.api.cursomc.domain.enums.EstadoPagamento;
import com.udemy.spring.api.cursomc.exceptions.ObjectNotFoundException;
import com.udemy.spring.api.cursomc.repositories.ItemPedidoRepository;
import com.udemy.spring.api.cursomc.repositories.PagamentoRepository;
import com.udemy.spring.api.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id // Supplier???
				+ ", Tipo: " + Cliente.class));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);// Lembrar de setar a mão dubla dos objetos
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}

		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());

//		final Pedido  objF = obj;
//		obj.getItens().forEach(item -> {
//			item.setDesconto(0.0);
//			item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());
//			item.setPedido(objF);
//		});
//		itemPedidoRepository.saveAll(obj.getItens());

		for (ItemPedido item : obj.getItens()) {
			item.setDesconto(0.0);
			item.setPreco(produtoService.find(item.getProduto().getId()).getPreco().doubleValue());
			item.setPedido(obj);
			
			
		}
		
		itemPedidoRepository.saveAll(obj.getItens());

		return obj;
	}

}
