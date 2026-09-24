package com.murali.bankingops;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
public class BankingOperationConsumer {
 @KafkaListener(topics="banking-operations-events",groupId="banking-operations-service")
 public void consume(Map<String,Object> event){System.out.println("BANKING OPERATION RECEIVED: "+event);}
}