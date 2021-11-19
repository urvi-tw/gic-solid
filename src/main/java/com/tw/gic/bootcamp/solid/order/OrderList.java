package com.tw.gic.bootcamp.solid.order;

import org.springframework.stereotype.Component;

import javax.inject.Singleton;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Component
public class OrderList {

    private Map<Integer, Order> orderList;

    public OrderList(Map<Integer, Order> orderList) {
        this.orderList = orderList;
    }

    public Collection<Order> getAll(){
        return orderList.values();
    }

    public Order getOrder(int orderId){
        return orderList.get(orderId);
    }

    public void addOrder(Order order){
        orderList.put(order.getId(), order);
    }
}
