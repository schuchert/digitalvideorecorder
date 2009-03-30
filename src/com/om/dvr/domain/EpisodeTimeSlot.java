package com.om.dvr.domain;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class EpisodeTimeSlot {
    private final LocalDate date;
    private final LocalTime startTime;
    private final int durationInMinutes;
    private final int channel;

    public EpisodeTimeSlot(LocalDate date, LocalTime startTime, int durationInMinutes, int channel) {
        this.date = date;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.channel = channel;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public int getChannel() {
        return channel;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EpisodeTimeSlot) {
            EpisodeTimeSlot rhs = getClass().cast(obj);
            return rhs.channel == channel && rhs.durationInMinutes == durationInMinutes && rhs.date.equals(date)
                    && rhs.startTime.equals(startTime);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return durationInMinutes * channel * startTime.hashCode() % date.hashCode();
    }

    @Override
    public String toString() {
        return String.format("[%3d, %4d/%02d/%02d, %d:%02d, %3d]", channel, date.getYear(), date.getMonthOfYear(), date
                .getDayOfMonth(), startTime.getHourOfDay(), startTime.getMinuteOfHour(), durationInMinutes);
    }

    public boolean conflictsWith(EpisodeTimeSlot otherSlot) {
        if (channel != otherSlot.channel)
            return false;

        return hasTimeConflictWith(otherSlot);
    }

    public boolean hasTimeConflictWith(EpisodeTimeSlot otherSlot) {
        DateTime startDateTime = date.toDateTime(startTime);
        DateTime endDateTime = startDateTime.plusMinutes(durationInMinutes);

        DateTime otherStartDateTime = otherSlot.date.toDateTime(otherSlot.startTime);
        DateTime otherEndDateTime = otherStartDateTime.plusMinutes(otherSlot.durationInMinutes);

        if (startsWithin(startDateTime, otherStartDateTime, otherEndDateTime))
            return true;

        return endsWithin(endDateTime, otherStartDateTime, otherEndDateTime);
    }

    private boolean startsWithin(DateTime startDateTime, DateTime otherStartDateTime, DateTime otherEndDateTime) {
        return startDateTime.equals(otherStartDateTime)
                || (startDateTime.isAfter(otherStartDateTime) && startDateTime.isBefore(otherEndDateTime));
    }

    private boolean endsWithin(DateTime endDateTime, DateTime otherStartDateTime, DateTime otherEndDateTime) {
        return endDateTime.isAfter(otherStartDateTime) && endDateTime.isBefore(otherEndDateTime);
    }
}