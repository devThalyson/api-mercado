package br.com.mercadoApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.mercadoApi.dto.CategoriaDTO;
import br.com.mercadoApi.model.Categoria;
import br.com.mercadoApi.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository repository;

	// Listando todas as categorias
	public List<CategoriaDTO> getCategorias() {
		List<Categoria> categorias = repository.findAll();

		List<CategoriaDTO> list = new ArrayList<>();

		for (Categoria c : categorias) {
			list.add(CategoriaDTO.create(c));
		}

		return list;
	}

	// Salvando uma nova categoria
	public CategoriaDTO saveCategoria(Categoria categoria) {
		Assert.isNull(categoria.getId(), "Não foi possivel salvar a categoria");

		return CategoriaDTO.create(repository.save(categoria));
	}

	// Listar uma categoria especifica
	public Optional<CategoriaDTO> getCategoriaById(Long id) {
		Optional<Categoria> categoria = repository.findById(id);
		if (categoria.isPresent()) {
			return Optional.of(CategoriaDTO.create(categoria.get()));
		} else {
			return null;
		}

	}

	// Atualizando uma Categoria
	public CategoriaDTO update(Categoria categoria, Long id) {

		// Buscando a categoria no banco de dados
		Optional<Categoria> optional = repository.findById(id);

		if (optional.isPresent()) {
			Categoria db = optional.get();

			// Copiando as propriedades
			db.setNome(categoria.getNome());
			db.setImagem(categoria.getImagem());
			db.setAtiva(categoria.getAtiva());
			db.setProdutos(categoria.getProdutos());

			repository.save(db);
			return CategoriaDTO.create(db);
		} else {
			return null;
		}

	}

	// Deletando uma Categoria
	public void delete(Long id) {
		repository.deleteById(id);

	}

}
