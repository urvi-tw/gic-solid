package com.tw.gic.bootcamp.solid.team;

import org.springframework.stereotype.Service;

@Service
public class TeamService {


    private ComplaintProcessingTeam complaintProcessingTeam;

    private static TeamService instance = new TeamService();


    public static TeamService getInstance() {
        return instance;
    }

    public TeamService() {
    }

    public String getTeamStats()
    {
        return complaintProcessingTeam.getStatus();

    }

}
