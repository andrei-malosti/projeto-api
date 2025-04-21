package com.projeto.spring.api.mapper;

import org.mapstruct.Mapper;

import com.projeto.spring.api.dto.formaPagamento.FormaPagamentoDTO;
import com.projeto.spring.domain.model.FormaPagamento;

@Mapper(componentModel = "spring")
public interface FormaPagamentoMapper {
	
	FormaPagamento toEntity(FormaPagamentoDTO dto);
	
	FormaPagamentoDTO toDTO(FormaPagamento entity);
}
