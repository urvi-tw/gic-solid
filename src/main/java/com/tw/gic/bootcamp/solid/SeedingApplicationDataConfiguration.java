package com.tw.gic.bootcamp.solid;

import com.tw.gic.bootcamp.solid.complaint.ComplaintRegister;
import com.tw.gic.bootcamp.solid.complaint.CustomerComplaint;
import com.tw.gic.bootcamp.solid.complaint.UrgentCustomerComplaint;
import com.tw.gic.bootcamp.solid.order.Order;
import com.tw.gic.bootcamp.solid.order.OrderStatus;
import com.tw.gic.bootcamp.solid.product.Category;
import com.tw.gic.bootcamp.solid.product.Product;
import com.tw.gic.bootcamp.solid.product.ProductStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SeedingApplicationDataConfiguration {

    @Bean
    public List<Product> getProductsForSeeding() {
        final List<Product> products = new ArrayList<>();
        products.add(new Product(10, "Parker Pen", Category.STATIONARY, 101, ProductStatus.SOLD));
        products.add(new Product(20, "Parker Pen", Category.STATIONARY, 101, ProductStatus.SOLD));
        products.add(new Product(30, "Hero Pen", Category.STATIONARY, 101, ProductStatus.SOLD));
        products.add(new Product(40, "Hero Pen", Category.STATIONARY, 101, ProductStatus.SOLD));
        products.add(new Product(50, "Sony 32 TV", Category.ELECTRONICS, 24566, ProductStatus.AVAILABLE));
        products.add(new Product(60, "Sony 32 TV", Category.ELECTRONICS, 24566, ProductStatus.AVAILABLE));
        return products;
    }

    @Bean
    public Map<Category, Map<Integer, Product>> loadInventory() {
        List<Product> products = getProductsForSeeding();

        HashMap<Category, Map<Integer, Product>> inventory = new HashMap<>();

        for (Product product : products) {
            Map<Integer, Product> productMap = inventory.get(product.getCategory());
            if (productMap == null) {
                productMap = new HashMap<>();
                inventory.put(product.getCategory(), productMap);
            }
            productMap.put(product.getSerialNumber(), product);
        }
        return inventory;
    }

    @Bean
    public HashMap<Integer, Order> loadOrders() {
        Date date = new Date(System.currentTimeMillis());
        HashMap<Integer, Order> orders = new HashMap<>();
        orders.put(1, new Order(1, 23, 33, 10, date, OrderStatus.NEW, "123/ 4, Banglore"));
        orders.put(2, new Order(2, 23, 34, 20, date, OrderStatus.NEW, "123/ 4, Banglore"));
        orders.put(3, new Order(3, 23, 35, 30, date, OrderStatus.NEW, "123/ 4, Banglore"));
        orders.put(4, new Order(4, 23, 35, 40, date, OrderStatus.PROCESSING, "123/ 4, Banglore"));
        return orders;
    }

    // keeps adding dummy complaints in background.
    public static void loadOrderComplaints(ComplaintRegister complaintRegister) throws InterruptedException {
        new Thread(() -> {
            for (int i = 1001; i < 100000; i++) {
                try {
                    if (i % 24 != 0) {
                        synchronized (complaintRegister) {
                            int nextComplaintId = complaintRegister.getNextComplaintId();
                            complaintRegister.add(CustomerComplaint.builder().id(nextComplaintId).orderId(34 + i).customerEmail("user"+nextComplaintId+"@mail.com").build());
                        }
                    } else {
                        UrgentCustomerComplaint u = new UrgentCustomerComplaint();
                        u.setOrderId(34 + i);
                        synchronized (complaintRegister) {
                            int nextComplaintId = complaintRegister.getNextComplaintId();
                            u.setId(nextComplaintId);
                            u.setCustomerEmail("user"+nextComplaintId+"@mail.com");
                            u.setCustomerPhoneNumber("9756"+nextComplaintId);
                            complaintRegister.add(u);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
