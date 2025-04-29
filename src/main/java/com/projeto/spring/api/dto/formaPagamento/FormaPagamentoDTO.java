package com.projeto.spring.api.dto.formaPagamento;

import java.io.Serializable;

import com.projeto.spring.domain.enumerado.TipoPagamento;

public record FormaPagamentoDTO(Long id,TipoPagamento tipo) implements Serializable{

}
