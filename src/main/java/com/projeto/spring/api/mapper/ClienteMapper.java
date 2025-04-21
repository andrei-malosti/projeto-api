package com.projeto.spring.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projeto.spring.api.dto.cliente.ClienteRequestDTO;
import com.projeto.spring.api.dto.cliente.ClienteResponseDTO;
import com.projeto.spring.domain.model.Cliente;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
	
	@Mapping(target = "pedido", ignore = true)
	Cliente toEntity(ClienteRequestDTO dto);
	
	ClienteResponseDTO toDTO(Cliente cliente);

}
