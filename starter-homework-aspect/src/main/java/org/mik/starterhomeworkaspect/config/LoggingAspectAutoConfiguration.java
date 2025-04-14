package org.mik.starterhomeworkaspect.config;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import jakarta.annotation.PostConstruct;
import org.mik.starterhomeworkaspect.aspect.LoggingAspect;
import org.mik.starterhomeworkaspect.enums.LoggingLevelEnum;
import org.mik.starterhomeworkaspect.logging.LoggingStrategy;
import org.mik.starterhomeworkaspect.logging.factory.LoggingStrategyFactory;
import org.mik.starterhomeworkaspect.properties.LoggingAspectProperties;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties(LoggingAspectProperties.class)
@ConditionalOnProperty(prefix = "api.aspect.logging",name = "enable",havingValue = "true",matchIfMissing = true)
public class LoggingAspectAutoConfiguration {

    private final LoggingAspectProperties loggingAspectProperties;

    public LoggingAspectAutoConfiguration(LoggingAspectProperties loggingAspectProperties) {
        this.loggingAspectProperties = loggingAspectProperties;
    }


    @PostConstruct
    public void initConfigureLogging() {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        LoggingLevelEnum level = loggingAspectProperties.getLevel();
        Level logbackLevel = Level.toLevel(level.name().toLowerCase(), Level.INFO);
        context.getLogger("ROOT").setLevel(logbackLevel);
        System.out.println("📢 Logging level set to: " + logbackLevel);
    }

    @Bean
    public LoggingAspect loggingAspect(LoggingAspectProperties loggingAspectProperties, LoggingStrategyFactory loggingStrategyFactory) {
        return new LoggingAspect(loggingAspectProperties,loggingStrategyFactory);
    }

    @Bean
    public LoggingStrategyFactory loggingStrategyFactory(List<LoggingStrategy> strategies) {
        return new LoggingStrategyFactory(strategies);
    }

}
