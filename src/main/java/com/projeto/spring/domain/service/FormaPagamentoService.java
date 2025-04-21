package com.projeto.spring.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projeto.spring.api.dto.formaPagamento.FormaPagamentoDTO;
import com.projeto.spring.api.exception.EntidadeEmUsoException;
import com.projeto.spring.api.exception.EntidadeNaoEncontradaException;
import com.projeto.spring.api.mapper.FormaPagamentoMapper;
import com.projeto.spring.domain.model.FormaPagamento;
import com.projeto.spring.domain.repository.FormaPagamentoRepository;

@Service
public class FormaPagamentoService {

	@Autowired
	private FormaPagamentoRepository repo;
	
	@Autowired
	private FormaPagamentoMapper mapper;
	
	
	public List<FormaPagamentoDTO> buscarTodos() {
		return repo.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
	}
	
	public FormaPagamentoDTO salvar(FormaPagamentoDTO pagamentoDTO) {
		FormaPagamento pagamento = mapper.toEntity(pagamentoDTO);
		repo.save(pagamento);
		return mapper.toDTO(pagamento);
	}
	
	public FormaPagamentoDTO buscarPorId(Long id) {
		FormaPagamento pagamento = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Forma de pagamento de id %d não encontrada", id)));
		return mapper.toDTO(pagamento);
	}
	
	public FormaPagamentoDTO atualizar(Long id, FormaPagamentoDTO pagamentoDTO) {
		FormaPagamento pagamento = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Forma de pagamento de id %d não encontrada", id)));
		pagamento.setTipo(pagamentoDTO.tipo());
		repo.save(pagamento);
		return mapper.toDTO(pagamento);
	}
	
	public void deletar(Long id) {
		try {
			FormaPagamento pagamento = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Forma de pagamento de id %d não encontrada", id)));
			repo.deleteById(pagamento.getId());
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Forma de pagamento de id %d está em uso!", id));
		}
	}
}
