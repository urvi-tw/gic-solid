package com.tw.gic.bootcamp.solid.product;

import com.tw.gic.bootcamp.solid.util.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    @Test
    void addProduct() {
        Inventory inventory = new Inventory(new HashMap<>());

        InventoryService s = new InventoryService(inventory);
        s.addProduct(new Product(10, "Parker Pen", Category.STATIONARY, ProductType.PHYSICAL,101, ProductStatus.SOLD));
        s.addProduct(new Product(10, "Parker Pen", Category.ELECTRONICS, ProductType.PHYSICAL,101, ProductStatus.SOLD));
    }

    @Test
    void testService(){
        Logger.instance().info("CustomerComplaint", "Emailing customer at ");

        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();
        
        String forObject = restTemplate.getForObject("https://inventory-external-svc.herokuapp.com/", String.class);

        Logger.instance().info("CustomerComplaint", "Response is " + forObject);



    }

    @Test
    void testEnvRead(){




    }
}