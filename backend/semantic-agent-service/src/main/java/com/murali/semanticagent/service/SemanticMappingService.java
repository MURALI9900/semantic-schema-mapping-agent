package com.murali.semanticagent.service;

import com.murali.semanticagent.client.AiServiceClient;
import com.murali.semanticagent.model.*;
import com.murali.semanticagent.repository.SemanticSchemaMappingRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SemanticMappingService {
 private final SemanticSchemaMappingRepository repo;
 private final AiServiceClient ai;
 private final BankingOperationPublisher publisher;

 public SemanticMappingService(SemanticSchemaMappingRepository repo,AiServiceClient ai,BankingOperationPublisher publisher){
  this.repo=repo; this.ai=ai; this.publisher=publisher;
 }

 public SemanticResponse map(SemanticRequest r){
  String id=r.transactionId()==null||r.transactionId().isBlank()?UUID.randomUUID().toString():r.transactionId();
  String request=Optional.ofNullable(r.request()).orElse("");
  Map<String,Object> aiResult=ai.extractIntent(request);
  String intent=String.valueOf(aiResult.getOrDefault("intent","UNKNOWN"));
  double confidence=((Number)aiResult.getOrDefault("confidence",0.60)).doubleValue()*100.0;

  String code=switch(intent){
   case "LOAN_REPAYMENT"->"TXN_CD_889";
   case "LOAN_CLEARANCE"->"LOAN_CLR_221";
   case "PAYMENT_REFUND"->"REFUND_117";
   case "LOAN_RESCHEDULING"->"RESCH_305";
   default->null;
  };

  if(code==null){
   return new SemanticResponse(id,intent,null,null,confidence,"PENDING_APPROVAL","No confident mapping found; officer approval required");
  }

  Optional<SemanticSchemaMapping> mapping=repo.findByActiveTrue().stream()
    .filter(m->m.getLegacyCode().equals(code)).findFirst();

  if(mapping.isEmpty()){
   return new SemanticResponse(id,intent,null,null,confidence,"PENDING_APPROVAL","Mapping catalog entry not found");
  }

  SemanticSchemaMapping m=mapping.get();
  double threshold=m.getConfidenceThreshold()==null?85.0:m.getConfidenceThreshold();

  if(confidence < threshold){
   return new SemanticResponse(id,intent,m.getLegacyCode(),m.getBusinessTerm(),confidence,"PENDING_APPROVAL","Human officer approval required");
  }

  publisher.publish(new BankingOperationEvent(id,r.officerId(),request,intent,m.getLegacyCode(),m.getBusinessTerm(),confidence));
  return new SemanticResponse(id,intent,m.getLegacyCode(),m.getBusinessTerm(),confidence,"AUTO_APPROVED","Mapping resolved and banking operation dispatched");
 }
}