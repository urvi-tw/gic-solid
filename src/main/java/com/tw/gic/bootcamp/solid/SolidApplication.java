package com.tw.gic.bootcamp.solid;

import com.tw.gic.bootcamp.solid.complaint.ComplaintProcessingTeam;
import com.tw.gic.bootcamp.solid.complaint.ComplaintRegister;
import com.tw.gic.bootcamp.solid.complaint.ComplaintStatsService;
import com.tw.gic.bootcamp.solid.external.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolidApplication {


    private static ExternalService externalService;

    @Autowired
    public SolidApplication(ExternalService externalService) {
        this.externalService = externalService;
    }

    public static ExternalService getExternalService(){
        return externalService;
    }

    public static void main(String[] args) {

        SpringApplication.run(SolidApplication.class, args);

        try {
            ComplaintRegister orderCompaints = new ComplaintRegister(10);
            ComplaintStatsService.getInstance().setRegsiter(orderCompaints);
            SeedingApplicationDataConfiguration.loadOrderComplaints(orderCompaints);

            ComplaintProcessingTeam orderProcessingTeam = new ComplaintProcessingTeam(2, 1, orderCompaints, externalService);
            ComplaintStatsService.getInstance().setTeam(orderProcessingTeam);
            orderProcessingTeam.startProcessing();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
