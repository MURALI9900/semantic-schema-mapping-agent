package com.murali.semanticagent.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import java.util.Map;

@Component
public class AiServiceClient {
 private final RestClient client;
 public AiServiceClient(@Value("${AI_SERVICE_URL:http://localhost:8084}") String url){
  this.client=RestClient.builder().baseUrl(url).build();
 }
 public Map<String,Object> extractIntent(String request){
  return client.post().uri("/api/v1/ai/intent").body(Map.of("request",request)).retrieve().body(Map.class);
 }
}