package com.solstice;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeUtil {

    public static Timestamp getTimestampFromDateString(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        java.util.Date date = simpleDateFormat.parse(dateString);
        return new Timestamp(date.getTime());
    }

}
