package com.udemy.spring.api.cursomc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Categoria implements Serializable{	/*
												*	Interface que diz q o objeto pode ser convertido em um seq de bytes
												*	para trafegar em rede/ gravados em arquivos
												*/
	
	private static final long serialVersionUID = 1L;
	
	private BigDecimal id;
	private String name;
	
	public Categoria() {
	}

	public Categoria(BigDecimal id, String name) {
		this.id = id;
		this.name = name;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*
	 * No java Para que dois objetos sejam comparados pelo seu conteúdo
	 * E não pelo ponteiro de memória
	 * Comparar Pelo Valor
	*/
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
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
