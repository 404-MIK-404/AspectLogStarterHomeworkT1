package org.mik.starterhomeworkaspect.properties;


import org.mik.starterhomeworkaspect.enums.LoggingLevelEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;


@ConfigurationProperties(prefix = "api.aspect.logging")
public class LoggingAspectProperties {

    private boolean enabled;

    private LoggingLevelEnum level;

    @ConstructorBinding
    public LoggingAspectProperties(boolean enabled, LoggingLevelEnum level) {
        this.enabled = enabled;
        this.level = level == null ? LoggingLevelEnum.INFO : level;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LoggingLevelEnum getLevel() {
        return level;
    }

    public void setLevel(LoggingLevelEnum level) {
        this.level = level;
    }
}
