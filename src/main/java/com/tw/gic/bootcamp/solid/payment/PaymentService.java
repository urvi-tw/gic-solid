package com.tw.gic.bootcamp.solid.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private PaymentHistory paymentHistory;

    public boolean isValidPayment(int paymentId, int customerId){
        Payment payment = paymentHistory.getPayment(paymentId);

        if (payment!=null){
            return payment.getCustomerId() == customerId;
        }
        return false;
    }
}
