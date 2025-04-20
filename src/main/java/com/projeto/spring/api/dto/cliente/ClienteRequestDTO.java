package com.projeto.spring.api.dto.cliente;

import lombok.Builder;

@Builder
public record ClienteRequestDTO(
		Long id,
		String nome,
		String cpf,
		String senha) {

}
