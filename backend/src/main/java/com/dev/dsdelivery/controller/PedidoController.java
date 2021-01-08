package com.dev.dsdelivery.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dev.dsdelivery.dto.PedidoDTO;
import com.dev.dsdelivery.service.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> findAll(){
		List<PedidoDTO> pedidos = pedidoService.findAll();
		return ResponseEntity.ok().body(pedidos);
	}
	
	
	@PostMapping
	public ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO pedidoDTO) {
		pedidoDTO = pedidoService.insert(pedidoDTO);
		URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(pedidoDTO.getId())
					.toUri();
		
		return ResponseEntity.created(uri).body(pedidoDTO);
	}
	
	@PutMapping("/{id}/delivered")
	public ResponseEntity<PedidoDTO> setDelivered(@PathVariable Long id) {
		PedidoDTO pedidoDTO = pedidoService.setDelivered(id);
		return ResponseEntity.ok().body(pedidoDTO);
	}
	
	
}
