package com.tw.gic.bootcamp.solid.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@Getter
public class ConfigReader {

    @Value("${external.service.url}")
    private String extSvcUrl;

}
