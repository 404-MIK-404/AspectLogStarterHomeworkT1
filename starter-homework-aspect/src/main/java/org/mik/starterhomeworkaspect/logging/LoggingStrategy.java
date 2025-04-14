package org.mik.starterhomeworkaspect.logging;


import org.mik.starterhomeworkaspect.enums.LoggingLevelEnum;

public interface LoggingStrategy {

    void log(String message,Object... params);

    LoggingLevelEnum getLevel();

}
