package com.tw.gic.bootcamp.solid.complaint;

import com.tw.gic.bootcamp.solid.external.ExternalService;
import com.tw.gic.bootcamp.solid.util.ConfigReader;
import com.tw.gic.bootcamp.solid.util.Logger;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
@Setter
public class CustomerComplaint {

    private int id;

    private int customerId;

    private int productId;

    private int orderId;

    private ComplaintStatus status = ComplaintStatus.NEW;

    private String complaint;

    private Date createdDate = new Date();

    private String customerEmail;

    public void emailCustomer(ExternalService externalService) {
        Logger.instance().info("CustomerComplaint", "Emailing customer at " + customerEmail);
        externalService.sendEmail(customerEmail, "Complaint ID " + id, "Your complaint is processed.");
    }


}
