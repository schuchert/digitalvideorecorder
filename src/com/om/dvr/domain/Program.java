package com.om.dvr.domain;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class Program {

    public final String programName;
    public final ScheduleSlot slot;

    private final List<Episode> episodes = new LinkedList<Episode>();

    public Program(String programName, int channel, int dayOfWeek, LocalTime timeOfDay, int lengthInMinutes) {
        this.programName = programName;
        this.slot = new ScheduleSlot(channel, dayOfWeek, timeOfDay, lengthInMinutes);
    }

    public Program(String programName, ScheduleSlot slot) {
        this.programName = programName;
        this.slot = slot;
    }

    public String id() {
        return programName + slot;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Program) {
            Program rhs = getClass().cast(other);
            return programName.equals(rhs.programName) && slot.equals(rhs.slot);
        }

        return false;
    }

    public int hash() {
        return id().hashCode();
    }

    public Episode addEpisode(String episodeName, LocalDate programDate, int durationInMinutes) {
        return addEpisode(episodeName, programDate, slot.timeOfDay, durationInMinutes);
    }

    public Iterator<Episode> getEpisodes() {
        return episodes.iterator();
    }

    public String getProgramName() {
        return programName;
    }

    public ScheduleSlot getScheduleSlot() {
        return slot;
    }

    public Episode addEpisode(String episodeName, LocalDate programDate, LocalTime localTime, int durationInMinutes) {
        Episode episode = new Episode(this, episodeName, programDate, localTime, durationInMinutes);
        episodes.add(episode);
        return episode;
    }

    public boolean isOn(int channel) {
        return slot.channel == channel;
    }
    
    @Override
    public String toString() {
        return String.format("[%s,%s]", programName, slot);
    }
}
