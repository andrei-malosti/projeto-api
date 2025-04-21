package com.projeto.spring.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projeto.spring.api.dto.produto.ProdutoRequestDTO;
import com.projeto.spring.api.dto.produto.ProdutoResponseDTO;
import com.projeto.spring.api.exception.EntidadeEmUsoException;
import com.projeto.spring.api.exception.EntidadeNaoEncontradaException;
import com.projeto.spring.api.mapper.ProdutoMapper;
import com.projeto.spring.domain.model.Produto;
import com.projeto.spring.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private ProdutoMapper mapper;
	
	public ProdutoResponseDTO buscarPorId(Long id) {
		Produto produto = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Produto de id %d não encontrado", id)));
		return mapper.toResponseDTO(produto);
	}
	
	public ProdutoResponseDTO salvar(ProdutoRequestDTO request) {
		Produto produto = mapper.toEntity(request);
		repo.save(produto);
		return mapper.toResponseDTO(produto);
	}
	
	public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
		Produto produto = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Produto de id %d não encontrado", id)));
		Produto produtoCopiar = mapper.toEntity(dto);
		produto.setNome(produtoCopiar.getNome());
		produto.setDescricao(produtoCopiar.getDescricao());
		produto.setPreco(produtoCopiar.getPreco());
		repo.save(produto);
		return mapper.toResponseDTO(produto);
	}
	
	public void deletar(Long id) {
		try {
			Produto produto = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Produto de id %d não encontrado", id)));
			repo.deleteById(produto.getId());
		} catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Produto de id %d está em uso!", id));
		}
	}
}
