package br.com.mercadoApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.mercadoApi.dto.ProdutoDTO;
import br.com.mercadoApi.model.Produto;
import br.com.mercadoApi.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

	// Listando todas os produtos
	public List<ProdutoDTO> getProdutos() {
		List<Produto> produtos = repository.findAll();

		List<ProdutoDTO> list = new ArrayList<>();

		for (Produto c : produtos) {
			list.add(ProdutoDTO.create(c));
		}

		return list;
	}

	// Salvando um novo produto
	public ProdutoDTO saveProduto(Produto produto) {
		Assert.isNull(produto.getId(), "Não foi possivel salvar o produto");

		return ProdutoDTO.create(repository.save(produto));
	}

	// Listar um produto especifico
	public Optional<ProdutoDTO> getProdutoById(Long id) {
		Optional<Produto> produto = repository.findById(id);
		if (produto.isPresent()) {
			return Optional.of(ProdutoDTO.create(produto.get()));
		} else {
			return null;
		}

	}

	// Atualizando um Produto
	public ProdutoDTO update(Produto produto, Long id) {

		// Buscando o produto no banco de dados
		Optional<Produto> optional = repository.findById(id);

		if (optional.isPresent()) {
			Produto db = optional.get();

			// Copiando as propriedades
			db.setNome(produto.getNome());
			db.setCategoria(produto.getCategoria());
			db.setDescricao(produto.getDescricao());
			db.setCompras(produto.getCompras());
			db.setImagem(produto.getImagem());
			db.setMedida(produto.getMedida());
			db.setMinimoVenda(produto.getMinimoVenda());
			db.setPeso(produto.getPeso());
			db.setPreco(produto.getPreco());
			db.setIndisponivel(produto.getIndisponivel());

			repository.save(db);
			return ProdutoDTO.create(db);
		} else {
			return null;
		}

	}

	// Deletando um Produto
	public void delete(Long id) {
		repository.deleteById(id);

	}
}
