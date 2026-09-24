CREATE EXTENSION IF NOT EXISTS vector;

CREATE TABLE IF NOT EXISTS semantic_schema_mapping (
    id BIGSERIAL PRIMARY KEY,
    legacy_code VARCHAR(100) NOT NULL UNIQUE,
    business_domain VARCHAR(100) NOT NULL,
    business_term VARCHAR(255) NOT NULL,
    definition TEXT NOT NULL,
    source_system VARCHAR(100),
    embedding vector(1536),
    confidence_threshold NUMERIC(5,2) DEFAULT 85.00,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_semantic_mapping_legacy_code
ON semantic_schema_mapping (legacy_code);

CREATE INDEX IF NOT EXISTS idx_semantic_mapping_embedding
ON semantic_schema_mapping USING hnsw (embedding vector_cosine_ops);

CREATE TABLE IF NOT EXISTS audit_event (
    id BIGSERIAL PRIMARY KEY,
    transaction_id VARCHAR(100) NOT NULL,
    event_type VARCHAR(100) NOT NULL,
    actor VARCHAR(100) NOT NULL,
    payload JSONB NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP
);
