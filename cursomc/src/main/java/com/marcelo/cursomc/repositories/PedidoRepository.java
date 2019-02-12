package com.marcelo.cursomc.repositories;

import com.marcelo.cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {


   // Optional<Categoria> findById(Integer id);
}
