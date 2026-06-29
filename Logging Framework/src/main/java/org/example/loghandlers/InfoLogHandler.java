package org.example.loghandlers;

import org.example.model.LogLevel;

public class InfoLogHandler extends BaseLogHandler {
    @Override
    public boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }
}
