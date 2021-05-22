package br.com.mercadoApi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mercadoApi.model.Cliente;
import br.com.mercadoApi.repository.ClienteRepository;

@Service(value = "userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente cliente = repository.findByEmail(email);

		if (cliente == null) {
			throw new UsernameNotFoundException("user not found");
		}

		return cliente;
	}

}
