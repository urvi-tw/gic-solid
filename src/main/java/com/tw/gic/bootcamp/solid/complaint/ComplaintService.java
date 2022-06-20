package com.tw.gic.bootcamp.solid.complaint;

import com.tw.gic.bootcamp.solid.error.ServiceException;
import com.tw.gic.bootcamp.solid.team.ComplaintProcessingTeam;

import java.util.List;

public class ComplaintService {

    private ComplaintRegister register;

    private ComplaintProcessingTeam team;

    private static ComplaintService instance = new ComplaintService();

    private ComplaintService() {
    }

    public static ComplaintService getInstance() {
        return instance;
    }

    public void setRegsiter(ComplaintRegister r) {
        register = r;
    }

    public void setTeam(ComplaintProcessingTeam t) {
        team = t;
    }

    public int addComplaint(CreateComplaint complaintCreate) throws InterruptedException {

        CustomerComplaint customerComplaint = new CustomerComplaint();
        customerComplaint.setComplaint(complaintCreate.getComplaint());
        customerComplaint.setOrderId(complaintCreate.getOrderId());
        synchronized (register) {
            int complaintId = register.getNextComplaintId();
            customerComplaint.setId(complaintId);
            register.add(customerComplaint);
            return complaintId;
        }
    }

    public List<CustomerComplaint> getAllComplaints() {
        return register.getAll();
    }

    public CustomerComplaint getComplaint(int id) throws ServiceException {
        CustomerComplaint customerComplaint = register.get(id);

        if (customerComplaint == null) {
            throw new ServiceException("No such complaint " + id);
        }
        return customerComplaint;
    }

    public String getStatus() {
        String stats = "";
        stats = stats + register.getStats() + " \n\n";
        stats = stats + team.getStatus() + " \n\n";
        return stats;
    }
}
