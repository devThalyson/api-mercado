package br.com.mercadoApi.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.mercadoApi.dto.ClienteDTO;
import br.com.mercadoApi.model.Cliente;
import br.com.mercadoApi.service.ClienteService;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;

	// Listar todos os clientes;
	@Secured({ "ROLE_ADMIN" })
	@GetMapping
	public ResponseEntity get() {
		return ResponseEntity.ok(service.getClientes());
	}

	// Listar um cliente por ID;
	@Secured({ "ROLE_ADMIN" })
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<ClienteDTO> cliente = service.getClienteById(id);

		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Salvar um cliente;
	@PostMapping("/cadastro")
	public ResponseEntity post(@RequestBody Cliente cliente) {

		ClienteDTO t = service.saveCliente(cliente);

		URI location = getUri(t.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	// Atualizar um cliente;
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Cliente cliente) {

		ClienteDTO t = service.update(cliente, id);

		if (t != null) {
			return ResponseEntity.ok(t);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	// Deletar um cliente;
	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
