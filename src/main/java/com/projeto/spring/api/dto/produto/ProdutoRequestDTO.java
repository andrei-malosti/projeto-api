package com.projeto.spring.api.dto.produto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProdutoRequestDTO(String nome, String descricao,BigDecimal preco) implements Serializable{

}
