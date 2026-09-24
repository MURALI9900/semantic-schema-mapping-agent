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
 @GetMapping("/health")
 public String health(){return "semantic-agent-service:UP";}
}