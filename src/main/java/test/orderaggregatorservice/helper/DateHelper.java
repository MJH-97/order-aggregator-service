package test.orderaggregatorservice.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String toDateTimeString(LocalDateTime date){
        return date.format(formatter);
    }
}
