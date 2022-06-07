package com.tw.gic.bootcamp.solid.external;

import com.tw.gic.bootcamp.solid.util.ConfigReader;
import com.tw.gic.bootcamp.solid.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExternalService {

    private ConfigReader configReader;

    @Autowired
    public ExternalService(ConfigReader configReader) {
        this.configReader = configReader;
    }


    public void sendEmail(String emailId, String subject, String content) {

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        Map<String, String> map = new HashMap<>();
        map.put("content", content);
        map.put("emailId", emailId);
        map.put("subject", subject);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(map, new HttpHeaders());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(configReader.getExtSvcUrl() + "/email", requestEntity, String.class);

        Logger.instance().info("ExternalService", stringResponseEntity.toString());

    }

    public void sendSms(String phoneNumber, String message) {

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        Map<String, String> map = new HashMap<>();
        map.put("phoneNumber", phoneNumber);
        map.put("message", message);
        HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(map, new HttpHeaders());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(configReader.getExtSvcUrl() + "/sms", requestEntity, String.class);

        Logger.instance().info("ExternalService", stringResponseEntity.toString());
    }

    public void courierProduct(int serialNumber, int orderId,int customerId,String address) {

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        Map<String, Object> map = new HashMap<>();
        map.put("serialNumber", serialNumber);
        map.put("orderId", orderId);
        map.put("customerId", customerId);
        map.put("address", address);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, new HttpHeaders());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(configReader.getExtSvcUrl() + "/courierProduct", requestEntity, String.class);

        Logger.instance().info("ExternalService", stringResponseEntity.toString());
    }

    public void digitallyDeliverProduct(int serialNumber, String address) {

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        Map<String, Object> map = new HashMap<>();
        map.put("email", address);
        map.put("productId", serialNumber);
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, new HttpHeaders());

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(configReader.getExtSvcUrl() + "/digitalDelivery", requestEntity, String.class);

        Logger.instance().info("ExternalService", stringResponseEntity.toString());
    }


}