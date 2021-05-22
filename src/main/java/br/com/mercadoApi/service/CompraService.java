package br.com.mercadoApi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.mercadoApi.dto.CompraDTO;
import br.com.mercadoApi.model.Compra;
import br.com.mercadoApi.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	CompraRepository repository;

	// Listando todas as compras
	public List<CompraDTO> getCompras() {
		List<Compra> compras = repository.findAll();

		List<CompraDTO> list = new ArrayList<>();

		for (Compra c : compras) {
			list.add(CompraDTO.create(c));
		}

		return list;
	}

	// Salvando uma nova compra
	public CompraDTO saveCompra(Compra compra) {
		Assert.isNull(compra.getId(), "Não foi possivel salvar a compra");

		return CompraDTO.create(repository.save(compra));
	}

	// Listar uma compra especifica
	public Optional<CompraDTO> getCompraById(Long id) {
		Optional<Compra> compra = repository.findById(id);
		if (compra.isPresent()) {
			return Optional.of(CompraDTO.create(compra.get()));
		} else {
			return null;
		}

	}

	// Atualizando uma Compra
	public CompraDTO update(Compra compra, Long id) {

		// Buscando a compra no banco de dados
		Optional<Compra> optional = repository.findById(id);

		if (optional.isPresent()) {
			Compra db = optional.get();

			// Copiando as propriedades
			db.setCliente(compra.getCliente());
			db.setDataDeCompra(compra.getDataDeCompra());
			db.setPreco(compra.getPreco());
			db.setProdutos(compra.getProdutos());
			db.setStatus(compra.getStatus());
			db.setRecebimento(compra.getRecebimento());
			db.setMotivoRejeicao(compra.getMotivoRejeicao());
			db.setFormaDePagamento(compra.getFormaDePagamento());
			db.setTroco(compra.getTroco());
			db.setTaxaDeEntrega(compra.getTaxaDeEntrega());

			repository.save(db);
			return CompraDTO.create(db);
		} else {
			return null;
		}

	}

	// Deletando uma Compra
	public void delete(Long id) {
		repository.deleteById(id);

	}

	// Listando compras de um usuário específico
	public List<CompraDTO> getComprasByCliente(Long id) {
		List<Compra> compras = repository.findByCliente(id);

		List<CompraDTO> list = new ArrayList<>();

		for (Compra c : compras) {
			list.add(CompraDTO.create(c));
		}
		return list;
	}

	// Listando compras pendentes
	public List<CompraDTO> getComprasByStatus(String status) {
		List<Compra> compras = repository.findByStatus(status);

		List<CompraDTO> list = new ArrayList<>();

		for (Compra c : compras) {
			list.add(CompraDTO.create(c));
		}
		return list;
	}

}
