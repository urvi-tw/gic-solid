package com.tw.gic.bootcamp.solid.shipping;

import com.tw.gic.bootcamp.solid.SolidApplication;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public String sendProduct(int serialNumber, int orderId, int customerId, String address) {
        if (address == null || address.isEmpty() || serialNumber < 0) {
            return "invalid details";
        }
        SolidApplication.getExternalService().courierProduct(serialNumber, orderId, customerId, address);
        return null;
    }
}

