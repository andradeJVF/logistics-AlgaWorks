package com.logistics.api.controller;

import java.util.List;

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

import com.logistics.api.model.Cliente;
import com.logistics.api.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> getAll() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> getById(@PathVariable Long clienteId) { // pathVariable vincula o parametro do método
																			// a variavel do getMapping

		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente cadastrar(@RequestBody Cliente cliente) { // requestBody vincula o parametro do metodo ao corpo da
																// requisição
		return clienteRepository.save(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletar(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(clienteId);
		return ResponseEntity.noContent().build();
	}
}
