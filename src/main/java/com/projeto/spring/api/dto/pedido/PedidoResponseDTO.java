package com.projeto.spring.api.dto.pedido;

import java.util.List;

import com.projeto.spring.api.dto.cliente.ClienteResponseDTO;
import com.projeto.spring.api.dto.produto.ProdutoResponseDTO;

public record PedidoResponseDTO(Long id, ClienteResponseDTO cliente, List<ProdutoResponseDTO> produtos) {
}
  