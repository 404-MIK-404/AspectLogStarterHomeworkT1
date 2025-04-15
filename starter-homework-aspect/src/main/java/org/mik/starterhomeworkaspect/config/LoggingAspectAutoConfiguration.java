package org.mik.starterhomeworkaspect.config;

import org.mik.starterhomeworkaspect.aspect.LoggingAspect;
import org.mik.starterhomeworkaspect.properties.LoggingAspectProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(LoggingAspectProperties.class)
@ConditionalOnProperty(prefix = "api.aspect.logging",name = "enable",havingValue = "true",matchIfMissing = true)
public class LoggingAspectAutoConfiguration {

    private final LoggingAspectProperties loggingAspectProperties;

    public LoggingAspectAutoConfiguration(LoggingAspectProperties loggingAspectProperties) {
        this.loggingAspectProperties = loggingAspectProperties;
    }


    @Bean
    public LoggingAspect loggingAspect(LoggingAspectProperties loggingAspectProperties) {
        return new LoggingAspect(loggingAspectProperties);
    }


}
