package br.com.mercadoApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.mercadoApi.dto.ClienteDTO;
import br.com.mercadoApi.model.Cliente;
import br.com.mercadoApi.model.Role;
import br.com.mercadoApi.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	@Autowired
	RoleService rservice;

	// Listando todas os clientes
	public List<ClienteDTO> getClientes() {
		List<Cliente> clientes = repository.findAll();

		List<ClienteDTO> list = new ArrayList<>();

		for (Cliente c : clientes) {
			list.add(ClienteDTO.create(c));
		}

		return list;
	}

	// Salvando um novo cliente
	public ClienteDTO saveCliente(Cliente cliente) {
		Assert.isTrue(repository.findByEmail(cliente.getEmail()) == null, "Esse e-mail já está cadastrado.");
		;

		List<Role> roles = new ArrayList<Role>();
		roles.add(rservice.getRoleById(1L).get());
		cliente.setRoles(roles);
		return ClienteDTO.create(repository.save(cliente));
	}

	// Listar um cliente especifico
	public Optional<ClienteDTO> getClienteById(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		if (cliente.isPresent()) {
			return Optional.of(ClienteDTO.create(cliente.get()));
		} else {
			return null;
		}

	}

	// Atualizando um Cliente
	public ClienteDTO update(Cliente cliente, Long id) {
		// Buscando o cliente no banco de dados
		Optional<Cliente> optional = repository.findById(id);

		if (optional.isPresent()) {
			Cliente db = optional.get();

			// Copiando as propriedades
			db.setNome(cliente.getNome());

			db.setEmail(cliente.getEmail());
			db.setEndereco(cliente.getEndereco());
			db.setSenha(cliente.getSenha());
			db.setTelefone(cliente.getTelefone());
			// db.setRoles(cliente.getRoles());
			repository.save(db);
			return ClienteDTO.create(db);
		} else {
			return null;
		}

	}

	// Deletando um Cliente
	public void delete(Long id) {
		repository.deleteById(id);

	}

	public void updateResetPasswordToken(String token, String email) {

		Cliente cliente = repository.findByEmail(email);

		cliente.setResetPasswordToken(token);
		repository.save(cliente);
		ClienteDTO.create(cliente);
	}

	public Cliente getByResetPasswordToken(String token) {
		return repository.findByResetPasswordToken(token);
	}

	public void updatePassword(Cliente cliente, String newPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(newPassword);
		cliente.setSenha(encodedPassword);

		cliente.setResetPasswordToken(null);
		repository.save(cliente);
	}

	public Cliente getClienteByEmail(String email) {
		Cliente cliente = repository.findByEmail(email);
		return cliente;
	}

}
