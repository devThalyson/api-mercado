package br.com.mercadoApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mercadoApi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Cliente findByEmail(String email);

	Cliente findByResetPasswordToken(String token);

}
