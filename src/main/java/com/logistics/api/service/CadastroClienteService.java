package com.logistics.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistics.api.exception.NegocioException;
import com.logistics.api.model.Cliente;
import com.logistics.api.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Transactional //declara que o método deve ser executado dentro de uma transação / tudo ou nada
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream().anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailEmUso) {
			throw new NegocioException("E-mail já em uso. Tente cadastro com outro e-mail");
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
}
