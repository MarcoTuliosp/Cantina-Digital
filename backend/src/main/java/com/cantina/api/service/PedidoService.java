package com.cantina.api.service;

import com.cantina.api.domain.Pedido;
import com.cantina.api.domain.StatusPedido;
import com.cantina.api.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido criarPedido(Pedido pedido) {
        pedido.setStatus(StatusPedido.RECEBIDO);

        // Vincular itens ao pedido e calcular total
        if (pedido.getItens() != null) {
            BigDecimal total = BigDecimal.ZERO;
            for (var item : pedido.getItens()) {
                item.setPedido(pedido);
                if (item.getPrecoUnitario() != null && item.getQuantidade() != null) {
                    total = total.add(item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
                }
            }
            pedido.setTotal(total);
        }

        // Persiste direto no PostgreSQL (sem fila Redis)
        // TODO: Quando escalar, adicionar Redis/fila aqui
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarPorStatus(StatusPedido status) {
        return pedidoRepository.findByStatus(status);
    }

    public Pedido atualizarStatus(UUID id, StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + id));
        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }
}
