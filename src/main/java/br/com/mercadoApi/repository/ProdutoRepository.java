package br.com.mercadoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mercadoApi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
