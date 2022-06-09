package com.tw.gic.bootcamp.solid;

import com.tw.gic.bootcamp.solid.complaint.ComplaintProcessingTeam;
import com.tw.gic.bootcamp.solid.complaint.ComplaintRegister;
import com.tw.gic.bootcamp.solid.complaint.ComplaintStatsService;
import com.tw.gic.bootcamp.solid.error.ServiceException;
import com.tw.gic.bootcamp.solid.external.ExternalService;
import com.tw.gic.bootcamp.solid.util.ConfigReader;
import com.tw.gic.bootcamp.solid.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolidApplication {


    private static ExternalService externalService;

    private static ConfigReader configReader;

    @Autowired
    public SolidApplication(ExternalService externalService,ConfigReader configReader) {
        this.configReader = configReader;
        this.externalService = externalService;
    }

    public static ExternalService getExternalService(){
        return externalService;
    }

    public static ConfigReader getConfigReader()
    {
        return configReader;
    }

    public static void main(String[] args) {

        SpringApplication.run(SolidApplication.class, args);

        try {

            if (getConfigReader().getComplaintRegisterSize() == null || getConfigReader().getTeamSize()==null) {
                Logger.instance().error("SolidApplication", "Application configuration incomplete");
                System.exit(1);
            }

            ComplaintRegister orderCompaints = new ComplaintRegister(getConfigReader().getComplaintRegisterSize());
            ComplaintStatsService.getInstance().setRegsiter(orderCompaints);
            SeedingApplicationDataConfiguration.loadOrderComplaints(orderCompaints);

            ComplaintProcessingTeam orderProcessingTeam = new ComplaintProcessingTeam(getConfigReader().getTeamSize(), getConfigReader().getTeamSLA(), orderCompaints, externalService);
            ComplaintStatsService.getInstance().setTeam(orderProcessingTeam);
            orderProcessingTeam.startProcessing();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
