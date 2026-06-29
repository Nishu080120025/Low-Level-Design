package org.example.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class LogMessage {
    private String message;
    private LogLevel logLevel;
    private Long timeStamp;

    public LogMessage(String message, LogLevel logLevel) {
        this.message = message;
        this.logLevel = logLevel;
        this.timeStamp = System.currentTimeMillis();
    }
}
