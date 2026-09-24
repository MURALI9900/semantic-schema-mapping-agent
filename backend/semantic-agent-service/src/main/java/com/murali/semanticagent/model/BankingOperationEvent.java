package com.murali.semanticagent.model;

public record BankingOperationEvent(
 String transactionId,
 String officerId,
 String request,
 String intent,
 String legacyCode,
 String businessTerm,
 double confidence
) {}