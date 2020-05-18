package com.udemy.spring.api.cursomc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.udemy.spring.api.cursomc.domain.Categoria;
import com.udemy.spring.api.cursomc.domain.Cidade;
import com.udemy.spring.api.cursomc.domain.Cliente;
import com.udemy.spring.api.cursomc.domain.Endereco;
import com.udemy.spring.api.cursomc.domain.Estado;
import com.udemy.spring.api.cursomc.domain.ItemPedido;
import com.udemy.spring.api.cursomc.domain.Pagamento;
import com.udemy.spring.api.cursomc.domain.PagamentoComBoleto;
import com.udemy.spring.api.cursomc.domain.PagamentoComCartao;
import com.udemy.spring.api.cursomc.domain.Pedido;
import com.udemy.spring.api.cursomc.domain.Produto;
import com.udemy.spring.api.cursomc.domain.enums.EstadoPagamento;
import com.udemy.spring.api.cursomc.domain.enums.TipoCliente;
import com.udemy.spring.api.cursomc.repositories.CategoriaRepository;
import com.udemy.spring.api.cursomc.repositories.CidadeRepository;
import com.udemy.spring.api.cursomc.repositories.ClienteRepository;
import com.udemy.spring.api.cursomc.repositories.EnderecoRepository;
import com.udemy.spring.api.cursomc.repositories.EstadoRepository;
import com.udemy.spring.api.cursomc.repositories.ItemPedidoRepository;
import com.udemy.spring.api.cursomc.repositories.PagamentoRepository;
import com.udemy.spring.api.cursomc.repositories.PedidoRepository;
import com.udemy.spring.api.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner { // CommandLineRunner permite metodos ao iniciar?

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Inserir Categorias ao iniciar aplicação

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Games");
		
		Categoria cat4 = new Categoria(null, "Cama, Mesa e Banho");
		Categoria cat5 = new Categoria(null, "Roupas");
		Categoria cat6 = new Categoria(null, "Esportes");
		
		Categoria cat7 = new Categoria(null, "Eletrodoméstiocs");
		Categoria cat8 = new Categoria(null, "Móveis");
		Categoria cat9 = new Categoria(null, "Ar Condicionado");
		
		Categoria cat10 = new Categoria(null, "Cozinha");
		Categoria cat11 = new Categoria(null, "Quarto");
		Categoria cat12 = new Categoria(null, "Outros");

		Produto p1 = new Produto(null, "Computador", new BigDecimal(2000.00));
		Produto p2 = new Produto(null, "Impressora", new BigDecimal(800.00));
		Produto p3 = new Produto(null, "Mouse", new BigDecimal(80.00));
		Produto p4 = new Produto(null, "Mesa de escritório", new BigDecimal(300.00));
		Produto p5 = new Produto(null, "Toalha", new BigDecimal(50.00));
		Produto p6 = new Produto(null, "Colcha", new BigDecimal(200.00));
		Produto p7 = new Produto(null, "TV true color", new BigDecimal(1200.00));
		Produto p8 = new Produto(null, "Roçadeira", new BigDecimal(800.00));
		Produto p9 = new Produto(null, "Abajour", new BigDecimal(100.00));
		Produto p10 = new Produto(null, "Pendente", new BigDecimal(180.00));
		Produto p11 = new Produto(null, "Shampoo", new BigDecimal(90.00));
		

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

//		p1.getCategorias().addAll(Arrays.asList(cat1));
//		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
//		p3.getCategorias().addAll(Arrays.asList(cat1));


		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));


		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Distrito Federal");

		Cidade cid1 = new Cidade(null, "Uberlânida", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		Cidade cid4 = new Cidade(null, "Brasília", est3);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
		est3.getCidades().addAll(Arrays.asList(cid4));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2 , cid3, cid4));
		
		Cliente cl1 = new Cliente(null, "Maria Silva", "maria@email.com", "36378912377", TipoCliente.PESSOA_FISICA);
		
		cl1.getTelefones().addAll(Arrays.asList("98646325","35984621"));
		
		Endereco e1 = new Endereco(null, "Rua FLores", "500", "Apto 303", "Jardim", "38648", cid1, cl1 );
		Endereco e2 = new Endereco(null, "Av Matos", "455", "Apto 200", "Sul", "4654", cid2, cl1 );
		
		cl1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(cl1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cl1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cl1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cl1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().add(ip3);
		
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p2.getItens().add(ip2);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
