package com.murali.semanticagent.service;
import com.murali.semanticagent.model.*;
import com.murali.semanticagent.repository.SemanticSchemaMappingRepository;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class SemanticMappingService {
 private final SemanticSchemaMappingRepository repo;
 public SemanticMappingService(SemanticSchemaMappingRepository repo){this.repo=repo;}
 public SemanticResponse map(SemanticRequest r){
  String id=r.transactionId()==null||r.transactionId().isBlank()?UUID.randomUUID().toString():r.transactionId();
  String text=Optional.ofNullable(r.request()).orElse("").toLowerCase();
  String keyword=text.contains("repay")?"repay":text.contains("clear")||text.contains("closure")?"clear":text.contains("refund")?"refund":text.contains("reschedul")?"reschedule":"";
  if(keyword.isEmpty()) return new SemanticResponse(id,"UNKNOWN",null,null,60.0,"PENDING_APPROVAL","No confident mapping found; officer approval required");
  String code=switch(keyword){case "repay"->"TXN_CD_889";case "clear"->"LOAN_CLR_221";case "refund"->"REFUND_117";default->"RESCH_305";};
  return repo.findByActiveTrue().stream().filter(m->m.getLegacyCode().equals(code)).findFirst()
   .map(m->new SemanticResponse(id,m.getBusinessTerm().toUpperCase().replace(' ','_'),m.getLegacyCode(),m.getBusinessTerm(),92.0,"AUTO_APPROVED","Mapping resolved from semantic schema catalog"))
   .orElse(new SemanticResponse(id,"UNKNOWN",null,null,60.0,"PENDING_APPROVAL","Mapping catalog entry not found"));
 }
}