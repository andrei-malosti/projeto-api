package com.projeto.spring.domain.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projeto.spring.api.dto.pedido.PedidoRequestDTO;
import com.projeto.spring.api.dto.pedido.PedidoResponseDTO;
import com.projeto.spring.api.exception.EntidadeEmUsoException;
import com.projeto.spring.api.exception.EntidadeNaoEncontradaException;
import com.projeto.spring.api.mapper.PedidoMapper;
import com.projeto.spring.domain.model.Cliente;
import com.projeto.spring.domain.model.FormaPagamento;
import com.projeto.spring.domain.model.Pedido;
import com.projeto.spring.domain.model.Produto;
import com.projeto.spring.domain.repository.ClienteRepository;
import com.projeto.spring.domain.repository.FormaPagamentoRepository;
import com.projeto.spring.domain.repository.PedidoRepository;
import com.projeto.spring.domain.repository.ProdutoRepository;

@Service
public class PedidoService {

	private final PedidoRepository repo;
	
	private final ProdutoRepository produtoRepository;
	
	private final ClienteRepository clienteRepository;
	
	private final FormaPagamentoRepository formaPagamentoRepository;
	
	private final PedidoMapper mapper;
	
	public PedidoService(PedidoRepository repo,ProdutoRepository produtoRepository,ClienteRepository clienteRepository,FormaPagamentoRepository formaPagamentoRepository,PedidoMapper mapper) {
		this.repo = repo;
		this.produtoRepository = produtoRepository;
		this.clienteRepository = clienteRepository;
		this.formaPagamentoRepository = formaPagamentoRepository;
		this.mapper = mapper;
	}
	
	@Cacheable(value = "pedido", key = "#id")
	public PedidoResponseDTO buscarPorId(Long id) {
		Pedido pedido = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Pedido de id %d não encontrado", id)));
		return mapper.toResponseDTO(pedido);
	}
	
	public PedidoResponseDTO salvar(PedidoRequestDTO dto) {
		Pedido pedido = new Pedido();
		Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow(() -> new EntidadeNaoEncontradaException("cliente não encontrado"));
		pedido.setCliente(cliente);
		
		if(dto.produtosId().isEmpty()) {
			throw new IllegalArgumentException("IDs de produtos não devem ser nulos ou vazios");
		}
		List<Produto> produtos = produtoRepository.findAllById(dto.produtosId());
		pedido.setProdutos(produtos);
		
		BigDecimal valorTotalProdutos = pedido.getProdutos().stream().map(Produto::getPreco).reduce(BigDecimal.ZERO, BigDecimal::add);
		pedido.setValorCompra(valorTotalProdutos);
		
		FormaPagamento formaPagamento = formaPagamentoRepository.findById(dto.formaPagamentoId()).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Forma de pagamento de id %d não encontrado", dto.formaPagamentoId())));
		pedido.setPagamento(formaPagamento);
		
		repo.save(pedido);
		
		return mapper.toResponseDTO(pedido);
	}
	
	public PedidoResponseDTO atualizar(Long id, PedidoRequestDTO dto) {
		Pedido pedido = repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Pedido de id %d não encontrado", id)));
		
		Cliente cliente = clienteRepository.findById(dto.clienteId())
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cliente de id %d não encontrado", id)));
		pedido.setCliente(cliente);
		
		if (dto.produtosId().isEmpty()) {
			throw new IllegalArgumentException("IDs de produtos não devem ser nulos ou vazios");
		}
		
		List<Produto> produtos = produtoRepository.findAllById(dto.produtosId());
		pedido.setProdutos(produtos);
		
		repo.save(pedido);
		
		return mapper.toResponseDTO(pedido);

	}
	
	public void deletar(Long id) {
		try {
			Pedido pedido = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Pedido de id %d não encontrado", id)));
			repo.deleteById(pedido.getId());
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Pedido de id %d está em uso!", id));
		}
	}
}


