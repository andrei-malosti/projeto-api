package com.projeto.spring.api.dto.cliente;

import java.io.Serializable;

public record ClienteResponseDTO(
		Long id,String nome) implements Serializable{
}
