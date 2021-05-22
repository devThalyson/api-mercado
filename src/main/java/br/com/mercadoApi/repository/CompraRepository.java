package br.com.mercadoApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mercadoApi.model.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {

	List<Compra> findByCliente(Long id);

	List<Compra> findByStatus(String status);

}
