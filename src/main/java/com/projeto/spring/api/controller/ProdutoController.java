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

import com.projeto.spring.api.dto.produto.ProdutoRequestDTO;
import com.projeto.spring.api.dto.produto.ProdutoResponseDTO;
import com.projeto.spring.api.exception.EntidadeEmUsoException;
import com.projeto.spring.api.exception.EntidadeNaoEncontradaException;
import com.projeto.spring.domain.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;

	@GetMapping("/{produtoId}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long produtoId) {
		try {
			ProdutoResponseDTO dto = service.buscarPorId(produtoId);
			return ResponseEntity.ok().body(dto);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProdutoResponseDTO salvar(@RequestBody ProdutoRequestDTO dto){
		return service.salvar(dto);
	}
	
	@PutMapping("/{produtoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long produtoId, @RequestBody ProdutoRequestDTO dto){
		try {
			return ResponseEntity.ok().body(service.atualizar(produtoId, dto));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/{produtoId}")
	public ResponseEntity<?> deletar(@PathVariable Long produtoId){
		try {
			service.deletar(produtoId);   
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
