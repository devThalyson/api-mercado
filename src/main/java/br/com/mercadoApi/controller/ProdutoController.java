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

import br.com.mercadoApi.dto.ProdutoDTO;
import br.com.mercadoApi.model.Produto;
import br.com.mercadoApi.service.ProdutoService;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService service;

	// Listar todos os produtos;
	@GetMapping
	public ResponseEntity get() {
		return ResponseEntity.ok(service.getProdutos());
	}

	// Listar um produto por ID;
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<ProdutoDTO> produto = service.getProdutoById(id);

		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Salvar um produto;
	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity post(@RequestBody Produto produto) {

		ProdutoDTO t = service.saveProduto(produto);

		URI location = getUri(t.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	// Atualizar um produto;
	@PutMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Produto produto) {

		ProdutoDTO t = service.update(produto, id);

		if (t != null) {
			return ResponseEntity.ok(t);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	// Deletar um produto;
	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
