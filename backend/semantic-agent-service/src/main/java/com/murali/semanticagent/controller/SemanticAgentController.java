package com.murali.semanticagent.controller;
import com.murali.semanticagent.model.*;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
@RestController
@RequestMapping("/api/v1/semantic")
public class SemanticAgentController {
 @PostMapping("/map")
 public SemanticResponse map(@RequestBody SemanticRequest r) {
  String id=r.transactionId()==null||r.transactionId().isBlank()?UUID.randomUUID().toString():r.transactionId();
  String text=r.request()==null?"":r.request().toLowerCase();
  if(text.contains("repay")) return new SemanticResponse(id,"LOAN_REPAYMENT","TXN_CD_889","Loan Repayment",94.0,"AUTO_APPROVED","Semantic mapping found");
  if(text.contains("clear")||text.contains("closure")) return new SemanticResponse(id,"LOAN_CLEARANCE","LOAN_CLR_221","Loan Clearance",91.0,"AUTO_APPROVED","Semantic mapping found");
  if(text.contains("refund")) return new SemanticResponse(id,"PAYMENT_REFUND","REFUND_117","Payment Refund",89.0,"AUTO_APPROVED","Semantic mapping found");
  if(text.contains("reschedul")) return new SemanticResponse(id,"LOAN_RESCHEDULING","RESCH_305","Loan Rescheduling",87.0,"AUTO_APPROVED","Semantic mapping found");
  return new SemanticResponse(id,"UNKNOWN",null,null,60.0,"PENDING_APPROVAL","No confident mapping found; officer approval required");
 }
}