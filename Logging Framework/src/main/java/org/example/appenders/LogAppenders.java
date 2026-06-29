package org.example.appenders;

import org.example.model.LogMessage;

public interface LogAppenders {

    void append(LogMessage message);
}
