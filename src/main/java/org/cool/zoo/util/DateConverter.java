package org.cool.zoo.util;



import java.util.Date;

public class DateConverter {


    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }


    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
