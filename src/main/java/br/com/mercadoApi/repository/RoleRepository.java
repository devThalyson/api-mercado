package br.com.mercadoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mercadoApi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
