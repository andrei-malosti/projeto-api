package com.projeto.spring.api.dto.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.projeto.spring.api.dto.cliente.ClienteResponseDTO;
import com.projeto.spring.api.dto.formaPagamento.FormaPagamentoDTO;
import com.projeto.spring.api.dto.produto.ProdutoResponseDTO;

public record PedidoResponseDTO(Long id, ClienteResponseDTO cliente, List<ProdutoResponseDTO> produtos,FormaPagamentoDTO pagamento, BigDecimal valorCompra) implements Serializable{
}
  