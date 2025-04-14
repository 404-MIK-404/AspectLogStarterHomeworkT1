package org.mik.starterhomeworkaspect.logging.impl;

import org.mik.starterhomeworkaspect.enums.LoggingLevelEnum;
import org.mik.starterhomeworkaspect.logging.LoggingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class InfoLoggingStrategyImpl implements LoggingStrategy  {


    private final Logger logger = LoggerFactory.getLogger(InfoLoggingStrategyImpl.class);


    @Override
    public void log(String message,Object... params) {
        logger.info(message,params);
    }

    @Override
    public LoggingLevelEnum getLevel() {
        return LoggingLevelEnum.INFO;
    }


}
