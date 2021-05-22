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

import br.com.mercadoApi.dto.CategoriaDTO;
import br.com.mercadoApi.model.Categoria;
import br.com.mercadoApi.service.CategoriaService;

@RestController
@RequestMapping("api/v1/categorias")
public class CategoriaController {

	@Autowired
	CategoriaService service;

	// Listar todos as categorias;
	@GetMapping
	public ResponseEntity get() {
		return ResponseEntity.ok(service.getCategorias());
	}

	// Listar uma categoria por ID;
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<CategoriaDTO> categoria = service.getCategoriaById(id);

		if (categoria.isPresent()) {
			return ResponseEntity.ok(categoria.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// Salvar uma categoria;
	@PostMapping
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity post(@RequestBody Categoria categoria) {

		CategoriaDTO t = service.saveCategoria(categoria);

		URI location = getUri(t.getId());

		return ResponseEntity.created(location).build();

	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	// Atualizar uma categoria;
	@PutMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Categoria categoria) {

		CategoriaDTO t = service.update(categoria, id);

		if (t != null) {
			return ResponseEntity.ok(t);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	// Deletar uma categoria;
	@DeleteMapping("/{id}")
	@Secured({ "ROLE_ADMIN" })
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
