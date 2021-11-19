package com.tw.gic.bootcamp.solid.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class Payment {

    private int id;

    private int customerId;

    private int amount;

    private Date date;
}
