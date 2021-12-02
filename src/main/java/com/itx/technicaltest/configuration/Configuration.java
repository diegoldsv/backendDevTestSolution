package com.itx.technicaltest.configuration;

import com.itx.technicaltest.TechnicaltestApplication;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }
    
    @Bean
    public Log getLogger()
    {
        return LogFactory.getLog(TechnicaltestApplication.class);
    }
}
