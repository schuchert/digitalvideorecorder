package com.om.dvr.fixture;

import org.joda.time.LocalTime;

public class ProgramDescription {
    public LocalTime startTime;
    public final int channel;
    public final String name;

    ProgramDescription(int channel, String name, LocalTime startTime) {
        this.channel = channel;
        this.name = name;
        this.startTime = startTime;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof ProgramDescription) {
            ProgramDescription rhs = getClass().cast(other);
            return rhs.channel == channel && rhs.name.equals(name) && rhs.startTime.equals(startTime);
        }
        return false;
    }

    public int durationInMinutes() {
        return name.length() * 15;
    }

    public String timeOfDay() {
        return String.format("%d:%02d", startTime.getHourOfDay(), startTime.getMinuteOfHour());
    }
}
