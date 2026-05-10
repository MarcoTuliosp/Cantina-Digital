# Constituição da Cantina Digital
## Lei do Projeto (Schemas, Regras, Invariantes)

### Esquema de Dados Central

```json
// ── Pedido (payload que entra no sistema) ──
{
  "idPedido": "uuid",
  "canal": "site | whatsapp | balcao",
  "timestamp": "ISO8601",
  "cliente": {
    "id": "uuid | null",
    "nome": "string",
    "telefone": "string",
    "tipo": "marmitex | sorveteria | acougue"
  },
  "itens": [
    {
      "produto": "string",
      "quantidade": "integer",
      "precoUnitario": "decimal",
      "observacao": "string | null"
    }
  ],
  "total": "decimal",
  "status": "RECEBIDO | EM_PREPARO | PRONTO | ENTREGUE | CANCELADO",
  "dominio": "cantina | sorveteria | acougue"
}

// ── Resposta de Confirmação ──
{
  "idPedido": "uuid",
  "numeroNaFila": "integer",
  "previsaoMinutos": "integer",
  "mensagem": "string"
}

// ── Relatório Diário ──
{
  "data": "YYYY-MM-DD",
  "totalPedidos": "integer",
  "faturamentoTotal": "decimal",
  "produtoMaisVendido": "string",
  "ticketMedio": "decimal",
  "pedidosPorStatus": { "entregue": "integer", "cancelado": "integer" }
}
```
