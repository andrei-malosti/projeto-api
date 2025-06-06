package com.projeto.spring.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projeto.spring.api.dto.cliente.ClienteRequestDTO;
import com.projeto.spring.api.dto.cliente.ClienteResponseDTO;
import com.projeto.spring.api.exception.EntidadeEmUsoException;
import com.projeto.spring.api.exception.EntidadeNaoEncontradaException;
import com.projeto.spring.api.mapper.ClienteMapper;
import com.projeto.spring.domain.model.Cliente;
import com.projeto.spring.domain.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository repo;
	
	private final ClienteMapper clienteMapper;
	
	public ClienteService(ClienteRepository repo, ClienteMapper clienteMapper) {
		this.repo = repo;
		this.clienteMapper = clienteMapper;
	}

	public ClienteResponseDTO salvar(ClienteRequestDTO request) {
		Cliente cliente = clienteMapper.toEntity(request);
		Cliente salvo = repo.save(cliente);
		return clienteMapper.toDTO(salvo);
	}
	
	public List<ClienteResponseDTO> listarCliente(){
		return repo.findAll().stream().map(clienteMapper::toDTO).collect(Collectors.toList());
	}
	
	@Cacheable(value = "cliente", key = "#id")
	public ClienteResponseDTO buscarPorId(Long id) {
		Cliente cliente = repo.findById(id)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cliente de id %d não encontrado", id)));
		return clienteMapper.toDTO(cliente);
	}
	
	@Cacheable(value ="clientes", key = "#id")
	public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dto) {
		Cliente cliente = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cliente de id %d não encontrado", id)));
		cliente.setNome(dto.nome());
		cliente.setCpf(dto.cpf());
		cliente.setSenha(dto.senha());
		repo.save(cliente);
		return clienteMapper.toDTO(cliente);
	}
	
	public void excluirCliente(Long id) {
		try {
			Cliente cliente = repo.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format("Cliente de id %d não encontrado", id)));
			repo.deleteById(cliente.getId());
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cliente de id %d está em uso!", id));
		}
	}
}
