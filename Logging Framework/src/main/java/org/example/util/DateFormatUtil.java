package org.example.util;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class DateFormatUtil {
    private static DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static String getDate(Long TimeStamp){
        return Instant.ofEpochMilli(TimeStamp)
                .atZone(java.time.ZoneId.systemDefault())
                .format(dateTimeFormatter);
    }
}
