package com.tw.gic.bootcamp.solid.team;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/team")
public class TeamController {

    @GetMapping("/stats")
    public ResponseEntity getStats() {
        return ResponseEntity.ok(TeamService.getInstance().getTeamStats());
    }



}
