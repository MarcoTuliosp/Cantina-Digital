# Descobertas

## As 5 Perguntas de Descoberta (Respondidas)

1. **Estrela Guia:** Qual o resultado único?
   → O cliente faz o pedido de marmitex (via canais), o sistema registra no banco, a cozinha visualiza a fila e o relatório é fechado ao final do dia.

2. **Integrações:**
   → Canais de pedido via WhatsApp, site e balcão. Webhooks do n8n atuarão como pontes e integradores (ex: Twilio/Z-API para o WhatsApp).

3. **Fonte da Verdade:**
   → Todos os dados consolidados e a persistência final de pedidos e clientes ficarão no banco PostgreSQL (hospedado no **Neon.tech** — cloud gratuito).

4. **Payload Final:**
   → Relatórios gerados a partir do banco e despachados como mensagens estruturadas por WhatsApp ou e-mail, controlados pelos fluxos Cron do n8n.

5. **Regras do Negócio:**
   → Pedido mínimo de 1 unidade, processamento síncrono na fase inicial. Focado no domínio Cantina inicialmente, com suporte previsto para expansão.

## Restrições e Decisões Técnicas

### Decisão: Migração Docker Local → Cloud (2026-05-10)

**Motivo:** Máquina de desenvolvimento com apenas 4GB de RAM não suporta Docker Desktop (Windows) + containers PostgreSQL + Redis + n8n simultaneamente. Sistema travava completamente.

**Solução adotada:** Infraestrutura 100% cloud gratuita:
- **PostgreSQL** → Neon.tech (free tier: 0.5GB, scale-to-zero)
- **Backend Java** → Render.com (free tier: 512MB RAM, dorme após 15min)
- **Frontend React** → Vercel.com (hobby plan: 100GB bandwidth/mês)
- **n8n** → Render.com (futuro, quando chegar na Fase 4)

### Decisão: Remoção Temporária do Redis (2026-05-10)

**Motivo:** Simplificar a arquitetura inicial. O Upstash (Redis cloud) tem limite de 10.000 commands/dia no free tier, o que é suficiente mas desnecessário nesta fase.

**Impacto:** Pedidos são salvos diretamente no PostgreSQL via JPA (sem fila intermediária). Quando o sistema escalar, reintegrar Redis via Upstash para processamento assíncrono.

**Fluxo atual:** `Frontend → API Java → PostgreSQL (direto)`  
**Fluxo futuro:** `Frontend → API Java → Redis (fila) → Consumer → PostgreSQL`

### Decisão: Monorepo (2026-05-10)

**Motivo:** Organização clara do projeto para não se perder. Estrutura definida:
```
cantina-digital/
├── docs/       → Documentação
├── backend/    → Java Spring Boot
├── frontend/   → React + Vite
└── infra/      → Configs cloud
```

- O **n8n** será o "cérebro operacional" no envio de notificações e relatórios, não sendo responsável pela lógica transacional (que fica no Java).
