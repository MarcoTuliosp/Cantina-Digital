package com.cantina.api.repository;

import com.cantina.api.domain.Pedido;
import com.cantina.api.domain.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

    List<Pedido> findByStatus(StatusPedido status);

    List<Pedido> findByTimestampBetween(LocalDateTime inicio, LocalDateTime fim);
}
