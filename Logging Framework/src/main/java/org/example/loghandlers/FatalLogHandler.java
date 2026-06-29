package org.example.loghandlers;

import org.example.model.LogLevel;

public class FatalLogHandler extends BaseLogHandler{
    @Override
    public boolean canHandle(LogLevel level) {
        return level == LogLevel.FATAL;
    }
}
