package br.com.mercadoApi.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.mercadoApi.model.Categoria;
import br.com.mercadoApi.model.Produto;
import lombok.Data;

@Data
public class CategoriaDTO {

	private Long id;

	private String nome;
	private String imagem;

	private int ativa;

	@JsonIgnoreProperties({ "categoria" })
	private List<Produto> produtos;

	public static CategoriaDTO create(Categoria c) {
		ModelMapper modelMapper = new ModelMapper();

		CategoriaDTO dto = modelMapper.map(c, CategoriaDTO.class);

		return dto;
	}

}
