package com.projeto.spring.api.mapper;

import org.mapstruct.Mapper;

import com.projeto.spring.api.dto.pedido.PedidoResponseDTO;
import com.projeto.spring.domain.model.Pedido;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
	
	PedidoResponseDTO toResponseDTO(Pedido pedido);
}
