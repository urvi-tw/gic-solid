package com.tw.gic.bootcamp.solid.complaint;

import com.tw.gic.bootcamp.solid.util.Logger;

import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComplaintRegister {

    private int complaintId = 1001;

    private LinkedBlockingDeque<CustomerComplaint> complaintList;

    private int totalComplaintsAdded;

    private int totalComplaintsProcessed;

    public ComplaintRegister(int size) {
        this.complaintList = new LinkedBlockingDeque<>(size);
    }


    public CustomerComplaint takeOne() throws InterruptedException {
        CustomerComplaint take = complaintList.take();
        totalComplaintsProcessed++;
        return take;
    }

    public CustomerComplaint get(int id) {
        Stream<CustomerComplaint> customerComplaintStream = complaintList.stream().filter(k -> k.getId() == id);

        return customerComplaintStream.findFirst().orElse(null);
    }

    public List<CustomerComplaint> getAll() {
        return complaintList.stream().collect(Collectors.toList());
    }

    public synchronized void add(CustomerComplaint c) throws InterruptedException {
        Logger.instance().info("ComplaintRegister", "New complaint added " + c.getId()+ " by "+Thread.currentThread().getName());
        complaintList.put(c);
        totalComplaintsAdded++;
        complaintId++;
    }

    public synchronized int getNextComplaintId(){
        return complaintId;
    }

    public int currentComplaintCount() {
        return complaintList.size();
    }


    public String getStats() {
        StringBuilder b = new StringBuilder();
        b.append("----------- Complaint Register -----------").append("\n");
        b.append("Total complaints registered so far: ").append(totalComplaintsAdded).append("\n");
        b.append("Total complaints processed so far: ").append(totalComplaintsProcessed).append("\n");
        b.append("Active complaints in register: ").append(this.currentComplaintCount()).append("\n");
        return b.toString();
    }

}
