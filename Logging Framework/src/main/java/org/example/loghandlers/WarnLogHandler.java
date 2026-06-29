package org.example.loghandlers;

import org.example.model.LogLevel;

public class WarnLogHandler extends BaseLogHandler {
    @Override
    public boolean canHandle(LogLevel level) {
        return level == LogLevel.WARN;
    }
}
