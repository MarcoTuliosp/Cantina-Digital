-- ═══════════════════════════════════════════
-- Cantina Digital — Schema do Banco de Dados
-- Executar no Neon.tech após criar o projeto
-- ═══════════════════════════════════════════

-- Clientes
CREATE TABLE IF NOT EXISTS clientes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    tipo VARCHAR(20) DEFAULT 'CANTINA'
);

-- Pedidos
CREATE TABLE IF NOT EXISTS pedidos (
    id_pedido UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    canal VARCHAR(20) DEFAULT 'SITE',
    "timestamp" TIMESTAMP DEFAULT NOW(),
    cliente_id UUID REFERENCES clientes(id),
    total DECIMAL(10, 2),
    status VARCHAR(20) DEFAULT 'RECEBIDO',
    dominio VARCHAR(20) DEFAULT 'CANTINA'
);

-- Itens do Pedido
CREATE TABLE IF NOT EXISTS itens_pedido (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    produto VARCHAR(100) NOT NULL,
    quantidade INTEGER NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    observacao TEXT,
    pedido_id UUID REFERENCES pedidos(id_pedido) ON DELETE CASCADE
);

-- ═══════════════════════════════════════════
-- Índices para performance
-- ═══════════════════════════════════════════
CREATE INDEX IF NOT EXISTS idx_pedidos_status ON pedidos(status);
CREATE INDEX IF NOT EXISTS idx_pedidos_timestamp ON pedidos("timestamp");
CREATE INDEX IF NOT EXISTS idx_pedidos_dominio ON pedidos(dominio);
CREATE INDEX IF NOT EXISTS idx_pedidos_cliente ON pedidos(cliente_id);

-- ═══════════════════════════════════════════
-- Dados de teste (opcional)
-- ═══════════════════════════════════════════
-- INSERT INTO clientes (nome, telefone, tipo) VALUES ('Cliente Teste', '11999999999', 'CANTINA');
