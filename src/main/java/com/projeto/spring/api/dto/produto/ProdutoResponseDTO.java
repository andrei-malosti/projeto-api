package com.projeto.spring.api.dto.produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(Long id,String nome, BigDecimal preco) {

}
