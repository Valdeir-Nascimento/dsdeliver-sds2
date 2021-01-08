package com.dev.dsdelivery.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dev.dsdelivery.entity.Pedido;
import com.dev.dsdelivery.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String address;
	private Double latitude;
	private Double longitude;
	private Instant moment;
	private OrderStatus status;

	private List<ProdutoDTO> produtos = new ArrayList<>();

	PedidoDTO() {}
	
	public PedidoDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.address = pedido.getAddress();
		this.latitude = pedido.getLatitude();
		this.longitude = pedido.getLongitude();
		this.moment = pedido.getMoment();
		this.status = pedido.getStatus();
		
		produtos = pedido.getProducts()
					.stream()
					.map(ProdutoDTO::new)
					.collect(Collectors.toList());
		
	}
	

	public PedidoDTO(Long id, String address, Double latitude, Double longitude, Instant moment, OrderStatus status) {
		this.id = id;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
		this.moment = moment;
		this.status = status;
	}
	

	
	
}
