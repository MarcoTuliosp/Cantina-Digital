# Plano de Tarefas (Blueprint)

## Fase 1: Visão e Lógica do Negócio
- [x] Validar as 5 Perguntas de Descoberta (Fase V)
- [x] Validar o Esquema de Dados em `constituicao.md`
- [x] Aprovar este Blueprint (`plano_de_tarefas.md`)

## Fase 2: Link (Conectividade e Infraestrutura — Cloud)
- [x] ~~Subir PostgreSQL via Docker~~ → Migrado para **Neon.tech** (cloud free)
- [x] ~~Subir Redis via Docker~~ → **Removido temporariamente** (será adicionado depois via Upstash)
- [ ] Criar conta no **Neon.tech** e configurar banco PostgreSQL
- [ ] Criar conta no **Render.com** e deployar backend Java
- [ ] Criar conta no **Vercel.com** e deployar frontend React
- [ ] Configurar variáveis de ambiente em cada plataforma
- [ ] Testar conectividade: Frontend → Backend → Banco

## Fase 3: Arquitetura (3 Camadas — SEM Redis)
- [x] Criar projeto Spring Boot e estruturar pacotes
- [x] Implementar modelos e repositórios
- [ ] ~~Implementar integração com Redis~~ → Adiado para fase de escalabilidade
- [ ] Criar endpoints da API de Pedidos (Java) — em andamento
- [ ] Criar projeto React (Frontend) — template Vite criado
- [ ] Implementar integração do Frontend com a API Java

## Fase 4: Estilo (Relatórios e UX)
- [ ] Configurar **n8n no Render** (free tier)
- [ ] Implementar fluxo no n8n de notificação de pedido
- [ ] Implementar fluxo de relatório diário (WhatsApp)
- [ ] Implementar fluxo de relatório mensal (Google Sheets)

## Fase 5: Gatilho (Deploy)
- [ ] Backend rodando no Render com health check ativo
- [ ] Frontend buildado e servido pelo Vercel
- [ ] Configurar crons e webhooks finais no n8n
- [ ] Validar endpoint `/api/pedidos/health`

## Fase Futura: Escalabilidade
- [ ] Adicionar Redis via **Upstash** (fila de pedidos)
- [ ] Implementar WebSocket para painel da cozinha em tempo real
- [ ] Módulo de Autenticação JWT + Spring Security
- [ ] Módulo SaaS com MercadoPago
