package com.projeto.spring.api.dto.produto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProdutoResponseDTO(Long id,String nome, BigDecimal preco) implements Serializable{

}
