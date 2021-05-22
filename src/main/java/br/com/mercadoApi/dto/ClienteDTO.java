package br.com.mercadoApi.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mercadoApi.model.Cliente;
import lombok.Data;

@Data
public class ClienteDTO {

	private Long id;

	private String nome;
	private String email;
	private String telefone;
	private String endereco;
	private String senha;
	private String token;
	private String resetPasswordToken;

	private List<String> roles;

	public static ClienteDTO create(Cliente c) {
		ModelMapper modelMapper = new ModelMapper();

		ClienteDTO dto = modelMapper.map(c, ClienteDTO.class);

		dto.roles = c.getRoles().stream().map(role -> role.getNome()).collect(Collectors.toList());

		return dto;
	}

	public static ClienteDTO create(Cliente cliente, String token) {
		ClienteDTO dto = create(cliente);
		dto.token = token;
		return dto;
	}

	public String toJson() throws JsonProcessingException {
		ObjectMapper m = new ObjectMapper();
		return m.writeValueAsString(this);
	}

}
