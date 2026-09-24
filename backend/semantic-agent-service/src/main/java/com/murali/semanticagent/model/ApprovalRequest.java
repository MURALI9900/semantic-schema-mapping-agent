package com.murali.semanticagent.model;

public record ApprovalRequest(String transactionId, String officerId, boolean approved) {}