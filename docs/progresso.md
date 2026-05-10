# Progresso e Log de Execução

## [2026-05-05] - Inicialização (Protocolo 0)
- **Status:** Concluído ✅
- **Ações Realizadas:**
  - Criação da pasta de projeto `cantina-digital` dentro do workspace ativo.
  - Criação da `constituicao.md` definindo os schemas JSON dos payloads de Pedido e Relatório.
  - Criação do `plano_de_tarefas.md` com o checklist detalhado de todas as 5 Fases do projeto.
  - Criação de `descobertas.md` para consolidar as 5 perguntas de negócio e definições arquiteturais.

## [2026-05-07] - Fase 2: Infraestrutura Docker (Tentativa)
- **Status:** ❌ Falhou
- **Ações Realizadas:**
  - Tentativa de subir Docker Compose com PostgreSQL + Redis + n8n
  - Máquina com 4GB RAM travou completamente
  - Docker Desktop + 3 containers consumiam toda a memória disponível
- **Conclusão:** Docker local inviável para esta máquina

## [2026-05-10] - Migração para Cloud + Reorganização Monorepo
- **Status:** Concluído ✅
- **Ações Realizadas:**
  - Decisão: Migrar toda infraestrutura para cloud 100% gratuita
  - Reorganização do projeto em estrutura monorepo limpa:
    - `api/` → `backend/` (renomeado)
    - `conexoes/web/` → `frontend/` (movido)
    - `*.md` → `docs/` (documentação centralizada)
    - `infra/` criado com configs cloud
  - Remoção de arquivos Docker (docker-compose.yml)
  - Remoção do Redis (RedisPublisher.java deletado)
  - Refatoração do PedidoService para salvar direto no PostgreSQL
  - Atualização do pom.xml sem dependência Redis
  - application.properties configurado para Neon cloud
  - Criado Dockerfile para deploy no Render
  - Criado CorsConfig.java para CORS global
  - Criado vercel.json para deploy do frontend
  - Criado infra/README.md com guia de setup cloud
  - Criado infra/schema.sql com schema do banco
  - Criado .gitignore global
- **Próximos Passos:**
  1. Criar conta no GitHub e subir o repositório
  2. Criar banco PostgreSQL no Neon.tech
  3. Deploy do backend no Render.com
  4. Deploy do frontend no Vercel.com
  5. Testar integração completa
