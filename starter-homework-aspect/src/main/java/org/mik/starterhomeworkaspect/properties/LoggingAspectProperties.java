package org.mik.starterhomeworkaspect.properties;


import org.slf4j.event.Level;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;


@ConfigurationProperties(prefix = "api.aspect.logging")
public class LoggingAspectProperties {

    private boolean enabled;

    private Level level;

    @ConstructorBinding
    public LoggingAspectProperties(boolean enabled, Level level) {
        this.enabled = enabled;
        this.level = level == null ? Level.INFO : level;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }
}
