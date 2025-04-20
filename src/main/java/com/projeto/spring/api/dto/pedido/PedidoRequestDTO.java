package com.projeto.spring.api.dto.pedido;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PedidoRequestDTO(@NotNull Long clienteId,@NotEmpty List<@NotNull Long> produtosId) {
}
