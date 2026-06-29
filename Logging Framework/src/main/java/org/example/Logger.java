package org.example;

import org.example.loghandlers.BaseLogHandler;
import org.example.model.LogLevel;
import org.example.model.LogMessage;

public  class Logger {
    private static Logger instance;
   private LogConfiguration logConfiguration;
   private final BaseLogHandler logHandlerChain;
    private Logger(){
        logConfiguration = new LogConfiguration();
        logHandlerChain= logConfiguration.getLogHandlerChain();
    }
    public static Logger getInstance(){
        if(instance==null){
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message, LogLevel level){
        LogMessage logMessage = new LogMessage(message, level);
        logHandlerChain.handleLog(logMessage);
    }


    

}
