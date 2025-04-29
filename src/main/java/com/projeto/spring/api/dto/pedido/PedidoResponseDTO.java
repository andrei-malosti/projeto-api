package com.projeto.spring.api.dto.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.projeto.spring.api.dto.cliente.ClienteResponseDTO;
import com.projeto.spring.api.dto.produto.ProdutoResponseDTO;
import com.projeto.spring.domain.model.FormaPagamento;

public record PedidoResponseDTO(Long id, ClienteResponseDTO cliente, List<ProdutoResponseDTO> produtos,FormaPagamento pagamento, BigDecimal valorCompra) implements Serializable{
}
  