package com.marcelo.cursomc.services.validation;

import com.marcelo.cursomc.domain.Cliente;
import com.marcelo.cursomc.domain.enums.TipoCliente;
import com.marcelo.cursomc.dto.ClienteDTO;
import com.marcelo.cursomc.dto.ClienteNewDTO;
import com.marcelo.cursomc.repositories.ClienteRepository;
import com.marcelo.cursomc.resources.exception.FieldMessage;
import com.marcelo.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }
    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String> ) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
        if(aux != null && !aux.getId().equals(uriId)){
            list.add(new FieldMessage("email", "Email já existente"));
        }

// inclua os testes aqui, inserindo erros na lista
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldname()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}