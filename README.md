# Semantic Schema Mapping Agent for Core Banking

An enterprise-oriented reference implementation that translates natural-language banking officer requests into semantic legacy-schema mappings, applies confidence-based human approval, orchestrates operations through Kafka, and maintains an audit trail.

## Architecture

- API Gateway: 8080
- Banking Operations: 8081
- AI Service: 8084
- Audit Service: 8085
- Semantic Agent Service: 8086
- Officer Portal: 4200
- PostgreSQL + pgvector: 5432
- Kafka: 9092

## Core flow

Natural language request -> AI intent extraction -> pgvector semantic mapping -> confidence evaluation -> human approval when confidence < 85% -> Kafka banking operation -> audit event.

## Repository structure

```
backend/
  semantic-agent-service/
  banking-operations-service/
  ai-service/
  audit-service/
  api-gateway/
frontend/
  officer-portal/
database/
  migrations/
  seed/
infrastructure/
  docker/
docs/
.github/workflows/
```

## Safety and demo data

This project uses synthetic banking schemas and data only. It must not contain production credentials, customer information, or proprietary bank schemas.

## Status

Phase 1 - architecture and repository foundation.
