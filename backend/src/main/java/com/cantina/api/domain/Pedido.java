package com.cantina.api.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
@Data
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idPedido;

    @Enumerated(EnumType.STRING)
    private CanalPedido canal;

    @CreationTimestamp
    private LocalDateTime timestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemPedido> itens;

    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @Enumerated(EnumType.STRING)
    private DominioSistema dominio;
}
