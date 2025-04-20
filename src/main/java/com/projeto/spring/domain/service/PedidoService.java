package com.projeto.spring.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.spring.api.dto.pedido.PedidoRequestDTO;
import com.projeto.spring.api.dto.pedido.PedidoResponseDTO;
import com.projeto.spring.api.exception.EntidadeNaoEncontradaException;
import com.projeto.spring.api.mapper.PedidoMapper;
import com.projeto.spring.domain.model.Cliente;
import com.projeto.spring.domain.model.Pedido;
import com.projeto.spring.domain.model.Produto;
import com.projeto.spring.domain.repository.ClienteRepository;
import com.projeto.spring.domain.repository.PedidoRepository;
import com.projeto.spring.domain.repository.ProdutoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoMapper mapper;
	
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
		Pedido pedido = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Pedido de id %d não encontrado", id)));
		repo.deleteById(pedido.getId());
	}
}


