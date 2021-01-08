package com.dev.dsdelivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dev.dsdelivery.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query("SELECT DISTINCT p FROM Pedido p JOIN FETCH p.products "
			+ " WHERE p.status = 0 ORDER BY p.moment ASC")
	List<Pedido> findOrdersWithProducts(); 
	
	
	
}
