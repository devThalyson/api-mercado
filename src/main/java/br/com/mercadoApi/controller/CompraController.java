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

import br.com.mercadoApi.dto.CompraDTO;
import br.com.mercadoApi.model.Compra;
import br.com.mercadoApi.service.CompraService;

@RestController
@RequestMapping("api/v1/compras")
public class CompraController {

	@Autowired
	CompraService service;

	// Listar todos as compras;
	@GetMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity get() {
		return ResponseEntity.ok(service.getCompras());
	}

	// Listar uma compra por ID;
	@GetMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<CompraDTO> compra = service.getCompraById(id);

		if (compra.isPresent()) {
			return ResponseEntity.ok(compra.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Salvar uma compra;
	@PostMapping
	public ResponseEntity post(@RequestBody Compra compra) {

		CompraDTO t = service.saveCompra(compra);

		URI location = getUri(t.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	// Atualizar uma compra;
	@PutMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Compra compra) {

		CompraDTO t = service.update(compra, id);

		if (t != null) {
			return ResponseEntity.ok(t);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	// Deletar uma compra;
	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);

		return ResponseEntity.ok().build();
	}

	// Listar compras de um cliente específico
	@GetMapping("/cliente/{id}")
	public ResponseEntity getComprasByCliente(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.getComprasByCliente(id));
	}

	// Listar compras por status

	@GetMapping("/status/{status}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity getComprasByStatus(@PathVariable("status") String status) {
		return ResponseEntity.ok(service.getComprasByStatus(status));
	}
}
