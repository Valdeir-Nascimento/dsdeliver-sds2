package com.dev.dsdelivery.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.dsdelivery.dto.ProdutoDTO;
import com.dev.dsdelivery.entity.Produto;
import com.dev.dsdelivery.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//readOnly:  operação somente de leitura
	@Transactional(readOnly = true)
	public List<ProdutoDTO> findAll() {
		List<Produto> produtos = produtoRepository.findAllByOrderByNameAsc();
		return produtos.stream().map(p -> new ProdutoDTO(p)).collect(Collectors.toList());
	}
	
}
