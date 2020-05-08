package com.udemy.spring.api.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.udemy.spring.api.cursomc.domain.Cliente;
import com.udemy.spring.api.cursomc.dto.ClienteDTO;
import com.udemy.spring.api.cursomc.repositories.ClienteRepository;
import com.udemy.spring.api.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	//o ID não está sendo passado dentro do DTO e sim na uri da requisição
	//Para acessar essa info usa-se:
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO dto, ConstraintValidatorContext context) {
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> list = new ArrayList<FieldMessage>();
		
		//Validar Email
		Cliente aux = repo.findByEmail(dto.getEmail());
		
		if(aux!= null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Emial já cadastrado"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
