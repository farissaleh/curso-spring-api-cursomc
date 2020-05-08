package com.udemy.spring.api.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.udemy.spring.api.cursomc.domain.Cliente;
import com.udemy.spring.api.cursomc.domain.enums.TipoCliente;
import com.udemy.spring.api.cursomc.dto.ClienteNewDTO;
import com.udemy.spring.api.cursomc.repositories.ClienteRepository;
import com.udemy.spring.api.cursomc.resources.exception.FieldMessage;
import com.udemy.spring.api.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		// inclua os testes aqui inserindo erros na lista
		
		//Validar Tipo e campo cpfOuCnpj
		if (dto.getTipo() == null) {
			list.add(new FieldMessage("tipo", "Preenchimento obrigatório"));
		} else {

			if (dto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(dto.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
			}

			if (dto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(dto.getCpfOuCnpj())) {
				list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
			}
		}
		
		//Validar Email
		Cliente aux = repo.findByEmail(dto.getEmail());
		
		if(aux!= null) {
			list.add(new FieldMessage("email", "Emial já cadastrado"));
		}
		
		
		//Acrescenta na lista de erros do framework de validação os erros identificados nas validações acimas
		//Lista será levada para o handler
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
