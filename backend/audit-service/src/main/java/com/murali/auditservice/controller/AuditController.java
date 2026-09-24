package com.murali.auditservice.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/v1/audit")
public class AuditController {
 @PostMapping("/events")
 public Map<String,Object> event(@RequestBody Map<String,Object> payload){return Map.of("status","RECORDED","eventId",UUID.randomUUID().toString(),"payload",payload);}
 @GetMapping("/health") public String health(){return "audit-service:UP";}
}