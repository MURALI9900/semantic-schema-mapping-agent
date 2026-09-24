package com.murali.semanticagent.service;

import com.murali.semanticagent.model.BankingOperationEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BankingOperationPublisher {
 private final KafkaTemplate<String,Object> kafkaTemplate;
 public BankingOperationPublisher(KafkaTemplate<String,Object> kafkaTemplate){this.kafkaTemplate=kafkaTemplate;}
 public void publish(BankingOperationEvent event){
  kafkaTemplate.send("banking-operations-events",event.transactionId(),event);
 }
}