package com.projeto.spring.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.spring.api.dto.cliente.ClienteRequestDTO;
import com.projeto.spring.api.dto.cliente.ClienteResponseDTO;
import com.projeto.spring.api.exception.EntidadeNaoEncontradaException;
import com.projeto.spring.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<ClienteResponseDTO>> listar(){
		return ResponseEntity.ok().body(Optional.of(service.listarCliente()).orElseThrow(() -> new RuntimeException("nenhum valor na lista")));
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody ClienteRequestDTO dto) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long clienteId) {
		try {
			return ResponseEntity.ok().body(service.buscarPorId(clienteId));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long clienteId,@RequestBody ClienteRequestDTO dto) {
		try {
			return ResponseEntity.ok().body(service.atualizarCliente(clienteId, dto));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<?> deletar(@PathVariable Long clienteId){
		try {
			service.excluirCliente(clienteId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
