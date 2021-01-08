package com.dev.dsdelivery.dto;

import java.io.Serializable;

import com.dev.dsdelivery.entity.Produto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private Double price;
	private String description;
	private String imageUri;
	
	ProdutoDTO() {}
	
	public ProdutoDTO(Long id, String name, Double price, String description, String imageUri) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageUri = imageUri;
	}
	
	public ProdutoDTO(Produto produto) {
		id = produto.getId();
		name = produto.getName();
		price = produto.getPrice();
		description = produto.getDescription();
		imageUri = produto.getImageUri();
	}
	

}
