package com.udemy.spring.api.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.spring.api.cursomc.domain.Cidade;
import com.udemy.spring.api.cursomc.domain.Cliente;
import com.udemy.spring.api.cursomc.domain.Endereco;
import com.udemy.spring.api.cursomc.domain.enums.TipoCliente;
import com.udemy.spring.api.cursomc.dto.ClienteDTO;
import com.udemy.spring.api.cursomc.dto.ClienteNewDTO;
import com.udemy.spring.api.cursomc.exceptions.DataIntegrityException;
import com.udemy.spring.api.cursomc.exceptions.ObjectNotFoundException;
import com.udemy.spring.api.cursomc.repositories.ClienteRepository;
import com.udemy.spring.api.cursomc.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	@Autowired 
	private EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repo.findById(id);
		return cliente.orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class));
	}
	
	@Transactional//Todas as alterações feitas no banco serão transacionais
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente que possui pedidos relacionadoss.");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));
		Cidade cid = new Cidade(dto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cid , cli);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(dto.getTelefone1());
		if (dto.getTelefone2()!= null) {
			cli.getTelefones().add(dto.getTelefone2());
		}
		if (dto.getTelefone3()!= null) {
			cli.getTelefones().add(dto.getTelefone3());
		}
		return cli;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getNome());
	}

}
