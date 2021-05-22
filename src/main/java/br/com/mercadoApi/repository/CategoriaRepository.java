package br.com.mercadoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mercadoApi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
