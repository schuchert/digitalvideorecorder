package com.om.dvr.util;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class TimeUtil {
    public static String dayNumberToName(int dayNumber) {
        switch (dayNumber) {
        case DateTimeConstants.MONDAY:
            return "Monday";
        case DateTimeConstants.TUESDAY:
            return "Tuesday";
        case DateTimeConstants.WEDNESDAY:
            return "Wednesday";
        case DateTimeConstants.THURSDAY:
            return "Thursday";
        case DateTimeConstants.FRIDAY:
            return "Friday";
        case DateTimeConstants.SATURDAY:
            return "Saturday";
        case DateTimeConstants.SUNDAY:
            return "Sunday";
        default:
            throw new RuntimeException("Invalid dayNumber provided: " + dayNumber);
        }
    }

    public static int dayNameToNumber(String dayName) {
        if ("monday".equalsIgnoreCase(dayName))
            return DateTimeConstants.MONDAY;
        if ("tuesday".equalsIgnoreCase(dayName))
            return DateTimeConstants.TUESDAY;
        if ("wednesday".equalsIgnoreCase(dayName))
            return DateTimeConstants.WEDNESDAY;
        if ("thursday".equalsIgnoreCase(dayName))
            return DateTimeConstants.THURSDAY;
        if ("friday".equalsIgnoreCase(dayName))
            return DateTimeConstants.FRIDAY;
        if ("saturday".equalsIgnoreCase(dayName))
            return DateTimeConstants.SATURDAY;
        if ("sunday".equalsIgnoreCase(dayName))
            return DateTimeConstants.SUNDAY;
        if ("monday".equalsIgnoreCase(dayName))
            return DateTimeConstants.MONDAY;

        throw new RuntimeException("Invalid dayName provided: " + dayName);
    }

    public static String formatDateYMD(LocalDate date) {
        return String.format("%d/%d/%d", date.getYear(), date.getMonthOfYear(), date.getDayOfMonth());
    }

    public static String formatTimeHMM(LocalTime startTime) {
        return String.format("%d:%02d", startTime.getHourOfDay(), startTime.getMinuteOfHour());
    }
}
