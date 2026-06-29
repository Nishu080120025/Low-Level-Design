package org.example.loghandlers;

import org.example.appenders.LogAppenders;
import org.example.model.LogLevel;

import java.util.List;

public class DebugLogHandler extends BaseLogHandler{

    @Override
    public boolean canHandle(LogLevel level){
        return level==LogLevel.DEBUG;
    }
}
