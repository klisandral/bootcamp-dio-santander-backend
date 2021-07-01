package com.bytebank.bootcamp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytebank.bootcamp.model.dto.StockDTO;
import com.bytebank.bootcamp.service.StockService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping(value = "/stock")
public class StockController {

	@Autowired
	private StockService service;

	@Operation(summary = "Cadastra um stock")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> save(@RequestBody @Valid StockDTO dto) {
		return ResponseEntity.ok(service.save(dto));
	}
	
	@Operation(summary = "Atualiza um stock")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> update(@RequestBody @Valid StockDTO dto) {
		return ResponseEntity.ok(service.update(dto));
	}
	
	@Operation(summary = "Deleta um stock por id")
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> delete(@PathVariable Long id) {
		return ResponseEntity.ok(service.delete(id));
	}
	
	@Operation(summary = "Pega todos os stocks cadastrados")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@Operation(summary = "Pega um stock por id")
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StockDTO> findById(@PathVariable @Valid Long id) {
		return ResponseEntity.ok(service.findById(id));
	}
	
	@Operation(summary = "Pega os stocks do dia")
	@GetMapping(value = "/today", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StockDTO>> findByToday() {
		return ResponseEntity.ok(service.findByToday());
	}

}
