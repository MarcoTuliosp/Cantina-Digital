# 🛠️ Setup da Infraestrutura Cloud — Cantina Digital

> Todos os serviços abaixo são **100% gratuitos**. Nenhum cartão de crédito necessário.

---

## 1. 🐘 PostgreSQL — Neon.tech

1. Acesse [neon.tech](https://neon.tech) e crie uma conta (pode usar GitHub)
2. Clique em **"Create Project"**
3. Nome do projeto: `cantina-digital`
4. Região: escolha a mais próxima (São Paulo se disponível)
5. Após criar, copie a **Connection String** que aparece
6. Use os dados para preencher o `.env`:
   - `DATABASE_URL` → a connection string JDBC
   - `DATABASE_USER` → username mostrado
   - `DATABASE_PASSWORD` → password mostrado

> **Limite Free:** 0.5 GB storage, 100 compute-hours/mês

---

## 2. ☕ Backend Java — Render.com

1. Acesse [render.com](https://render.com) e crie uma conta (pode usar GitHub)
2. Conecte seu repositório GitHub
3. Clique em **"New" → "Web Service"**
4. Selecione o repositório `cantina-digital`
5. Configure:
   - **Name:** `cantina-api`
   - **Region:** Oregon (ou mais próxima)
   - **Root Directory:** `backend`
   - **Runtime:** Docker
   - **Instance Type:** Free
6. Adicione as **Environment Variables**:
   - `DATABASE_URL` → a URL JDBC do Neon
   - `DATABASE_USER` → seu user do Neon
   - `DATABASE_PASSWORD` → sua senha do Neon
   - `CORS_ORIGINS` → URL do seu frontend no Vercel
   - `PORT` → `8080`
7. Clique em **"Create Web Service"**

> **Limite Free:** 512MB RAM, dorme após 15min inativo

---

## 3. 🌐 Frontend React — Vercel.com

1. Acesse [vercel.com](https://vercel.com) e crie uma conta (pode usar GitHub)
2. Clique em **"New Project"**
3. Importe o repositório `cantina-digital`
4. Configure:
   - **Framework Preset:** Vite
   - **Root Directory:** `frontend`
5. Adicione a **Environment Variable**:
   - `VITE_API_URL` → URL do backend no Render (ex: `https://cantina-api.onrender.com`)
6. Clique em **"Deploy"**

> **Limite Free:** 100GB bandwidth/mês, deploy automático a cada push

---

## 4. ⚡ n8n — Render.com (Futuro)

Será configurado quando chegarmos na Fase 4 (Estilo — Relatórios e Notificações).

---

## 5. 🔴 Redis — Upstash.com (Futuro)

Será adicionado quando o sistema precisar de escalabilidade com filas.

---

## Diagrama dos Serviços

```
                    ┌──────────────┐
                    │   VERCEL     │
                    │  Frontend    │
                    │  (React)     │
                    └──────┬───────┘
                           │ HTTPS
                    ┌──────▼───────┐
                    │   RENDER     │
                    │  Backend     │
                    │  (Spring)    │
                    └──────┬───────┘
                           │ SSL
                    ┌──────▼───────┐
                    │    NEON      │
                    │  PostgreSQL  │
                    │  (Cloud)     │
                    └──────────────┘
```
