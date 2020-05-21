package com.udemy.spring.api.cursomc.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity // Exemplo Ótimo de Entidade associativa muitos pra muitos entre Produto e Pedido
public class ItemPedido implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@JsonIgnore // Não será serializado por conta da complexidade do tratamento
	@EmbeddedId// Diz o id está embutido em um tipo(classe) auxiliar
	private ItemPedidoPK id = new ItemPedidoPK();// Objeto auxiliar deve ser instanciado

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemPedido() {
	}

	// ItemPedidoPk n aparece no cosntrutor e sim as classes correspondentes para
	// facilitar o desenvolvimento
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}

	// Para permitir um acesso direto (sem passar pelo atriubuto id) as classes
	// correspondetes
	// deve-se criar os getters dessas classes
	@JsonIgnore // ignorar na serialização p evitar ref ciclica
	public Pedido getPedido() {
		return id.getPedido();
	}

 	public Produto getProduto() {
		return id.getProduto();
	}

	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
