package com.murali.semanticagent.controller;

import com.murali.semanticagent.model.*;
import com.murali.semanticagent.service.SemanticMappingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/semantic")
public class SemanticAgentController {
 private final SemanticMappingService service;
 public SemanticAgentController(SemanticMappingService service){this.service=service;}

 @PostMapping("/map")
 public SemanticResponse map(@RequestBody SemanticRequest request){return service.map(request);}

 @PostMapping("/approve")
 public SemanticResponse approve(@RequestBody ApprovalRequest request){
  return new SemanticResponse(request.transactionId(),"HUMAN_APPROVAL",null,null,100.0,
   request.approved()?"APPROVED":"REJECTED",
   request.approved()?"Officer approval recorded; operation workflow will be enhanced with persisted pending state in the next phase."
   :"Officer rejected the proposed mapping");
 }

 @GetMapping("/health")
 public String health(){return "semantic-agent-service:UP";}
}