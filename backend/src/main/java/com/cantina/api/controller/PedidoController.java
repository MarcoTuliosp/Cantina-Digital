package com.cantina.api.controller;

import com.cantina.api.domain.Pedido;
import com.cantina.api.domain.StatusPedido;
import com.cantina.api.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // POST /api/pedidos → Recebe novo pedido
    @PostMapping
    public ResponseEntity<Pedido> criar(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.criarPedido(pedido));
    }

    // GET /api/pedidos → Lista todos
    @GetMapping
    public ResponseEntity<List<Pedido>> listar(
            @RequestParam(required = false) StatusPedido status) {
        if (status != null) {
            return ResponseEntity.ok(pedidoService.listarPorStatus(status));
        }
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    // PATCH /api/pedidos/{id}/status → Atualiza status
    @PatchMapping("/{id}/status")
    public ResponseEntity<Pedido> atualizarStatus(
            @PathVariable UUID id,
            @RequestParam StatusPedido status) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(id, status));
    }

    // GET /api/health → Health check para Render
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "servico", "cantina-api"));
    }
}
