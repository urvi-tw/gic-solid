package com.tw.gic.bootcamp.solid.complaint;

import com.tw.gic.bootcamp.solid.external.ExternalService;
import com.tw.gic.bootcamp.solid.util.Logger;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class UrgentCustomerComplaint extends CustomerComplaint {

    private String customerPhoneNumber = "-not set-";

    public void smsCustomer(ExternalService externalService) {
        Logger.instance().info("UrgentCustomerComplaint", "Sending sms to customer at " + customerPhoneNumber);
        externalService.sendSms(customerPhoneNumber,"Your complaint "+getId()+" is processed");
    }

    public void emailCustomer(ExternalService externalService) {
        throw new RuntimeException("emailing is not supported for urgent complaints, sms them instead");
    }

}
