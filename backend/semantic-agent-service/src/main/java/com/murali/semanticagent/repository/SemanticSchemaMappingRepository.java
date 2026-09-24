package com.murali.semanticagent.repository;
import com.murali.semanticagent.model.SemanticSchemaMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface SemanticSchemaMappingRepository extends JpaRepository<SemanticSchemaMapping,Long> {
 List<SemanticSchemaMapping> findByActiveTrue();
}