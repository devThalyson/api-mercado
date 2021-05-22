package br.com.mercadoApi.dto;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.mercadoApi.model.Cliente;
import br.com.mercadoApi.model.Compra;
import br.com.mercadoApi.model.Produto;
import lombok.Data;

@Data
public class CompraDTO {

	private Long id;

	private String status;
	private String recebimento;
	private String formaDePagamento;
	private String motivoRejeicao;

	private double preco;
	private double taxaDeEntrega;
	private double troco;

	private Date dataDeCompra;

	private List<Produto> produtos;

	private Cliente cliente;

	public static CompraDTO create(Compra c) {
		ModelMapper modelMapper = new ModelMapper();

		CompraDTO dto = modelMapper.map(c, CompraDTO.class);

		return dto;
	}

}
