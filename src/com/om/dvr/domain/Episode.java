package com.om.dvr.domain;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class Episode {
    public final Program program;
    public final String name;
    public final EpisodeTimeSlot slot;

    public Episode(Program program, String episodeName, LocalDate episodeDate, LocalTime startTime,
            int durationInMinutes) {
        this.program = program;
        this.name = episodeName;
        this.slot = new EpisodeTimeSlot(episodeDate, startTime, durationInMinutes, program.slot.channel);
    }

    public Program getProgram() {
        return program;
    }

    public String getName() {
        return name;
    }

    public EpisodeTimeSlot getSlot() {
        return slot;
    }

    public boolean conflictsWith(EpisodeTimeSlot otherSlot) {
        return slot.conflictsWith(otherSlot);
    }

    public boolean hasTimeConflictWith(Episode scheduled) {
        return slot.hasTimeConflictWith(scheduled.slot);
    }

    @Override
    public String toString() {
        return String.format("[%s,%s]", name, slot);
    }
}