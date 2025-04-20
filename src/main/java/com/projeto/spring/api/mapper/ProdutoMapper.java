package com.projeto.spring.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.projeto.spring.api.dto.produto.ProdutoRequestDTO;
import com.projeto.spring.api.dto.produto.ProdutoResponseDTO;
import com.projeto.spring.domain.model.Produto;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
	
	@Mapping(target = "id", ignore = true)
	Produto toEntity(ProdutoRequestDTO dto);
	
	ProdutoResponseDTO toResponseDTO(Produto produto);

}
