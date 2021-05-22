package br.com.mercadoApi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String imagem;
	private String descricao;
	private String medida;
	private String peso;

	private double preco;
	private double minimoVenda;

	private int indisponivel;

	@ManyToOne
	private Categoria categoria;

	@JsonIgnore
	@ManyToMany(mappedBy = "produtos")
	@Cascade(CascadeType.ALL)
	private List<Compra> compras = new ArrayList<Compra>();

}
