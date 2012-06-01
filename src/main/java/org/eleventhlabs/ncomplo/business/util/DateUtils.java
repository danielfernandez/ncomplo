package org.eleventhlabs.ncomplo.business.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public final class DateUtils {

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN);
    

    public static String toString(final Calendar calendar) {
        return DATE_FORMAT.format(calendar.getTime());
    }

    public static Calendar toCalendar(final String string) throws ParseException {
        final Date date = DATE_FORMAT.parse(string);
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date.getTime());
        return calendar;
    }
    
    
    private DateUtils() {
        super();
    }
    
    
}
