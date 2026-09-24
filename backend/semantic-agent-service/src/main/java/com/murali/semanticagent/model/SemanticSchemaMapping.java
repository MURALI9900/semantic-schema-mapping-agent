package com.murali.semanticagent.model;
import jakarta.persistence.*;
@Entity
@Table(name="semantic_schema_mapping")
public class SemanticSchemaMapping {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(name="legacy_code",nullable=false,unique=true) private String legacyCode;
 @Column(name="business_domain",nullable=false) private String businessDomain;
 @Column(name="business_term",nullable=false) private String businessTerm;
 @Column(nullable=false) private String definition;
 @Column(name="source_system") private String sourceSystem;
 public Long getId(){return id;} public String getLegacyCode(){return legacyCode;} public String getBusinessDomain(){return businessDomain;}
 public String getBusinessTerm(){return businessTerm;} public String getDefinition(){return definition;} public String getSourceSystem(){return sourceSystem;}
}