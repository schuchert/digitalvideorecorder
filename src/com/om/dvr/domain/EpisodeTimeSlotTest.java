package com.om.dvr.domain;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EpisodeTimeSlotTest {
    @Test
    public void twoSameSlotsConflictInTime() throws Exception {
        EpisodeTimeSlot slot1 = new EpisodeTimeSlot(new LocalDate(), new LocalTime("1:00"), 60, 42);
        EpisodeTimeSlot slot2 = new EpisodeTimeSlot(new LocalDate(), new LocalTime("1:00"), 60, 42);
        assertTrue(slot1.hasTimeConflictWith(slot2));
    }

    @Test
    public void twoSlotsOnEachOthersEdgeDoNotConflict() throws Exception {
        EpisodeTimeSlot slot1 = new EpisodeTimeSlot(new LocalDate(), new LocalTime("1:00"), 60, 42);
        EpisodeTimeSlot slot2 = new EpisodeTimeSlot(new LocalDate(), new LocalTime("2:00"), 60, 42);
        assertFalse(slot1.hasTimeConflictWith(slot2));
        assertFalse(slot2.hasTimeConflictWith(slot1));
    }

    @Test
    public void twoSlotsWith15MinuteOverlapConflictWithEachOther() {
        EpisodeTimeSlot slot1 = new EpisodeTimeSlot(new LocalDate(), new LocalTime("1:00"), 45, 42);
        EpisodeTimeSlot slot2 = new EpisodeTimeSlot(new LocalDate(), new LocalTime("1:30"), 60, 42);
        assertTrue(slot1.hasTimeConflictWith(slot2));
        assertTrue(slot2.hasTimeConflictWith(slot1));
    }
}
