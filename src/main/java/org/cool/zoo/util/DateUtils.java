package org.cool.zoo.util;

import java.sql.Date;
import java.sql.Timestamp;

public class DateUtils {

    public static java.util.Date getDateFormat(Date date) {
        java.sql.Timestamp timestamp = null;
        if (date != null) {
            timestamp = new Timestamp(date.getTime());
        }
        return timestamp;
    }

    public static Date convertStringToDate(String string) {
        Date date = null;
        Timestamp timestamp = null;
        if (string != null && !string.equals("") && !string.isEmpty()) {
            timestamp = Timestamp.valueOf(string);
            date = new Date(timestamp.getTime());
        }

        return date;
    }
}
