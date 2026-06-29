package org.example;

import org.example.appenders.LogAppenders;
import org.example.loghandlers.*;
import org.example.model.LogLevel;

public class LogConfiguration {

    private final InfoLogHandler infoLogHandler;
    private final WarnLogHandler warnLogHandler;
    private final FatalLogHandler fatalLogHandler;
    private final ErrorLogHandler errorLogHandler;
    private final DebugLogHandler debugLogHandler;
    private final BaseLogHandler logHandlerChain;

    public LogConfiguration() {
        infoLogHandler = new InfoLogHandler();
        warnLogHandler = new WarnLogHandler();
        fatalLogHandler = new FatalLogHandler();
        errorLogHandler = new ErrorLogHandler();
        debugLogHandler = new DebugLogHandler();

        // Set up the chain of responsibility
        infoLogHandler.setNextHandler(warnLogHandler);
        warnLogHandler.setNextHandler(errorLogHandler);
        errorLogHandler.setNextHandler(fatalLogHandler);
        fatalLogHandler.setNextHandler(debugLogHandler);

        logHandlerChain = infoLogHandler;
    }

    public BaseLogHandler getLogHandlerChain() {
        return logHandlerChain;
    }

    public void addAppenderForLevel(LogLevel level, LogAppenders appender) {
        switch (level) {
            case INFO:
                infoLogHandler.subscribe(appender);
                break;
            case WARN:
                warnLogHandler.subscribe(appender);
                break;
            case ERROR:
                errorLogHandler.subscribe(appender);
                break;
            case FATAL:
                fatalLogHandler.subscribe(appender);
                break;
            case DEBUG:
                debugLogHandler.subscribe(appender);
                break;
        }
    }
}
