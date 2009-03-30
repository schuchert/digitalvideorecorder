package com.om.dvr.domain;

import org.joda.time.LocalTime;

public class ScheduleSlot {

    public final int channel;
    public final int dayOfWeek;
    public final LocalTime timeOfDay;
    public final int durationInMintues;

    public ScheduleSlot(int channel, int dayOfWeek, LocalTime timeOfDay, int durationInMintues) {
        this.channel = channel;
        this.dayOfWeek = dayOfWeek;
        this.timeOfDay = timeOfDay;
        this.durationInMintues = durationInMintues;
    }

    public int getChannel() {
        return channel;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getTimeOfDay() {
        return timeOfDay;
    }

    public int getDurationInMinutes() {
        return durationInMintues;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ScheduleSlot) {
            ScheduleSlot rhs = getClass().cast(other);
            return channel == rhs.channel && dayOfWeek == rhs.dayOfWeek && timeOfDay.equals(rhs.timeOfDay);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return ("" + dayOfWeek + timeOfDay + channel).hashCode();
    }

    @Override
    public String toString() {
        return String.format("[%d:%s:%d:%02d]", channel, dayOfWeek, timeOfDay.getHourOfDay(), timeOfDay
                .getMinuteOfHour());
    }

    public LocalTime endsAt() {
        return timeOfDay.plusMinutes(durationInMintues);
    }
}
