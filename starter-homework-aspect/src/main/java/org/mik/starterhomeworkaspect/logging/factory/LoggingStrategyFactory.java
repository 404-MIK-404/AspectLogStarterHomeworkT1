package org.mik.starterhomeworkaspect.logging.factory;


import org.mik.starterhomeworkaspect.enums.LoggingLevelEnum;
import org.mik.starterhomeworkaspect.logging.LoggingStrategy;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;

@Component
public class LoggingStrategyFactory {

    private final EnumMap<LoggingLevelEnum, LoggingStrategy> loggingStrategyMap;

    public LoggingStrategyFactory(List<LoggingStrategy> listLoggingStrategy) {
        this.loggingStrategyMap = new EnumMap<>(LoggingLevelEnum.class);
        listLoggingStrategy.forEach(loggingStrategy -> {
            this.loggingStrategyMap.put(loggingStrategy.getLevel(),loggingStrategy);
        });
    }

    public LoggingStrategy getLoggingStrategy(LoggingLevelEnum loggingLevelEnum) {
        return this.loggingStrategyMap.get(loggingLevelEnum);
    }

}
