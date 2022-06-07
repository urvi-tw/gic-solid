package com.tw.gic.bootcamp.solid.shipping;

import com.tw.gic.bootcamp.solid.SolidApplication;
import com.tw.gic.bootcamp.solid.product.ProductType;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public String dispatchProduct(int serialNumber, int orderId, int customerId, String address, ProductType productType) {
        if (address == null || address.isEmpty() || serialNumber < 0) {
            return "invalid details";
        }
        if(productType==ProductType.DIGITAL)
            SolidApplication.getExternalService().digitallyDeliverProduct(serialNumber,address);
        else
            SolidApplication.getExternalService().courierProduct(serialNumber, orderId, customerId, address);
        return null;
    }
}

