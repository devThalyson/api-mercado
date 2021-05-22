package br.com.mercadoApi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mercadoApi.model.Role;
import br.com.mercadoApi.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository repository;

	public Optional<Role> getRoleById(Long id) {
		Optional<Role> role = repository.findById(id);
		if (role.isPresent()) {
			return Optional.of(role.get());
		} else {
			return null;
		}
	}

}
