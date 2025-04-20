package com.projeto.spring.api.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.spring.api.dto.pedido.PedidoRequestDTO;
import com.projeto.spring.api.dto.pedido.PedidoResponseDTO;
import com.projeto.spring.api.exception.EntidadeNaoEncontradaException;
import com.projeto.spring.domain.service.PedidoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;

	@GetMapping("/{pedidoId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long pedidoId){
		try {
			return ResponseEntity.ok().body(service.buscarPorId(pedidoId));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> salvar(@RequestBody @Valid PedidoRequestDTO request) {
		try {
			PedidoResponseDTO dto = service.salvar(request);
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{pedidoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long pedidoId, @RequestBody PedidoRequestDTO dto){
		try {
			return ResponseEntity.ok().body(service.atualizar(pedidoId, dto));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{pedidoId}")
	public ResponseEntity<?> deletar(@PathVariable Long pedidoId){
		try {
			service.deletar(pedidoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
