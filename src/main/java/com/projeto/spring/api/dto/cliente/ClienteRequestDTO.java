package com.projeto.spring.api.dto.cliente;

import java.io.Serializable;

public record ClienteRequestDTO (
		Long id,
		String nome,
		String cpf,
		String senha) implements Serializable {

}
