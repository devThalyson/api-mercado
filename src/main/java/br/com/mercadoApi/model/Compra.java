package br.com.mercadoApi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@NamedNativeQuery(name = "Compra.findByCliente", query = "select * from compra where cliente_id = ?", resultClass = Compra.class)
@NamedNativeQuery(name = "Compra.findByStatus", query = "select * from compra where status = ?", resultClass = Compra.class)
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String status;
	private String recebimento;
	private String formaDePagamento;
	private String motivoRejeicao;

	private double preco;
	private double taxaDeEntrega;
	private double troco;

	private Date dataDeCompra;

	@ManyToMany
	private List<Produto> produtos = new ArrayList<Produto>();

	@JsonIgnoreProperties({ "roles" })
	@ManyToOne
	private Cliente cliente;

}
