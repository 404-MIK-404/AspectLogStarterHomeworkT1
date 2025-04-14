package org.mik.starterhomeworkaspect.logging.impl;

import org.mik.starterhomeworkaspect.enums.LoggingLevelEnum;
import org.mik.starterhomeworkaspect.logging.LoggingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component()
public class DebugLoggingStrategyImpl implements LoggingStrategy {

    private final Logger logger = LoggerFactory.getLogger(DebugLoggingStrategyImpl.class);


    @Override
    public void log(String message) {
        logger.debug(message);
    }

    @Override
    public LoggingLevelEnum getLevel() {
        return LoggingLevelEnum.DEBUG;
    }
}
