package org.example.appenders;

import org.example.formatters.LogFormatters;
import org.example.model.LogMessage;

public class ConsoleLogAppenders implements LogAppenders {

    private LogFormatters formatters;
    public ConsoleLogAppenders(LogFormatters formatters) {
        this.formatters = formatters;
    }
    @Override
    public void append(LogMessage message){
        System.out.println(formatters.formatLog(message));
    }

}
