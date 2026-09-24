package com.murali.semanticagent.model;
public record SemanticResponse(String transactionId,String intent,String legacyCode,String businessTerm,double confidence,String status,String message) {}