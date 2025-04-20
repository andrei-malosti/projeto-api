package com.projeto.spring.api.dto.produto;

import java.math.BigDecimal;

public record ProdutoRequestDTO(String nome, String descricao,BigDecimal preco) {

}
