package com.tw.gic.bootcamp.solid.payment;

import java.util.List;
import java.util.Map;

public class PaymentHistory {

    private Map<Integer, Payment> paymentList;

    public Payment getPayment(int paymentId){
        return paymentList.get(paymentId);
    }
}
