package br.com.mercadoApi.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.mercadoApi.model.Categoria;
import br.com.mercadoApi.model.Compra;
import br.com.mercadoApi.model.Produto;
import lombok.Data;

@Data
public class ProdutoDTO {

	private Long id;

	private String nome;
	private String imagem;
	private String descricao;
	private String medida;
	private String peso;

	private double preco;
	private double minimoVenda;

	private int indisponivel;

	private Categoria categoria;

	@JsonIgnore
	private List<Compra> compras;

	public static ProdutoDTO create(Produto p) {
		ModelMapper modelMapper = new ModelMapper();

		ProdutoDTO dto = modelMapper.map(p, ProdutoDTO.class);

		return dto;
	}

}
