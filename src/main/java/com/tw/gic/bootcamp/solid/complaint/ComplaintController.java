package com.tw.gic.bootcamp.solid.complaint;

import com.tw.gic.bootcamp.solid.error.ServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/complaint")
public class ComplaintController {

    @PostMapping
    public ResponseEntity addComplaint(CreateComplaint c) throws InterruptedException {
        return ResponseEntity.ok("Added complaint. ID is: "+ComplaintStatsService.getInstance().addComplaint(c));
    }

    @GetMapping
    public ResponseEntity getComplaint(int id) throws ServiceException {
        return ResponseEntity.ok(ComplaintStatsService.getInstance().getComplaint(id));
    }

    @GetMapping("/all")
    public ResponseEntity getAllComplaints() {
        return ResponseEntity.ok(ComplaintStatsService.getInstance().getAllComplaints());
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam(required = false) int customerId) {
        return null;
    }


    @GetMapping("/stats")
    public ResponseEntity getStats() {
        return ResponseEntity.ok(ComplaintStatsService.getInstance().getStatus());
    }
}
