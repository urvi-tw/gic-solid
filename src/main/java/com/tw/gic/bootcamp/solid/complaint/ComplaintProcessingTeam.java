package com.tw.gic.bootcamp.solid.complaint;

import com.tw.gic.bootcamp.solid.external.ExternalService;

import java.util.ArrayList;
import java.util.List;

public class ComplaintProcessingTeam {

    private int teamSize;

    private int SLA;

    private List<CustomerCareExecutive> teamMembers = new ArrayList<>();

    private ComplaintRegister complaintRegister;

    private ExternalService externalService;

    public ComplaintProcessingTeam(int teamSize, int SLA, ComplaintRegister complaintRegister, ExternalService externalService) {
        this.teamSize = teamSize;
        this.SLA = SLA;
        this.complaintRegister = complaintRegister;
        this.externalService = externalService;
    }

    public void startProcessing() throws InterruptedException {
        for (int i = 0; i < teamSize; i++) {
            CustomerCareExecutive customerCareExecutive = new CustomerCareExecutive("Emp-" + i, SLA, complaintRegister, externalService);
            teamMembers.add(customerCareExecutive);
            customerCareExecutive.start();
        }
    }


    public String getStatus() {
        StringBuilder b = new StringBuilder("---- ComplaintProcessingTeam ----").append("\n");
        b.append("Team SLA: " + SLA).append("\n");
        b.append("Team size: " + teamSize).append("\n");
        for (CustomerCareExecutive t : teamMembers) {
            b.append("\t Exec name: \t" + t.getName()).append("\n");
            b.append("\t Processed: \t" + t.getProcessedComplaintsCount()).append("\n");
            b.append("\t Failed: \t" + t.getFailedToProcessCount()).append("\n");
            b.append("\n");
        }
        return b.toString();
    }

}
