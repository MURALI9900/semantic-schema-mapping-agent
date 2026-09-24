package com.murali.aiservice.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/v1/ai")
public class AiController {
 @PostMapping("/intent")
 public Map<String,Object> intent(@RequestBody Map<String,String> request){
  String text=Optional.ofNullable(request.get("request")).orElse("").toLowerCase();
  String intent=text.contains("repay")?"LOAN_REPAYMENT":text.contains("refund")?"PAYMENT_REFUND":text.contains("reschedul")?"LOAN_RESCHEDULING":text.contains("clear")||text.contains("closure")?"LOAN_CLEARANCE":"UNKNOWN";
  return Map.of("intent",intent,"model","demo-local-intent-engine","confidence",intent.equals("UNKNOWN")?0.60:0.94);
 }
 @GetMapping("/health") public String health(){return "ai-service:UP";}
}