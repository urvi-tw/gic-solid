package com.tw.gic.bootcamp.solid.order;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@Getter
@ToString
@Setter
public class Order {

    private int id;

    private int customerId;

    private int paymentId;

    private int productId;

    private Date orderDate;

    private OrderStatus status;

    private String address;
}
