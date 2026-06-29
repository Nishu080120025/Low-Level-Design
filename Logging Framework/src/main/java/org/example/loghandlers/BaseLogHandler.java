package org.example.loghandlers;

import lombok.Getter;
import lombok.Setter;
import org.example.appenders.LogAppenders;
import org.example.model.LogLevel;
import org.example.model.LogMessage;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public abstract class BaseLogHandler {

    protected BaseLogHandler nextHandler;
    protected List<LogAppenders>appenders;

    public BaseLogHandler() {
        this.appenders = new CopyOnWriteArrayList<>();
    }

    public void handleLog(LogMessage message){
        if(canHandle(message.getLogLevel())){
            notifyObserver(message);
        }
        else{
            if(nextHandler!=null){
                nextHandler.handleLog(message);
            }
        }
    }

     public boolean canHandle(LogLevel logLevel) {
         return false;
     }

     public  void notifyObserver(LogMessage message){
        for(LogAppenders appender:appenders){
            appender.append(message);
        }
     }

     public void subscribe(LogAppenders appender){
        appenders.add(appender);
     }
}
