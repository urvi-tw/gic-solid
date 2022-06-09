package com.tw.gic.bootcamp.solid.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;

@Configuration
@Getter
public class ConfigReader {

    @Value("${external.service.url}")
    private String extSvcUrl;

    @Value("#{new Integer('${complaint.register.size}')}")
    private Integer complaintRegisterSize;

    @Value("#{new Integer('${team.size}')}")
    private Integer teamSize;

    @Value("#{new Integer('${team.sla}')}")
    private Integer teamSLA;



    @PostConstruct
    public void afterPropertiesSet() {
        if (this.complaintRegisterSize == null || this.teamSize==null) {
            Logger.instance().error("SolidApplication", "Application configuration Incomplete");
            System.exit(1);
        }
    }

}
