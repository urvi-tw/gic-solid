package com.tw.gic.bootcamp.solid.complaint;

import com.tw.gic.bootcamp.solid.external.ExternalService;
import com.tw.gic.bootcamp.solid.util.Logger;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

@Getter
public class CustomerCareExecutive extends Thread {

    private int SLA;

    private ComplaintRegister complaintRegister;

    private ExternalService externalService;

    private int processedComplaintsCount;

    private int failedToProcessCount;

    public CustomerCareExecutive(String execName, int SLA, ComplaintRegister register, ExternalService externalService){
        super(execName);
        this.SLA = SLA;
        this.complaintRegister = register;
        this.externalService = externalService;
    }


    @Override
    public void run() {
        while (true) {
            try {
                CustomerComplaint complaint = complaintRegister.takeOne();
                processComplaint(complaint);
                processedComplaintsCount++;
            } catch (Exception e) {
                Logger.instance().error(Thread.currentThread().getName(), e.getMessage());
                failedToProcessCount++;
            }
        }
    }

    public int getProcessedComplaintsCount() {
        return processedComplaintsCount;
    }

    public int getFailedToProcessCount() {
        return failedToProcessCount;
    }

    void processComplaint(CustomerComplaint c) throws InterruptedException {
        c.setStatus(ComplaintStatus.PROCESSING);
        Logger.instance().info(Thread.currentThread().getName(), "Processing complaint ID: " + c.getId() + " on order " + c.getOrderId());
        TimeUnit.SECONDS.sleep(SLA);
        c.emailCustomer(externalService);
        if (c instanceof UrgentCustomerComplaint) {
            ((UrgentCustomerComplaint) c).smsCustomer(externalService);
        }
        c.setStatus(ComplaintStatus.CLOSED);

    }

}
