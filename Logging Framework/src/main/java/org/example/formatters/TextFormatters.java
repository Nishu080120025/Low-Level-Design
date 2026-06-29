package org.example.formatters;

import org.example.model.LogMessage;
import org.example.util.DateFormatUtil;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TextFormatters implements LogFormatters {

    @Override
    public String formatLog(LogMessage message) {
        String timeStamp = DateFormatUtil.getDate(message.getTimeStamp());
        return String.format("%s", "[%s]", "%s", timeStamp, message.getLogLevel(), message.getMessage());
    }
}
