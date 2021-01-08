package com.dev.dsdelivery.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.dsdelivery.dto.PedidoDTO;
import com.dev.dsdelivery.dto.ProdutoDTO;
import com.dev.dsdelivery.entity.Pedido;
import com.dev.dsdelivery.entity.Produto;
import com.dev.dsdelivery.enums.OrderStatus;
import com.dev.dsdelivery.repository.PedidoRepository;
import com.dev.dsdelivery.repository.ProdutoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//readOnly: operação somente de leitura
	@Transactional(readOnly = true)
	public List<PedidoDTO> findAll() {
		List<Pedido> pedidos = pedidoRepository.findOrdersWithProducts();
		return pedidos.stream().map(pedido -> new PedidoDTO(pedido)).collect(Collectors.toList());
	}
	
	@Transactional
	public PedidoDTO insert(PedidoDTO pedidoDTO) {
		Pedido pedido = new Pedido(
				null, 
				pedidoDTO.getAddress(), 
				pedidoDTO.getLatitude(), 
				pedidoDTO.getLongitude(),
				Instant.now(), OrderStatus.PENDING);
		for (ProdutoDTO p : pedidoDTO.getProdutos()) {
			//getOne: Instancia mas n vai no banco de dados, ele salva as associações do pedido
			Produto produto = produtoRepository.getOne(p.getId());
			pedido.getProducts().add(produto);
		}
		
		pedido = pedidoRepository.save(pedido);
		return new PedidoDTO(pedido);
		
	}
	
	@Transactional
	public PedidoDTO setDelivered(Long id) {
		Pedido pedido = pedidoRepository.getOne(id);
		pedido.setStatus(OrderStatus.DELIVERED);
		pedido = pedidoRepository.save(pedido);
		
		return new PedidoDTO(pedido);
	}
	
}
