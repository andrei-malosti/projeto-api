package com.projeto.spring.api.controller;

import java.util.List;

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

import com.projeto.spring.api.dto.formaPagamento.FormaPagamentoDTO;
import com.projeto.spring.api.exception.EntidadeEmUsoException;
import com.projeto.spring.api.exception.EntidadeNaoEncontradaException;
import com.projeto.spring.domain.service.FormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	private final FormaPagamentoService service;
	
	public FormaPagamentoController(FormaPagamentoService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<FormaPagamentoDTO> salvar(@RequestBody FormaPagamentoDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
	}
	
	@GetMapping
	public List<FormaPagamentoDTO> listar() {
		return service.buscarTodos();
	}
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<?> buscar(@PathVariable Long formaPagamentoId){
		try {

			return ResponseEntity.ok().body(service.buscarPorId(formaPagamentoId));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{formaPagamentoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamentoDTO dto){
		try {
			return ResponseEntity.ok().body(service.atualizar(formaPagamentoId, dto));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	public ResponseEntity<?> deletar(@PathVariable Long formaPagamentoId){
		try {
			service.deletar(formaPagamentoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
}
